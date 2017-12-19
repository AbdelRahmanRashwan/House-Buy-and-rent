
var old_marker = null;
var map = null;
var address = "";

function show_map(lng, lat) {
    var myLatLng = {lat: lat, lng: lng};
    var options = {
        zoom: 4,
        center: myLatLng
    };
    map = new google.maps.Map(document.getElementById('map'), options);
    old_marker = new google.maps.Marker({
        position: myLatLng,
        map: map
    });

    google.maps.event.addListener(map, 'click', function(event) {
        placeMarker(event.latLng);
        getAddress(event.latLng);
        // document.getElementById('address').setAttribute('value',address);
    });
}

function updateHTML(ad) {
    if(curr_user_id !== ad['user']['id']){
        window.alert("You are not the owner of this ad");
        window.location.href = 'home.jsp';
    }
    document.getElementById('ad_id').setAttribute('value', ad['id']);
    document.getElementById('house_type').setAttribute('value', ad['type']);
    document.getElementById('description').setAttribute('value',ad['description']);
    document.getElementById('area').setAttribute('value', ad['area']);
    document.getElementById('space').setAttribute('value', ad['size']);
    document.getElementById('floor').setAttribute('value', ad['floor']);
    document.getElementById('lat').setAttribute('value', ad['latitude']);
    document.getElementById('lng').setAttribute('value', ad['longitude']);
    var pictures_container = document.getElementById('photo_slideshow');
    for(var pic of ad['pictures']){
        var img = document.createElement('img');
        img.setAttribute('src',"data:image/png;base64,"+pic['imageBase64']) ;
        pictures_container.appendChild(img);
    }
    show_map(ad['longitude'],ad['latitude']);
}

function getAddress(latLng) {
    var geoCoder = new google.maps.Geocoder;
    var infoWindow = new google.maps.InfoWindow;
    geoCoder.geocode({'location': latLng}, function(results, status) {
        if (status === 'OK') {
            if (results[0]) {
                // Get only the area
                address = results[0].formatted_address;
                infoWindow.setContent(address);
                infoWindow.open(map, old_marker);
                document.getElementById('area').setAttribute('value',address);
                document.getElementById('lat').setAttribute('value',latLng.lat());
                document.getElementById('lng').setAttribute('value',(latLng.lng()));
            } else {
                window.alert('No results found');
            }
        } else {
            window.alert("Can't find this location");
        }
    });

}

function placeMarker(location) {
    if (old_marker != null) {
        old_marker.setMap(null);
    }
    old_marker = new google.maps.Marker({
        position: location,
        map: map
    });
}