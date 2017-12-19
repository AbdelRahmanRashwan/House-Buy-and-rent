var ads;
var user_type;

function processRequest(xmlHttp) {
    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
        //Get data
        var ads_json = xmlHttp.responseText;
        // document.writeln("before parsing");
        ads = JSON.parse(ads_json);

        //Update the HTML
        updateHTML();
    }
}

function get_all_ads(userType){
    user_type = userType;
    var strURL = "GetAdsServlet";
    var xmlHttp;

    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
        xmlHttp = new XMLHttpRequest();
    }else if (window.ActiveXObject) { // IE
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlHttp.open('GET', strURL, true);
    xmlHttp.onreadystatechange=function(){processRequest(xmlHttp);};
    xmlHttp.send();
}

function get_filtered_ads() {

    var strURL = "GetFilteredAdsServlet";

    var size_lower_bound = document.getElementById('lower_bound').value;
    var size_upper_bound = document.getElementById('upper_bound').value;

    var filters = {};

    filters['area'] = document.getElementById('area').value;
    filters['type'] = document.getElementById('house_type').value;
    filters['size'] = {'lower_bound':size_lower_bound, 'upper_bound':size_upper_bound};
    filters['suspend'] = 0;


    var xmlHttp = null;

    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
        xmlHttp = new XMLHttpRequest();
    }else if (window.ActiveXObject) { // IE
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlHttp.open("POST", strURL);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.onreadystatechange=function(){processRequest(xmlHttp);};
    xmlHttp.send("filter=" +JSON.stringify(filters));

}

function updateHTML() {
    document.getElementById('ads_list').innerHTML = '';

    //Get the reference of the DIV in the HTML DOM by passing the ID
    for (var i = 0; i <ads.length;i++) {
        addNewAdCard(ads[i]);
    }
}

function addNewAdCard(ad) {
    var ad_li = document.createElement('li');
    var ad_type = document.createElement('h1');
    var ad_description = document.createElement('p');
    var ad_image = document.createElement('img');
    var view_ad_button = document.createElement('button');

    ad_type.id = 'type';
    ad_type.innerHTML = "Type: "+ad['type'];

    ad_image.id = 'image';
    ad_image.setAttribute('src',"data:image/png;base64,"+ad['pictures'][0]['imageBase64']) ;
    document.writeln(ad['pictures'][0]['image_bytes']);

    ad_description.id = 'description';
    ad_description.innerHTML = "Description: "+ad['description'];

    view_ad_button.innerHTML = 'View';
    view_ad_button.addEventListener("click", function() {
        window.location.href = "show_ad.jsp?id="+ad['id'];
    });

    ad_li.listStyle = 'none';

    ad_li.appendChild(ad_type);
    // ad_li.appendChild(ad_image);
    ad_li.appendChild(ad_description);
    ad_li.appendChild(view_ad_button);

    ad_li.setAttribute("class","ads-card");


    if(user_type === "admin"){
        var suspend_button = document.createElement('button');
        var delete_button = document.createElement('button');
        suspend_button.innerHTML = 'Suspend';
        delete_button.innerHTML = 'Delete';
        suspend_button.addEventListener("click", function() {
            suspendAd(ad);
        });

        delete_button.addEventListener("click", function() {
            var ans = confirm('You cannot undone this, are you sure you want to delete this ad?');
            if(ans === true){
                delete_ad(ad);
            }
        });

        ad_li.appendChild(delete_button);
        ad_li.appendChild(suspend_button);
    }
    document.getElementById('ads_list').appendChild(ad_li);
}


function refresh(ad) {

    ads.splice(ads.indexOf(ad),1);
    updateHTML();
}

function suspendAd(ad) {

    var strURL = "AdServlet?id="+ad['id']+"&suspended="+true;
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
                refresh(ad);
            }else{
                window.alert("Sorry something went wrong");
            }
        }
    };
    xmlHttp.send();
}

function delete_ad(ad) {
    var strURL = "AdServlet?id="+ad['id'];
    var xmlHttp = null;

    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
        xmlHttp = new XMLHttpRequest();
    }else if (window.ActiveXObject) { // IE
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlHttp.open("DELETE", strURL);
    xmlHttp.onreadystatechange=function(){
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            if(xmlHttp.responseText === "success"){
                window.alert("Ad deleted successfully");
                refresh(ad);
            }else{
                window.alert("Sorry something went wrong");
            }
        }
    };
    xmlHttp.send();
}


// function redirect(url, ad) {
//     document.getElementById('houseType').setAttribute('value', ad['type']);
//     // document.getElementById('space').setAttribute('value', ad['space']);
//     // document.getElementById('description').setAttribute('value', ad['description']);
//     // document.getElementById('location').setAttribute('value', ad['area']);
//     // document.getElementById('floor').setAttribute('value', ad['floor']);
//     // document.getElementById('lng').setAttribute('value', ad['longitude']);
//     // document.getElementById('lat').setAttribute('value', ad['latitude']);
//     document.getElementById('hidden_form').setAttribute('action',url);
//     document.getElementById('hidden_form').submit();
//
//
// }
