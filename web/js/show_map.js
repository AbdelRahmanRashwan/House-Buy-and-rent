var old_marker = null;
var map = null;
var address = "";
function loadMap() {
    var myLatLng = {lat: -25.363, lng: 131.044};
    var options = {
        zoom: 4,
        center: myLatLng
    };
    map = new google.maps.Map(document.getElementById('map'), options);
    google.maps.event.addListener(map, 'click', function(event) {
        placeMarker(event.latLng);
        getAddress(event.latLng);
        // document.getElementById('address').setAttribute('value',address);
    });
    // document.writeln(address);
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