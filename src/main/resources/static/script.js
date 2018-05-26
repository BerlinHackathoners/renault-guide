var map;
var directionsService;
var marker = [];
var polyLine = [];
var poly2 = [];
var startLocation = [];
var endLocation = [];
var timerHandle = [];
var infoWindow = null;

var startLoc = [];
var endLoc = [];

var lastVertex = 1;
var step = 5; // 5; // metres
var eol = [];

var lastPosition = 0;

window.initialize = initialize;
window.setRoutes = setRoutes;

// Called on body load
function initialize() {

    // Initialize infoWindow
    infoWindow = new google.maps.InfoWindow({
        size: new google.maps.Size(150, 50)
    });
    var options = {
        // Max zoom
        zoom: 18
    };
    map = new google.maps.Map(document.getElementById("map_canvas"), options);

    // Initial location which loads up on map
    address = 'Paris'

    // Geocoder is used to encode or actually geocode textual addresses to lat long values
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({'address': address}, function (results, status) {
        map.fitBounds(results[0].geometry.viewport);
    });
}

// Returns the marker
function createMarker(latlng, label, html) {
    var contentString = '<b>' + label + '</b><br>' + html;
    var car = "M17.402,0H5.643C2.526,0,0,3.467,0,6.584v34.804c0,3.116,2.526,5.644,5.643,5.644h11.759c3.116,0,5.644-2.527,5.644-5.644 V6.584C23.044,3.467,20.518,0,17.402,0z M22.057,14.188v11.665l-2.729,0.351v-4.806L22.057,14.188z M20.625,10.773 c-1.016,3.9-2.219,8.51-2.219,8.51H4.638l-2.222-8.51C2.417,10.773,11.3,7.755,20.625,10.773z M3.748,21.713v4.492l-2.73-0.349 V14.502L3.748,21.713z M1.018,37.938V27.579l2.73,0.343v8.196L1.018,37.938z M2.575,40.882l2.218-3.336h13.771l2.219,3.336H2.575z M19.328,35.805v-7.872l2.729-0.355v10.048L19.328,35.805z";
    // Using Marker API, marker is created
    var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        title: label,
        zIndex: 10,
		icon: {
			path: car,
			anchor: new google.maps.Point(10, 25) // orig 10,50 back of car, 10,0 front of car, 10,25 center of car
			scale: .7,
			strokeColor: 'white',
			strokeWeight: .10,
			fillOpacity: 1,
			fillColor: '#404040',
			offset: '5%',
		};
//        icon: {
//              url: "https://image.ibb.co/gxU3rT/zoe_marker.png",
//              size: new google.maps.Size(32, 48),
//              origin: new google.maps.Point(0, 0),
//              anchor: new google.maps.Point(16, 24),
////              scaledSize: new google.maps.Size(25, 25)
//        }
    });
    marker.myname = label;
    // Adding click listener to open up info window when marker is clicked
    google.maps.event.addListener(marker, 'click', function () {
        infoWindow.setContent(contentString);
        infoWindow.open(map, marker);
    });
    return marker;
}

function toggleError(msg){
    document.getElementById('error-msg').innerText = msg;
}

// Using Directions Service find the route between the starting and ending points
function setRoutes() {
    map && initialize();
    // Empty out the error msg
    toggleError("");
    // Set the values and check if any is empty, and if yes, show error and return
    var startVal = document.getElementById("start").value
    var endVal = document.getElementById("end").value;
    if (!startVal || !endVal){
        toggleError("Please enter both start and end locations.");
        return;
    }
    // Just to avoid weird case of same start and end location
    if (startVal === endVal){
        toggleError("Please enter different locations in both inputs");
        return;
    }
    startLoc[0] = startVal;
    endLoc[0] = endVal;
    
    // Empty out previous values
    startLocation = [];
    endLocation = [];
    polyLine = [];
    poly2 = [];
    timerHandle = [];

    var directionsDisplay = new Array();
    for (var i = 0; i < startLoc.length; i++) {
        var rendererOptions = {
            map: map,
            suppressMarkers: true,
            preserveViewport: true
        };
        directionsService = new google.maps.DirectionsService();
        var travelMode = google.maps.DirectionsTravelMode.DRIVING;
        var request = {
            origin: startLoc[i],
            destination: endLoc[i],
            travelMode: travelMode
        };
        directionsService.route(request, makeRouteCallback(i, directionsDisplay[i]), rendererOptions);
    }
}

