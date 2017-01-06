/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var markers = [];
var addresses = [];
var address;
var deletionAddress;
var geocoder = new google.maps.Geocoder;

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 8,
        center: {
            lat: 44,
            lng: 18
        }
    });

    map.addListener('click', function (e) {
        placeMarkerAndPanTo(e.latLng, map);
    });
}

function placeMarkerAndPanTo(latLng, map) {
    var marker = new google.maps.Marker({
        position: latLng,
        map: map
    });
    markers.push(marker);
    marker.addListener("dblclick", function () {
        var a = marker.getPosition().lat();
        var b = marker.getPosition().lng();
        writeLocation(a + " " + b);
        marker.setMap(null);
        ////////
        deleteAddress(a, b);
        ////////
        removeA(deletionAddress);
    });
    map.panTo(latLng);
    var output = latLng.toString();
    output = output.substring(0, output.length - 1);
    output = output.substring(1);
    var latlngStr = output.split(',', 2);
    var lat = parseFloat(latlngStr[0]);
    var lng = parseFloat(latlngStr[1]);
    getReverseGeocodingData(lat, lng);
}

function writeLocation(text) {
    document.getElementById('text').innerHTML = text;
}

function getReverseGeocodingData(lat, lng) {
    var latlng = new google.maps.LatLng(lat, lng);
    // This is making the Geocode request
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({
        'latLng': latlng
    }, function (results, status) {
        if (status !== google.maps.GeocoderStatus.OK) {
            alert(status);
        }
        if (status == google.maps.GeocoderStatus.OK) {
            address = (results[0].formatted_address);
            addresses.push(address);
            writeLocation(address);
        }
    });
}

function deleteAddress(lat, lng) {
    var latlng = new google.maps.LatLng(lat, lng);
    // This is making the Geocode request
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({
        'latLng': latlng
    }, function (results, status) {
        if (status !== google.maps.GeocoderStatus.OK) {
            alert(status);
        }
        if (status == google.maps.GeocoderStatus.OK) {
            deletionAddress = (results[0].formatted_address);
        }
    });
}

function showAddresses() {
    var s = "";
    for (var i = 0; i < addresses.length; i++) {
        s = s + addresses[i] + " | ";
    }
    writeLocation(s);
}

function removeA(arr) {
    var index = addresses.indexOf(arr);
    addresses.splice(index, 1);
}


