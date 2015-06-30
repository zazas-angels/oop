
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*, core.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
</head>
<body>
<%!
String getComment(java.util.Date date) {
String comment = "";
switch(date.getMonth()) {
case 0: case 1:
comment = "very cold"; break;
case 2: case 3: case 4:
comment = "getting warmer"; break;
case 5: case 6: case 7:
comment = "summer!"; break;
case 8:
comment = "school time"; break;
case 9:
comment = "getting colder"; break;
case 10:
comment = "Thanksgiving"; break;
case 11:
comment = "break time"; break;
};
return comment;
}
%>
<%
Date now = new Date();
out.println("It is " + getComment(now));
%>
</body>
</html>
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

