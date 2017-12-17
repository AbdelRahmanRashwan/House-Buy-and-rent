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

function updateHTML(ad) {
    // document.writeln('i am here');
    ad_id = ad['id'];
    var ad_container = document.getElementById('ad');
    document.getElementById('house_type').innerHTML = ad['type'];
    document.getElementById('description').setAttribute('value',ad['description']);
    document.getElementById('area').setAttribute('value', ad['area']);

    //Adding pictures
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

    if(ad['user']['id'] === curr_user_id){
        var editBtn = document.createElement('button');
        editBtn.innerHTML = 'Edit';
        editBtn.addEventListener("click", function() {
            window.location.href = "edit_ad.jsp?id="+ad['id'];
        });
        ad_container.appendChild(editBtn);
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