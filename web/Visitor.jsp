<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Page</title>
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
	window.onload = function() {
		alert( 1);
		$.post("UserPageData", {
			data : "",
			id:<%= request.getParameter("id")%>,
			view : 2
		}, function(result) {
			//alert(result);
			document.body.innerHTML = result;
			numElements += document.getElementsByTagName("div").length;
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

		});
	};
	function save() {
		alert(0);

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
		var wasVisible = document.getElementById("control").style.visibility =="visible" ;
		document.getElementById("themeselect").style.visibility = "hidden";
		document.getElementById("edit").style.visibility = "hidden";
		document.getElementById("addSub").style.visibility = "hidden";
		var txt = document.body.innerHTML;
		alert(txt);
		$.post("UserPageData", {
			data : txt,
			id:<%= request.getParameter("id")%>,
			view : 0
		}, function(result) {
			alert(1);
		});
		if(wasVisible)
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





</head>
<body>

</body>
</html>