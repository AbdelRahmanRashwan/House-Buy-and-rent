function get_ad(ad_id) {

    var strURL = "GetFilteredAdsServlet";

    var xmlHttp = null;

    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
        xmlHttp = new XMLHttpRequest();
    }else if (window.ActiveXObject) { // IE
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlHttp.open("POST", strURL);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.onreadystatechange=function(){processRequest(xmlHttp);};
    xmlHttp.send("filter=" +JSON.stringify({id:ad_id}));

}

function processRequest(xmlHttp) {

    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
        //Get data
        var ads_json = xmlHttp.responseText;
        // document.writeln("before parsing");
        ads = JSON.parse(ads_json);

        //Update the HTML
        updateHTML(ads[0]);
    }
}

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
    document.getElementById('house_type').innerHTML = ad['type'];
    document.getElementById('description').setAttribute('value',ad['description']);
    document.getElementById('area').setAttribute('value', ad['area']);
    show_map(ad['longitude'],ad['latitude']);
}