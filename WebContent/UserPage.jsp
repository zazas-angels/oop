<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- Author: glaba13 --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Page</title>

<script type="text/javascript" src="dragresize.js"></script>
<link rel="stylesheet" type="text/css" href="DragResizeStyle.css">

<link rel="stylesheet" type="text/css" href="UserControlButton.css">
<style type="text/css">
</style>

<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Comfortaa'
	rel='stylesheet' type='text/css'>

<script>
	function upload() {
	alert("ha");
		var xmlHttp;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlHttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				alert("dastuldai");
			}
		};
	var file = document.getElementById("uploadfile");

		/* Create a FormData instance */
		var formData = new FormData();
		/* Add the file */
		formData.append("file", file.files[0]);

		xmlHttp.open("post", "FileUploadHandler", true);
		xmlHttp.setRequestHeader("Content-Type", "multipart/form-data");
		xmlHttp.send(formData); /* Send to server */ 
/* 		xmlHttp.open("POST", "FileUploadHandler", true);
		xmlHttp.send(); */
		alert("zaza");

	}
</script>
<script type="text/javascript">
	function changeSize(id) {
		var element = document.getElementById(id);
		//alert(document.getElementById("size"+id).value);
		//alert("Zaza");
		var size = document.getElementById("size" + id).value;
		if (!isNaN(size) && size > 0)
			element.style.fontSize = size + "px";

	}
</script>
<%--uploader style --%>
<link rel="stylesheet" type="text/css" href="Uploader.css">
<script type="text/javascript">
	//<![CDATA[

	// Using DragResize is simple!
	// You first declare a new DragResize() object, passing its own name and an object
	// whose keys constitute optional parameters/settings:

	var dragresize = new DragResize('dragresize', {
		minWidth : 50,
		minHeight : 50,
		minLeft : 20,
		minTop : 20,
		maxLeft : 600,
		maxTop : 600
	});

	// Optional settings/properties of the DragResize object are:
	//  enabled: Toggle whether the object is active.
	//  handles[]: An array of drag handles to use (see the .JS file).
	//  minWidth, minHeight: Minimum size to which elements are resized (in pixels).
	//  minLeft, maxLeft, minTop, maxTop: Bounding box (in pixels).

	// Next, you must define two functions, isElement and isHandle. These are passed
	// a given DOM element, and must "return true" if the element in question is a
	// draggable element or draggable handle. Here, I'm checking for the CSS classname
	// of the elements, but you have have any combination of conditions you like:

	dragresize.isElement = function(elm) {
		if (elm.className && elm.className.indexOf('drsElement') > -1)
			return true;
	};
	dragresize.isHandle = function(elm) {
		if (elm.className && elm.className.indexOf('drsMoveHandle') > -1)
			return true;
	};

	// You can define optional functions that are called as elements are dragged/resized.
	// Some are passed true if the source event was a resize, or false if it's a drag.
	// The focus/blur events are called as handles are added/removed from an object,
	// and the others are called as users drag, move and release the object's handles.
	// You might use these to examine the properties of the DragResize object to sync
	// other page elements, etc.

	dragresize.ondragfocus = function() {
	};
	dragresize.ondragstart = function(isResize) {
	};
	dragresize.ondragmove = function(isResize) {
	};
	dragresize.ondragend = function(isResize) {
	};
	dragresize.ondragblur = function() {
	};

	// Finally, you must apply() your DragResize object to a DOM node; all children of this
	// node will then be made draggable. Here, I'm applying to the entire document.
	dragresize.apply(document);

	//]]>
</script>
<%--color picker --%>
<script type="text/javascript" src="jscolor.js"></script>
</head>

<body>
	<%--text --%>
	<div class="drsElement"
		style="left: 50px; top: 150px; width: 350px; height: 90px; background: white; text-align: center">
		<div class="drsMoveHandle">Text:</div>
		<div style="border: 1px solid green; background-color: #A5B7F2;">
			Font: <input id="zaz" size="5" class="color" colorType="font"
				labelId="7" value="000000"> Back: <input id="zaz" size="5"
				class="color" colorType="back" labelId="7" value="FFFFFF">
			Size:<input id="size7" size="1" value="18" onkeydown="changeSize(7);"
				onpaste="changeSize(7);" oninput="changeSize(7);">
		</div>
		<div id="7">
			<p contenteditable="true">opaa</p>
		</div>


	</div>
	<%--control panel --%>
	<div class="controlPanel">

		<div id="circle">
			<i class="icon1 fa fa-pencil fa-lg"></i> <i
				class="icon2 fa fa-star fa-lg"></i> <span> ADD</span>
		</div>
		<div id="sub">
			<div id="circle">
				<i class="icon1 fa fa-star fa-lg"></i> <i
					class="icon2 fa fa-plus fa-lg"></i> <span>Text</span>
			</div>
			<div id="circle">
				<i class="icon1 fa fa-star fa-lg"></i> <i
					class="icon2 fa fa-plus fa-lg"></i> <span>Two</span>
			</div>
			<div id="circle">
				<i class="icon1 fa fa-star fa-lg"></i> <i
					class="icon2 fa fa-plus fa-lg"></i> <span>Three</span>
			</div>
		</div>



	</div>
	<%--image --%>

	<div class="drsElement "
		style="left: 150px; top: 280px; width: 350px; height: 150px; background: #DFC; text-align: center">
		

			
	<form id="form1">    

    <label for="sampleFile">Please select a file </label>

    <input id="sampleFile" name="sampleFile" type="file" /> <br/>

    <input id="uploadBtn" type="button" value="Ajax Submit" onClick="performAjaxSubmit();"></input>

</form>

            



		<div class="drsMoveHandle " style="background: #DFC;">
			<img alt="" src="noImage.png " style="width: 250px; height: 230px;">
		</div>

	</div>


	<input type="file" id="uploadfile" name="uploadfile" />
	<input type="button" value="upload" onclick="upload()" />

	
	
	
	
<script type="text/javascript">

    function performAjaxSubmit() {

      
        var sampleFile = document.getElementById("sampleFile").files[0];

        var formdata = new FormData();


        formdata.append("sampleFile", sampleFile);

        var xhr = new XMLHttpRequest();       

        xhr.open("POST","FileUploader", true);

        xhr.send(formdata);

        xhr.onload = function(e) {

            if (this.status == 200) {

               alert(this.responseText);

            }

        };                    

    }   

</script>
	
	
</body>
</html>