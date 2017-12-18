var ad_id;
function show_map(lng, lat) {
    var myLatLng = {lat: lat, lng: lng};
    var options = {
        zoom: 4,
        center: myLatLng
    };
    map = new google.maps.Map(document.getElementById('map'), options);
    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map
    });
}

function delete_ad(id) {
    var strURL = "DeleteAdServlet?id="+id;
    var xmlHttp = null;

    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
        xmlHttp = new XMLHttpRequest();
    }else if (window.ActiveXObject) { // IE
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlHttp.open("GET", strURL);
    xmlHttp.onreadystatechange=function(){
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            if(xmlHttp.responseText === "success"){
                window.alert("Ad deleted successfully");
                window.location.href="home.jsp";
            }else{
                window.alert("Sorry something went wrong");
            }
        }
    };
    xmlHttp.send();
}

function updateHTML(ad) {
    ad_id = ad['id'];
    var ad_container = document.getElementById('ad');
    document.getElementById('house_type').innerHTML = ad['type'];
    document.getElementById('description').setAttribute('value',ad['description']);
    document.getElementById('area').setAttribute('value', ad['area']);

    //Showing pictures
    var pictures_container = document.getElementById('photo_slideshow');
    for(var pic of ad['pictures']){
        var img = document.createElement('img');
        img.setAttribute('src',"data:image/png;base64,"+pic['imageBase64']) ;
        pictures_container.appendChild(img);
    }

    //Showing house location on map
    show_map(ad['longitude'],ad['latitude']);

    //Showing comments
    var comments_container = document.getElementsByClassName('comments')[0];
    for(var comment of ad['comments']){
        var comment_p = document.createElement('p');
        comment_p.innerHTML = comment['userName'] +": "+comment['comment'];
        comments_container.appendChild(comment_p);
    }

    //Showing the ad edit and delete
    if(ad['user']['id'] === curr_user_id){
        var editBtn = document.createElement('button');
        var deleteBtn = document.createElement('button');
        editBtn.innerHTML = 'Edit';
        deleteBtn.innerHTML = 'Delete';
        editBtn.addEventListener("click", function() {
            window.location.href = "edit_ad.jsp?id="+ad['id'];
        });
        deleteBtn.addEventListener("click", function() {
            var ans = confirm('You cannot undone this, are you sure you want to delete this ad?');
            if(ans === true){
                delete_ad(ad['id']);
            }
        });
        ad_container.appendChild(editBtn);
        ad_container.appendChild(deleteBtn);
    }
}

function postComment(comment_val) {
    var strURL = "CommentServlet";

    var xmlHttp = null;

    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
        xmlHttp = new XMLHttpRequest();
    }else if (window.ActiveXObject) { // IE
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlHttp.open("POST", strURL);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.onreadystatechange=function(){processCommentRequest(xmlHttp, comment_val);};
    xmlHttp.send("comment=" +JSON.stringify({id:ad_id,comment_val:comment_val}));

}

function processCommentRequest(xmlHttp, comment_val) {

    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
        //Get data
        var response = xmlHttp.responseText;

        if(response === "success"){
            //Update the HTML
            var comments_container = document.getElementsByClassName('comments')[0];
            var comment_p = document.createElement('p');
            comment_p.innerHTML = curr_user_name +": "+ comment_val;
            comments_container.appendChild(comment_p);
        }
    }
}

function comment(event) {
    if(event.keyCode === 13){
        var comment_val = document.getElementById('comment').value;
        document.getElementById('comment').value = '';
        if(!(comment_val.trim() === '')){
            postComment(comment_val);
        }
    }
}