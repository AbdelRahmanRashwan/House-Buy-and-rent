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
    var ad_container = document.getElementById('ad');
    document.getElementById('house_type').innerHTML = ad['type'];
    document.getElementById('description').setAttribute('value',ad['description']);
    document.getElementById('area').setAttribute('value', ad['area']);
    var pictures_container = document.getElementById('photo_slideshow');
    for(var pic of ad['pictures']){
        var img = document.createElement('img');
        img.setAttribute('src',"data:image/png;base64,"+pic['imageBase64']) ;
        pictures_container.appendChild(img);
    }
    show_map(ad['longitude'],ad['latitude']);
    // if(ad['user']['id'] === curr_user_id){
        var editBtn = document.createElement('button');
        editBtn.innerHTML = 'Edit';
        editBtn.addEventListener("click", function() {
            window.location.href = "edit_ad.jsp?id="+ad['id'];
        });
        ad_container.appendChild(editBtn);
    // }
}