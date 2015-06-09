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
<script type="text/javascript">
	var numElements = 5;//need threading?
	function createImage() {

		numElements += 1;
		alert(1);
		document.body.innerHTML += "<div class=\"drsElement\" style=\"left: 150px; top: 280px; width: 350px; height: 150px; background: #DFC; text-align: center\"><form id="+numElements+"><input id=\"sampleFile1\" name=\"sampleFile\" type=\"file\" accept=\"image\" /> <br /> <input class=\"gobutton\" id=\"uploadBtn\" type=\"button\"	value=\"Upload\" onClick=\"performAjaxSubmit(0,"
				+ numElements
				+ ");\"></input></form> <div class=\"drsMoveHandle\" style=\"background: #DFC;\" id=\"image1\"><img alt=\"\" src=\"noImage.png \" style=\"width: 250px; height: 230px;\"></div></div>";
		alert(2);
	}

	function createText() {
		numElements += 1;
		alert(1);
		document.body.innerHTML += "<div class=\"drsElement\""
			+"	style=\"left: 50px; top: 150px; width: 350px; height: 90px; background: white; text-align: center\">"
				+ "	<div class=\"drsMoveHandle\">Text:</div>"
				+ "	<div style=\"border: 1px solid green; background-color: #A5B7F2;\">"
				+ "		Font: <input id=\"Font"
				+ numElements
				+ "\" size=\"5\" class=\"color\" colorType=\"font\""
				+ "	labelId=\""
				+ numElements
				+ "\" value=\"000000\"  autocomplete=\"off\" style=\"color: rgb(255, 255, 255); background-image: none; background-color: rgb(0, 0, 0);\"> Back: <input id=\"Back"
				+ numElements
				+ "\" size=\"5\""
				+ "	class=\"color\" colorType=\"back\" labelId=\""
				+ numElements
				+ "\" value=\"FFFFFF\"  autocomplete=\"off\" style=\"color: rgb(255, 255, 255); background-image: none; background-color: rgb(0, 0, 0);\">"
				+ "		Size:<input id=\"size"
				+ numElements
				+ "\" size=\"1\" value=\"18\" onkeydown=\"changeSize("
				+ numElements
				+ ");\""
				+ "			onpaste=\"changeSize("
				+ numElements
				+ ");\" oninput=\"changeSize("
				+ numElements
				+ ");\">"
				+ "	</div>"
				+ "	<div id=\""+numElements+"\">"
				+ "		<p contenteditable=\"true\">Your Text</p>"
				+ "</div>	</div>";

		alert(2);
	}
	function changeSize(id) {
		var element = document.getElementById(id);
		//alert(document.getElementById("size"+id).value);
		//alert("Zaza");
		var size = document.getElementById("size" + id).value;
		if (!isNaN(size) && size > 0)
			element.style.fontSize = size + "px";

	}
	function createGallery(){
		numElements += 1;
		alert(1);
		document.body.innerHTML += 
		'<div class="drsElement"'
		+'	style="left: 150px; top: 280px; width: 50px; height: 100px; background: #DFC; text-align: center">'
		+'		<div class="drsMoveHandle" style="background: #DFC">Slider</div>'

		+'		<form id="'+numElements+'">	'
		+'					<input id="sampleFile'+numElements+'" name="sampleFile" type="file" accept="image" />'
		+'			<br /> Image descr: <input id="imageText'+numElements+'" type="text" /> <input'
		+'				class="gobutton" id="uploadBtn" type="button" value="Upload"'
		+'				onClick="performAjaxSubmit(1,'+numElements+');"></input>'
		+'		</form>'
		+'		<div style="width: 550px; heigth: 300px;">'
		+'			<div id="slider'+numElements+'" class="slider">'
					
		+'				<img src="images/image-slider-3.jpg" />'
		+'				<img src="images/image-slider-1.jpg" />'
					
		+'			</div>'
		+'		</div>'
		+'	</div>';
	}
	function createCommentBox() {

		numElements += 1;
		alert(1);
		document.body.innerHTML += '<div class="drsElement"'
				+'style="left: 150px; top: 280px; width: 50px; height: 100px; background: #DFC; text-align: center">'

				+ 'zaza'
				+ '	<div class="drsMoveHandle" style="background: #DFC">'
				+ '		<form action="#" id="usrform">'
				+ '			Name: <input type="text" name="usrname"> <input disabled'
						+'			class="gobutton" type="submit" style="background: blue">'
				+ '	</form>'
				+ '	<br>'
				+ '		<textarea rows="4" cols="50" name="comment" form="usrform">'
				+ '		Enter text here...</textarea>'
				+ '		<br> <br>'
				+ '		<div class=rounded>'
				+ '	<p>'
				+ '			<a class="name 1"> name </a> comment 1'
				+ '		</p>'
				+ '	</div>'
				+ '	<br>'
				+ '	<div class=rounded>'
				+ '		<p>'
				+ '		<a class="name 2"> name </a> comment 2'
				+ '	</p>'
				+ '</div>' + '	</div>' + '	</div>';

		alert(2);
	}
	
	function uploadVideo(id){
		var link=document.getElementById("videoLink"+id).value;
		if(link.substring(0, 32)=="https://www.youtube.com/watch?v="){
			alert('<iframe width="560" height="315" src="https://www.youtube.com/embed/'+link.substring(32)
					+'" frameborder="0" allowfullscreen></iframe>');
			document.getElementById("video"+id).innerHTML=
				'<iframe width="560" height="315" src="https://www.youtube.com/embed/'+link.substring(32)
				+'" frameborder="0" allowfullscreen></iframe>';
		}else{
			document.getElementById("video"+id).innerHTML="error loading";
		
		}
			
		
		
		
	}
	
	function createVideo(){

		numElements+= 1;
		alert(1);
		document.body.innerHTML +=
		'<div class="drsElement"'
		+'	style="left: 150px; top: 280px; width: 560px; height: 350px; background: #DFC; text-align: center">'
		+'	<div class="drsMoveHandle" style="background: #DFC">Youtube Video</div>'
		+'	Youtube link: <input id="videoLink'+numElements+'" type="text" /> <input'
		+'			class="gobutton"  type="button" value="Upload Video"'
		+'			onClick="uploadVideo('+numElements+');"></input>'

		+'	<div id="video'+numElements+'"></div>'
		+'	</div>';

	}
