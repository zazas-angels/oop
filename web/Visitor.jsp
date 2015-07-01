<%
    request.getSession().setAttribute("userId", request.getParameter("id"));
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Visitor Page</title>

    <style>
        #map-canvas {
            width: 500px;
            height: 300px;
            float: right; !important;
            position: absolute; !important;
            bottom: 10px;
            right: 10px;
            display: none;
        }
        #googleMap-button {
            position: absolute; !important;
            bottom: 10px;
            right: 10px;
            z-index: 999;
        }
    </style>
    <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js"></script>
    <script
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript" src="dragresize1.js"></script>

    <link rel="stylesheet" type="text/css" href="DragResizeStyle.css">
    <link rel="stylesheet" type="text/css" href="CloseButton.css">

    <link rel="stylesheet" type="text/css" href="gallery.css">
    <link rel="stylesheet" type="text/css" href="body.css">
    <link rel="stylesheet" type="text/css" href="commentStyle.css">
    <link rel="stylesheet" type="text/css" href="rating.css">
    <link rel="stylesheet" type="text/css" href="AlbomImage.css">
    <link rel="stylesheet" type="text/css" href="ChatBox.css">
    <link rel="stylesheet"
          href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="UserControlButton.css">
    <style type="text/css">
    </style>

    <link rel="stylesheet"
          href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link href='http://fonts.googleapis.com/css?family=Comfortaa'
          rel='stylesheet' type='text/css'>

    <script type="text/javascript" src="slider.js"></script>
    <script type="text/javascript">
        var numElements = 33;//Jesus <3
        var viewMode = false;
        window.onload = function () {
            //alert(1);
            $.post("UserPageData", {
                data: "",
                id:<%= request.getParameter("id")%>,
                view: 2
            }, function (result) {
                //alert(result);
                document.body.innerHTML = result;
                numElements += document.getElementsByTagName("div").length;
                $("#map-canvas").remove();
                document.body.innerHTML += '<div id="map-canvas"></div>';
                view();
                document.getElementById("edit").style.visibility = "hidden";
                var elements = document.getElementsByTagName("textArea");
                for (var i = 0; i < elements.length; i++) {
                    var element = elements[i];
                    if (element.className != "comment") {
                        element.value = element.getAttribute("val");
                    }

                }
                var elements = document.getElementsByTagName("select");
                for (var i = 0; i < elements.length; i++) {
                    var element = elements[i];
                    element.value = element.getAttribute("val");
                    changeBackground();
                }
                googleMap();
                addMarkers();

                $("#map-canvas").toggle();
            });
        };
        function save() {
            //alert(0);

            var elements = document.getElementsByTagName("input");
            for (var i = 0; i < elements.length; i++) {
                var element = elements[i];
                if (element.className != "comment") {
                    element.setAttribute("val", element.value);
                }
            }
            elements = document.getElementsByTagName("textarea");
            for (var i = 0; i < elements.length; i++) {
                var element = elements[i];
                if (element.className != "comment") {
                    element.setAttribute("val", element.value);
                }
            }

            elements = document.getElementsByTagName("select");
            for (var i = 0; i < elements.length; i++) {
                var element = elements[i];
                element.setAttribute("val", element.value);
            }
            document.getElementById("control").style.visibility = "hidden";
            var wasVisible = document.getElementById("control").style.visibility == "visible";
            document.getElementById("themeselect").style.visibility = "hidden";
            document.getElementById("edit").style.visibility = "hidden";
            document.getElementById("addSub").style.visibility = "hidden";
            var txt = document.body.innerHTML;
            //alert(txt);
            $.post("UserPageData", {
                data: txt,
                id:<%= request.getParameter("id")%>,
                view: 0
            }, function (result) {
                //alert(1);
            });
            if (wasVisible)
                document.getElementById("control").style.visibility = "visible";

        }
    </script>

    <%--upload --%>
    <script type="text/javascript">

    </script>
    <%--uploader style --%>
    <link rel="stylesheet" type="text/css" href="Uploader.css">

    <%--color picker --%>
    <script type="text/javascript" src="jscolor.js"></script>
    <%--gallery --%>

    <script
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="sss.js"></script>
    <link rel="stylesheet" href="sss.css" type="text/css" media="all">
    <script type="text/javascript" src="ControlFunctions.js"></script>


    <script>

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

                initialized = true;
            }
        }

        // Zoom to 12 when clicking on marker
        function addInfo(marker, contentString, closeInfo) {
            var infowindow = new google.maps.InfoWindow();
            infowindow.setContent(contentString[0])
            if (!closeInfo) {
                infowindow.open(map, marker);
            }
            google.maps.event.addListener(marker, 'click', function () {
                infowindow.open(map, marker);
            });
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
                                        '<tr><td><label>'+mark.name+'</label></td></tr>' +
                                        '<tr><td><label for="pDesc"><span>აღწერა :</span></td></tr>' +
                                        '<tr><td><label maxlength="150">' + mark.desc + '</label></td></tr>' +
                                        '</table>' +
                                        '</form>' +
                                        '</div>');
                                addInfo(marker, contentString, true);
                            }
                        }
                    }
            );
        }

    </script>
    <script type="text/javascript">
	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		ga('create', 'UA-64580083-1', {
		  'cookieDomain': 'none'
		});  
		ga('send', 'pageview', {
		  'page': '/htmltest.html',
		  'hitCallback': function() {
		    alert('analytics.js done sending data');
		  }
		});
	</script>
</head>
<body>

<input id="googleMap-button" val="google-map"  type="button" onclick="googleMap()" value="google-map">
</body>
</html>
