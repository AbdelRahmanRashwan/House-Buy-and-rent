var curr_user_id;
function get_ad(ad_id, user_id) {
    curr_user_id = user_id;
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