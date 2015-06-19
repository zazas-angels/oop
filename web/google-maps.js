/**
 * Created by nika on 6/18/15.
 */
var initialized = false;
function googleMap() {
    if (initialized === false) {
        initialize();
    }
    $("#map-canvas").toggle();
}


var map;
function initialize() {
    if (initialized === false) {
        var mapCanvas = document.getElementById('map-canvas');
        var mapOptions = {
            center: new google.maps.LatLng(42.1324592, 43.8070955),
            zoom: 7,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        }
        map = new google.maps.Map(mapCanvas, mapOptions);

        google.maps.event.addListener(map, 'click', function (event) {
            placeMarker(event.latLng);
        });
        initialized = true;
    }
}

// Zoom to 12 when clicking on marker
function addInfo(marker, contentString, closeInfo) {
    var infowindow = new google.maps.InfoWindow();
    infowindow.setContent(contentString[0]);

    //Find remove button in infoWindow
    var removeBtn = contentString.find("a.remove-marker")[0];

    //add click listner to remove marker button
    google.maps.event.addDomListener(removeBtn, "click", function (event) {
        //call remove_marker function to remove the marker from the map
        remove_marker(marker);
    });

    //Find save button in infoWindow
    var saveBtn = contentString.find('a.save-marker')[0];

    if (typeof saveBtn !== 'undefined') //continue only when save button is present
    {
        //add click listner to save marker button
        google.maps.event.addDomListener(saveBtn, "click", function (event) {
            var mName = contentString.find('input.save-name')[0].value; //name input field value
            var mDesc = contentString.find('textarea.save-desc')[0].value; //description input field value

            if (mName == '' || mDesc == '') {
                alert("Please enter Name and Description!");
            } else {
                //call save_marker function and save the marker details
                save_marker(marker, mName, mDesc);
            }
        });
    }

    if (!closeInfo) {
        infowindow.open(map, marker);
    }
    google.maps.event.addListener(marker, 'click', function () {
        infowindow.open(map, marker);
    });
}


function placeMarker(location) {
    var marker = new google.maps.Marker({
        position: location,
        draggable: true,
        animation: google.maps.Animation.DROP,
        map: map
    });
    var contentString = $('<div class="marker-edit">' +
        '<form name="SaveMarker" id="SaveMarker">' +
        '<table>' +
        '<tr><td><label for="pName"><span>სახელი :</span></label></td></tr>' +
        '<tr><td><input type="text" name="pName" class="save-name" placeholder="ჩაწერეთ სახელი" maxlength="40" /></label></td></tr>' +
        '<tr><td><label for="pDesc"><span>აღწერა :</span></td></tr>' +
        '<tr><td><textarea name="pDesc" class="save-desc" placeholder="ჩაწერეთ მისამართი" maxlength="150"></textarea></label></td></tr>' +
        '<tr><td><a class = "save-marker" href="#">შენახვა</a><a class = "remove-marker" style="padding-left: 5em" href="#">წაშლა</a></td></tr>' +
        '</table>' +
        '</form>' +
        '</div>');
    addInfo(marker, contentString, false);
}

$(document).ready(function () {
    addMarkers();
});


function addMarkers() {
    $.post("map-marker.jsp",
        {"type": "get"},
        function (data) {
            var arr = data;
            if (arr.length > 0) {
                initialize();
                for (var i = 0; i < arr.length; i++) {
                    var mark = arr[i];
                    var myLatlng = new google.maps.LatLng(mark.lat, mark.lng);

                    var marker = new google.maps.Marker({
                        position: myLatlng,
                        draggable: false,
                        animation: google.maps.Animation.DROP,
                        map: map
                    });
                    var contentString = $('<div class="marker-edit">' +
                        '<form name="SaveMarker" id="SaveMarker">' +
                        '<table>' +
                        '<tr><td><label for="pName" <span>სახელი :</span></label></td></tr>' +
                        '<tr><td><input type="text" name="pName" value=\"' + mark.name + '\" class="save-name" placeholder="ჩაწერეთ სახელი" maxlength="40" /></label></td></tr>' +
                        '<tr><td><label for="pDesc"><span>აღწერა :</span></td></tr>' +
                        '<tr><td><textarea name="pDesc" class="save-desc" placeholder="ჩაწერეთ მისამართი" maxlength="150">' + mark.desc + '</textarea></label></td></tr>' +
                        '<tr><td><a class = "save-marker" href="#">შენახვა</a><a class = "remove-marker" style="padding-left: 5em" href="#">წაშლა</a></td></tr>' +
                        '</table>' +
                        '</form>' +
                        '</div>');
                    addInfo(marker, contentString, true);
                }
                $("#googleMap-button").click();
            }
        }
    );
}

//############### Remove Marker Function ##############
function remove_marker(Marker) {
    /* determine whether marker is draggable
     new markers are draggable and saved markers are fixed */
    if (Marker.getDraggable()) {
    }
    else {
        //Remove saved marker from DB and map using jQuery Ajax
        var mLatLang = Marker.getPosition(); //get marker position
        $.post("map-marker.jsp",
            {lat: mLatLang.A, lang: mLatLang.F, "type": "remove"}
            // Marker.setDraggable(false); //set marker to fixed
        );
    }
    Marker.setMap(null); //just remove new marker
}


//############### Save Marker Function ##############
function save_marker(Marker, mName, mAddress) {
    var mLatLang = Marker.getPosition(); //get marker position

    /* determine whether marker is draggable
     new markers are draggable and saved markers are fixed */
    if (Marker.getDraggable()) {
    }
    else {
        //Remove saved marker from DB and map using jQuery Ajax
        $.post("map-marker.jsp",
            {lat: mLatLang.A, lang: mLatLang.F, "type": "remove"}
        );
    }

    //Save new marker using jQuery Ajax
    var mLatLang = Marker.getPosition(); //get marker position
    $.post("map-marker.jsp",
        {name: mName, address: mAddress, lat: mLatLang.A, lang: mLatLang.F, "type": "save"},
        function (data) {
            if (data === "added") {
            }
        }
    );
    //set marker to fixed
    Marker.setDraggable(false);
}