</script>
<%--comment style --%>
<style type="text/css">
div.rounded {
	width: 255px;
	margin: 0;
	background: #3498db;
	background-image: -webkit-linear-gradient(top, #3498db, #2980b9);
	background-image: -moz-linear-gradient(top, #3498db, #2980b9);
	background-image: -ms-linear-gradient(top, #3498db, #2980b9);
	background-image: -o-linear-gradient(top, #3498db, #2980b9);
	background-image: linear-gradient(to bottom, #3498db, #2980b9);
	-webkit-border-radius: 17;
	-moz-border-radius: 17;
	border-radius: 17px;
	font-family: Arial;
	color: #ffffff;
	font-size: 20px;
	padding: 5px 10px 5px 10px;
	text-decoration: none;
}

a.name {
	-webkit-border-radius: 30;
	-moz-border-radius: 30;
	border-radius: 30px;
	font-family: Arial;
	color: #1100ff;
	font-size: 25px;
	background: #ffffff;
	padding: 15px 15px 15px 15px;
	text-decoration: none;
}

a.name:hover {
	background: #edf1f5;
	text-decoration: none;
}
</style>
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
<%--gallery --%>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="sss.min.js"></script>
<link rel="stylesheet" href="sss.css" type="text/css" media="all">


<script>
	jQuery(function($) {
		$('.slider').sss();
	});
</script>
</head>

<body>
	<%--text --%>
	<%--<div class="drsElement"
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


	</div> --%>










	<%--control panel --%>
	<div class="controlPanel">

		<div id="circle">
			<i class="icon1 fa fa-pencil fa-lg"></i> <i
				class="icon2 fa fa-star fa-lg"></i> <span> ADD</span>
		</div>
		<div id="sub">
			<div onclick="createText();" id="circle">
				<i class="icon1 fa fa-star fa-lg"></i> <i
					class="icon2 fa fa-plus fa-lg"></i><span>Text</span>
			</div>
			<div onclick="createImage();" id="circle">
				<i class="icon1 fa fa-star fa-lg"></i> <i
					class="icon2 fa fa-plus fa-lg"></i> <span>Image</span>
			</div>
			<div onclick="createCommentBox();" id="circle">
				<i class="icon1 fa fa-star fa-lg"></i> <i
					class="icon2 fa fa-plus fa-lg"></i> <span>Comments Box</span>
			</div>
			
				<div onclick="createGallery();" id="circle">
				<i class="icon1 fa fa-star fa-lg"></i> <i
					class="icon2 fa fa-plus fa-lg"></i> <span>Slider Gallery</span>
			</div>
			<div onclick="createVideo();" id="circle">
				<i class="icon1 fa fa-star fa-lg"></i> <i
					class="icon2 fa fa-plus fa-lg"></i> <span>Youtube Video</span>
			</div>
			
			
		</div>



	</div>
	<%--image --%>
	<%--
	<div class="drsElement "
		style="left: 150px; top: 280px; width: 350px; height: 150px; background: #DFC; text-align: center">



		<form id="1">

			<input id="sampleFile1" name="sampleFile" type="file" accept="image" />
			<br /> <input class="gobutton" id="uploadBtn" type="button"
				value="Upload" onClick="performAjaxSubmit(0,1);"></input>

		</form>





		<div class="drsMoveHandle " style="background: #DFC;" id="image1">
			<img alt="" src="noImage.png " style="width: 250px; height: 230px;">
		</div>

	</div>
 --%>




	<script type="text/javascript">
		function performAjaxSubmit(multi, id) {

			var sampleFile = document.getElementById("sampleFile" + id).files[0];

			var formdata = new FormData();

			var image;
			var slider;
			if (multi == 0) {
				image = document.getElementById("image" + id);
			} else {
				slider = document.getElementById("slider" + id);
			}

			formdata.append("sampleFile", sampleFile);

			if (window.XMLHttpRequest) {
				// code for IE7+, Firefox, Chrome, Opera, Safari
				xhr = new XMLHttpRequest();
			} else {
				// code for IE6, IE5
				xhr = new ActiveXObject("Microsoft.XMLHTTP");
			}

			xhr.open("POST", "FileUploader", true);

			xhr.send(formdata);

			xhr.onload = function(e) {

				if (this.status == 200) {
					if (multi == 0) {
						image.innerHTML = this.responseText;
					} else {
						var text = document.getElementById("imageText"+id);
						alert(text.value);
						alert("qeia+");
						var additionalText = "";
						if(text.value!=""){
							alert("no");
							additionalText='<span class="caption">'+text.value+'</span>';
						}
						slider.innerHTML +='<div>'+ this.responseText+ additionalText+'</div>';
						
					}
					alert(this.responseText);

				}

			};

		}
	</script>

	<%--comment box --%>
	<%-- 

	<div class="drsElement"
		style="left: 150px; top: 280px; width: 50px; height: 100px; background: #DFC; text-align: center">

		zaza
		<div class="drsMoveHandle" style="background: #DFC">
			<form action="#" id="usrform">
				Name: <input type="text" name="usrname"> <input disabled
					class="gobutton" type="submit" style="background: blue">
			</form>
			<br>
			<textarea rows="4" cols="50" name="comment" form="usrform">
Enter text here...</textarea>
			<br> <br>
			<div class=rounded>
				<p>
					<a class="name"> name </a> comment 1
				</p>
			</div>
			<br>
			<div class=rounded>
				<p>
					<a class="name"> name </a> comment 1
				</p>
			</div>

		</div>
	</div>
--%>

	<%-- gallery --%>

	<div class="drsElement"
		style="left: 150px; top: 280px; width: 50px; height: 100px; background: #DFC; text-align: center">
		<div class="drsMoveHandle" style="background: #DFC">Slider</div>

		<form id="1">

			<input id="sampleFile1" name="sampleFile" type="file" accept="image" />
			<br /> Image descr: <input id="imageText1" type="text" /> <input
				class="gobutton" id="uploadBtn" type="button" value="Upload"
				onClick="performAjaxSubmit(1,1);"></input>
		</form>
		<div style="width: 550px; heigth: 300px;">
			<div id="slider1" class="slider">
				
				<img src="images/image-slider-3.jpg" />
				
			</div>
		</div>
	</div>

<%--video --%>
<%-- 
	<div class="drsElement"
		style="left: 150px; top: 280px; width: 560px; height: 350px; background: #DFC; text-align: center">
		<div class="drsMoveHandle" style="background: #DFC">Youtube Video</div>
Youtube link: <input id="videoLink1" type="text" /> <input
				class="gobutton"  type="button" value="Upload Video"
				onClick="uploadVideo(1);"></input>

		<div id="video1"></div>
</div>
--%>



</body>
</html>