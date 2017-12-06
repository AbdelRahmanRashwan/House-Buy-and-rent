var ads;

function processRequest(xmlHttp) {
    // document.writeln(xmlHttp.readyState+" & "+xmlHttp.status);
    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
        //Get data
        var ads_json = xmlHttp.responseText;
        // document.writeln("before parsing");
        ads = JSON.parse(ads_json);

        //Update the HTML
        updateHTML();
    }
}

function addNewAdCard(ad, house) {
    var ad_li = document.createElement('li');
    var ad_type = document.createElement('h1');
    var ad_description = document.createElement('p');
    var view_ad_button = document.createElement('button');

    ad_type.id = 'type';
    ad_type.innerHTML = ad['type'];

    ad_description.id = 'description';
    ad_description.innerHTML = house['description'];

    view_ad_button.innerHTML = 'View';
    view_ad_button.addEventListener("click", function() {
        alert("you clicked "+ad['id']); });

    ad_li.listStyle = 'none';

    ad_li.appendChild(ad_type);
    ad_li.appendChild(ad_description);
    ad_li.appendChild(view_ad_button);
    document.getElementById('ads_list').appendChild(ad_li);
}

function updateHTML() {
    document.getElementById('ads_list').innerHTML = '';

    //Get the reference of the DIV in the HTML DOM by passing the ID
    for (var ad of ads) {
        var house = ad['house'];

        addNewAdCard(ad,house);
    }
}

function get_all_ads(){
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

    filters['location'] = document.getElementById('location').value;
    filters['type'] = document.getElementById('type').value;
    filters['size'] = {'lower_bound':size_lower_bound, 'upper_bound':size_upper_bound};

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
