var ads;

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

function get_suspended_ads() {

    var strURL = "GetFilteredAdsServlet";

    var filters = {};

    filters['suspend'] = 1;


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
    for (var ad of ads) {
        addNewAdCard(ad);
    }
}

function addNewAdCard(ad) {
    var ad_li = document.createElement('li');
    var ad_type = document.createElement('h1');
    var ad_description = document.createElement('p');
    var ad_image = document.createElement('img');
    var view_ad_button = document.createElement('button');
    var unsuspend_button = document.createElement('button');

    ad_type.id = 'type';
    ad_type.innerHTML = ad['type'];

    ad_image.id = 'image';
    ad_image.setAttribute('src',"data:image/png;base64,"+ad['pictures'][0]['imageBase64']) ;

    ad_description.id = 'description';
    ad_description.innerHTML = ad['description'];

    view_ad_button.innerHTML = 'View';
    view_ad_button.addEventListener("click", function() {
        window.location.href = "show_ad.jsp?id="+ad['id'];
    });

    unsuspend_button.innerHTML = 'Un-suspend';
    unsuspend_button.addEventListener("click", function() {
        unsuspendAd(ad);
    });

    ad_li.listStyle = 'none';

    ad_li.appendChild(ad_type);
    ad_li.appendChild(ad_image);
    ad_li.appendChild(ad_description);
    ad_li.appendChild(view_ad_button);
    ad_li.appendChild(unsuspend_button);
    document.getElementById('ads_list').appendChild(ad_li);
}


function refresh(ad) {

    ads.splice(ads.indexOf(ad),1);
    updateHTML();
}

function unsuspendAd(ad) {

    var strURL = "AdServlet?id="+ad['id']+"&suspended="+false;
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