// called after getting route from directions service, does all the heavylifting
function makeRouteCallback(routeNum, disp, rendererOptions) {
    // check if polyline and map exists, if yes, no need to do anything else, just start the animation
    if (polyLine[routeNum] && (polyLine[routeNum].getMap() != null)) {
        startAnimation(routeNum);
        return;
    }
    return function (response, status) {
        // If directions service successfully returns and no polylines exist already, then do the following
        if (status == google.maps.DirectionsStatus.ZERO_RESULTS){
            toggleError("No routes available for selected locations");
            return;
        }
        if (status == google.maps.DirectionsStatus.OK) {
            startLocation[routeNum] = new Object();
            endLocation[routeNum] = new Object();
            // Set up polyline for current route
            polyLine[routeNum] = new google.maps.Polyline({
                path: [],
                strokeColor: '#FFFF00',
                strokeWeight: 3
            });
            poly2[routeNum] = new google.maps.Polyline({
                path: [],
                strokeColor: '#FFFF00',
                strokeWeight: 3
            });
            // For each route, display summary information.
            var legs = response.routes[0].legs;
            // directionsrenderer renders the directions obtained previously by the directions service
            disp = new google.maps.DirectionsRenderer(rendererOptions);
            disp.setMap(map);
            disp.setDirections(response);

            // create Markers
            for (i = 0; i < legs.length; i++) {
                // for first marker only
                if (i == 0) {
                    startLocation[routeNum].latlng = legs[i].start_location;
                    startLocation[routeNum].address = legs[i].start_address;
                    marker[routeNum] = createMarker(legs[i].start_location, "start", legs[i].start_address, "green");
                }
                endLocation[routeNum].latlng = legs[i].end_location;
                endLocation[routeNum].address = legs[i].end_address;
                var steps = legs[i].steps;
                for (j = 0; j < steps.length; j++) {
                    var nextSegment = steps[j].path;
                    for (k = 0; k < nextSegment.length; k++) {
                        polyLine[routeNum].getPath().push(nextSegment[k]);
                    }
                }
            }
        }
        if (polyLine[routeNum]){
            // render the line to map
            polyLine[routeNum].setMap(map);
            // and start animation
            startAnimation(routeNum);
        }
    }
}

// Spawn a new polyLine every 20 vertices
function updatePoly(i, d) {
    if (poly2[i].getPath().getLength() > 20) {
        poly2[i] = new google.maps.Polyline([polyLine[i].getPath().getAt(lastVertex - 1)]);
    }

    if (polyLine[i].GetIndexAtDistance(d) < lastVertex + 2) {
        if (poly2[i].getPath().getLength() > 1) {
            poly2[i].getPath().removeAt(poly2[i].getPath().getLength() - 1)
        }
        poly2[i].getPath().insertAt(poly2[i].getPath().getLength(), polyLine[i].GetPointAtDistance(d));
    } else {
        poly2[i].getPath().insertAt(poly2[i].getPath().getLength(), endLocation[i].latlng);
    }
}

// Updates marker position to make the animation and update the polyline
function animate(index, d, tick) {
    if (d > eol[index]) {
        marker[index].setPosition(endLocation[index].latlng);
        return;
    }
    var p = polyLine[index].GetPointAtDistance(d);
    marker[index].setPosition(p);
    var heading = google.maps.geometry.spherical.computeHeading(lastPosition, p);
    marker[index].icon.rotation = heading;
    updatePoly(index, d);
    timerHandle[index] = setTimeout("animate(" + index + "," + (d + step) + ")", tick || 100);
}

// Start marker movement by updating marker position every 100 milliseconds i.e. tick value
function startAnimation(index) {
    if (timerHandle[index]) 
        clearTimeout(timerHandle[index]);
    eol[index] = polyLine[index].Distance();
    map.setCenter(polyLine[index].getPath().getAt(0));

    poly2[index] = new google.maps.Polyline({
        path: [polyLine[index].getPath().getAt(0)],
        strokeColor: "#FFFF00",
        strokeWeight: 3
    });
    timerHandle[index] = setTimeout("animate(" + index + ",50)", 2000);  // Allow time for the initial map display
}
