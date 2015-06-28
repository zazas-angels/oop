<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 6/28/15
  Time: 3:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <style>
    #map-canvas {
      width: 500px;
      height: 300px;
      display: none;
    }
  </style>
  <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js"></script>
  <script src="google-maps.js"></script>

  <script>
    function myFunction(){
      document.body.innerHTML = '<div id="map-canvas"></div>';
      googleMap();
    }
  </script>
</head>
<body>

<button onclick="myFunction();"></button>

</body>
</html>
