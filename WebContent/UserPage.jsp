<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- Author: glaba13 --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Page</title>

<script type="text/javascript" src="dragresize1.js"></script>
<link rel="stylesheet" type="text/css" href="DragResizeStyle.css">
<link rel="stylesheet" type="text/css" href="CloseButton.css">

<link rel="stylesheet" type="text/css" href="gallery.css">
<link rel="stylesheet" type="text/css" href="AlbomImage.css">
<link rel="stylesheet" type="text/css" href="ChatBox.css">
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="UserControlButton.css">
<style type="text/css">
</style>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Comfortaa'
	rel='stylesheet' type='text/css'>
<script type="text/javascript">
	var numElements = 23;//need threading?
	function createImage() {

		numElements += 1;
		alert(1);
		var div = document.createElement('div');
		div.setAttribute("id", "element" + numElements);
		div.innerHTML += '<div  class="drsElement"'
		+'		style="left: 150px; position: absolute; top: 280px; width: 350px; height: 150px; min-width: 270px; background: #DFC; text-align: center">'
				+ '		<form id="'+numElements+'">'
				+ '					<input id="sampleFile'+numElements+'" name="sampleFile" type="file" accept="image/gif,image/jpeg,image/jpg,,image/png" />'
				+ ' <a onclick="$('
				+ "element"
				+ numElements
				+ ').remove();" class="close" style="float: right;">×</a>'
				+ '			<br /> <input class="gobutton" id="uploadBtn" type="button"'
				+ '			value="Upload" onClick="performAjaxSubmit(0,'
				+ numElements
				+ ');"></input>'
				+ '				</form>'
				+ '<innerElement>	<div class="drsMoveHandle" style="background: #DFC;width:100%; height:calc(100% - 50px);" id="image'
				+ numElements
				+ '"  >'
				+ '		<img alt="noImage" src="noImage.png " style="width: 100%;height:100%;">'
				+ '	</div> </innerElement>' + '		</div>';
		document.body.appendChild(div);
		alert(2);
	}

	function createText() {
		numElements += 1;
		alert(1);
		var div = document.createElement('div');
		div.setAttribute("id", "element" + numElements);
		div.innerHTML = "<div class=\"drsElement\""
			+"	style=\"left: 50px; position: absolute; top: 150px; width: 350px; height: 120px; min-width:228px; min-height:88px; background: white; text-align: center\">"
				+ '<div class="drsMoveHandle">Text'
				+ ' <a onclick="$('
				+ "element"
				+ numElements
				+ ').remove();" class="close" style="float: right;">×</a>'
				+ "</div>"
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
				+ "<br>		Size:<input id=\"size"
				+ numElements
				+ "\" size=\"1\" value=\"18\" onkeydown=\"changeSize("
				+ numElements
				+ ");\""
				+ "			onpaste=\"changeSize("
				+ numElements
				+ ");\" oninput=\"changeSize("
				+ numElements
				+ ");\">"
				+ 'Bold: <input type="checkbox" id="bold'
				+ numElements
				+ '" onclick="changeBold('
				+ numElements
				+ ');">'
				+ "	</div>"
				+ '<innerElement> <textarea id="'
				+ numElements
				+ '"style=" text-align: center; width: calc(100% - 6px); height: calc(100% - 74px); resize:none;">'
				+ 'Your Text' + '</textarea> </innerElement>' + '</div>"';
		document.body.appendChild(div);
		jscolor.init();
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
	function createGallery() {
		numElements += 1;
		alert(1);
		var div = document.createElement('div');
		div.setAttribute("id", "element" + numElements);
		div.innerHTML += '<div class="drsElement"'
	+'	style="left: 150px; top: 280px; position: absolute; width: 500px; height: 300px; min-width:209px; min-height:167px; background: #DFC; text-align: center">'
				+ '	<div class="drsMoveHandle" style="background: #DFC">Slider'
				+ ' <a onclick="$('
				+ "element"
				+ numElements
				+ ').remove();" class="close" style="float: right;">×</a>'
				+ '</div>'

				+ '		<form id="'+numElements+'">'

				+ '	<input id="sampleFile'+numElements+'" name="sampleFile" type="file" accept="image/gif,image/jpeg,image/jpg,,image/png" /><input'
				+ '		class="gobutton" id="uploadBtn" type="button" value="Upload"'
				+ '		onClick="performAjaxSubmit(1,'
				+ numElements
				+ ');"></input> <br /> <select'
	+'		id="select'+numElements+'">'
				+ '	</select> <input class="gobutton" type="button" value="Delete"'
				+ '		onClick="deleteSelected('
				+ numElements
				+ ');"></input>'
				+ '</form>'
				+ '<innerElement><div class="slideshow"'
				+ '	style="width: calc(100% - 5px); height: calc(100% - 110px);">'
				+ '	<ul id="slider'+numElements+'">'

				+ '			</ul>' + '</div>' + '</innerElement></div>';

		document.body.appendChild(div);
	}

	function createCommentBox() {

		numElements += 1;
		alert(1);
		var div = document.createElement('div');
		div.setAttribute("id", "element" + numElements);
		div.innerHTML += ' <div class="drsElement"'
			+'		style="left: 150px; position: absolute;top: 280px; width: 500px; height: 350px; min-width:217px; min-height:281px;  background: #DFC; text-align: center">'
				+ '		<div class="drsMoveHandle">Comment Box'
				+ ' <a onclick="$('
				+ "element"
				+ numElements
				+ ').remove();" class="close" style="float: right;">×</a>'
				+ '</div>'
				+ '	<innerElement><form action="#" id="usrform" style="background: #DFC; width: 100%; height: 20%;">'
				+ '	Name: <input type="text" name="usrname"> <input disabled'
				+'	class="gobutton"  value="post" type="submit" style="background: blue">'
				+ '	</form>'
				+ '	<textarea rows="4" cols="50"'
				+ 'style="width: calc(100% - 5px); height: 20%;" name="comment"'
				+ 'form="usrform">'
				+ 'Enter text here...</textarea>'
				+ '	<br> <br>'
				+ '	<div class="chatBox" style="width: calc(100% - 70px); height:calc(60% - 70px);" >'
				+ '<p class="bubbleLeft2"><a class="name">name</a> simple comment 1</p>'
				+ ' 	<p class="bubbleLeft2"><a class="name">name</a>simple comment 2</p>'
				+ '	</div>	<innerElement>' + '</div>';
		document.body.appendChild(div);
		alert(2);
	}

	function uploadVideo(id) {
		var link = document.getElementById("videoLink" + id).value;
		if (link.substring(0, 32) == "https://www.youtube.com/watch?v=") {
			alert('<iframe width="560" height="315" src="https://www.youtube.com/embed/'
					+ link.substring(32)
					+ '" frameborder="0" allowfullscreen></iframe>');
			document.getElementById("video" + id).innerHTML = '<iframe width="100%" height="100%" src="https://www.youtube.com/embed/'
					+ link.substring(32)
					+ '" frameborder="0" allowfullscreen></iframe>';
		} else {
			document.getElementById("video" + id).innerHTML = "error loading";

		}

	}
function changeSubVisibility(){
	var elem = document.getElementById("addSub");
	if(elem.style.visibility=="visible"){
		elem.style.visibility="hidden";
	}else{
		elem.style.visibility="visible";
	}
}
	function createVideo() {

		numElements += 1;
		alert(1);
		var div = document.createElement('div');
		div.setAttribute("id", "element" + numElements);
		div.innerHTML += '<div type="video" class="drsElement"'
		+'	style="left: 150px; top: 280px; position: absolute; width: 560px; height: 350px; min-width:272px; min-height:131px;background: red; text-align: center">'
				+ '<opac>	<div class="drsMoveHandle" style="background: rgb(138, 237, 138);">Youtube Video'
				+ ' <a onclick="$('
				+ "element"
				+ numElements
				+ ').remove();" class="close" style="float: right;">×</a>'
				+ '</div></opac>'
				+ '	Youtube link: <input id="videoLink'+numElements+'" type="text" /> <br><input'
				+ '			class="gobutton"  type="button" value="Upload Video"'
				+ '			onClick="uploadVideo('
				+ numElements
				+ ');"></input>'

				+ '	<innerElement><div id="video'
				+ numElements
				+ '" style="background:red; width:100%;height:calc(100% - 75px);"></div></innerElement>'
				+ '	</div>';
		document.body.appendChild(div);

	}

	function changeBold(id) {
		var isChecked = document.getElementById("bold" + id).checked;
		if (isChecked) {
			document.getElementById(id).style.fontWeight = "Bold";
		} else {
			document.getElementById(id).style.fontWeight = "normal";
		}
	}
</script>
<%--comment style --%>
<style type="text/css">
div.rounded {
	width: 100%;
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
	margin: 0;
}

a.name {
	-webkit-border-radius: 30;
	-moz-border-radius: 30;
	border-radius: 30px;
	font-family: Arial;
	color: #1100ff;
	font-size: 25px;
	background: #ffffff;
	padding: 3px 3px 3px 3px;
	text-decoration: none;
}

a.name:hover {
	background: #edf1f5;
	text-decoration: none;
}
</style>
<%--upload --%>
<script type="text/javascript">
	function save() {
		alert(0);
		var txt = document.body.innerHTML;

		$.post("UserPageData", {
			data : txt,
			view : 0
		}, function(result) {
			alert(1);
		});
	}
	function view() {
		alert(0);
		var txt = document.body.innerHTML;

		$
				.post(
						"UserPageData",
						{
							data : txt,
							view : 1
						},
						function(result) {
							alert(result);

							var elements = document.getElementsByTagName("div");
							alert("len: " + elements.length)
							for (var i = 0; i < elements.length; i++) {
								var element = elements[i];
								//var innerEl = element.getElementsByTagName("innerElement");
								alert(element.className)
								if (element.className == "drsMoveHandle") {
									element.className = "dummyHand";
								}
								if (element.className == "drsElement") {
									alert("opa");
									element.style.visibility = "hidden";
									//innerEl.style.visible="visible";
									element
											.getElementsByTagName("innerElement")[0].style.visibility = "visible";
									element.className = "dummyElem";
								}
							}
							elements = document.getElementsByTagName("opac");
							for (var i = 0; i < elements.length; i++) {
								var element = elements[i];
								element.style.visibility = "hidden";
							}
							document.getElementById("control").style.visibility = "hidden";
							document.getElementById("edit").style.visibility = "visible";
						});
	}

	function makeEdition() {
		alert(0);
		document.getElementById("control").style.visibility = "visible";
		document.getElementById("edit").style.visibility = "hidden";
		var elements = document.getElementsByTagName("div");
		alert("len: " + elements.length)
		for (var i = 0; i < elements.length; i++) {
			var element = elements[i];
			alert(element.className)
			if (element.className == "dummyHand") {
				element.className = "drsMoveHandle";
			}
			if (element.className == "dummyElem") {
				element.className = "drsElement";
			}
		}
		elements = document.getElementsByTagName("opac");
		for (var i = 0; i < elements.length; i++) {
			var element = elements[i];
			element.style.visibility = "visible";
		}
		jscolor.init();

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
		minHeight : 50
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
<script src="sss.js"></script>
<link rel="stylesheet" href="sss.css" type="text/css" media="all">


<script>
	jQuery(function($) {
		$('.slider').sss();
	});
</script>



<script type="text/javascript">
	/*The code is pretty simple, we animate the ul with a -500px margin left. Then we find the first li and put it last to get the infinite animation.*/
	$(function() {
		//alert(document.getElementById("oo").style.width);
		setInterval(function() {

			$(".slideshow ul").animate({
			//marginLeft : -document.getElementById("oo").style.width
			}, 1000, function() {
				$(this).css({
					marginLeft : 0
				}).find("li:last").after($(this).find("li:first"));
			})
		}, 1500);
	});

	function deleteSelected(id) {
		var selection = document.getElementById("select" + id);

		if (selection.selectedIndex >= 0) {
			var ImageId = selection.options[selection.selectedIndex].value;
			selection.remove(selection.selectedIndex);
			$("#" + ImageId).remove();
		}

	}
</script>





<script type="text/javascript">
	function performAjaxSubmit(multi, id) {
		alert("movida");
		var sampleFile = document.getElementById("sampleFile" + id).files[0];

		var formdata = new FormData();

		var image;
		var slider;
		alert("Dam");
		if (multi == 0) {
			image = document.getElementById("image" + id);
		} else {
			alert("Das");
			slider = document.getElementById("slider" + id);
			alert("bol");
		}

		formdata.append("sampleFile", sampleFile);
		alert(1);
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xhr = new XMLHttpRequest();
			alert("pirveli");
		} else {
			// code for IE6, IE5
			alert("meore");
			xhr = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xhr.open("POST", "FileUploader", true);
		xhr.send(formdata);
		alert(2);
		xhr.onreadystatechange = function(e) {
			if (xhr.readyState == 4 && xhr.status == 200) {
				if (multi == 0) {
					alert(5);
					image.innerHTML = xhr.responseText;
				} else {
					//var text = document.getElementById("imageText" + id);
					//alert(text.value);
					/* alert("qeia+");
					var additionalText = "";
					if (text.value != "") {
						alert("no");
						additionalText = '<span class="caption">'
								+ text.value + '</span>';
					} */
					alert(3);
					numElements += 1;
					slider.innerHTML += '<li id='+numElements+'>'
							+ this.responseText + '</li>';
					document.getElementById('select' + id).innerHTML += '<option value='+numElements+'>'
							+ sampleFile.name + '</option>';

				}
				alert(this.responseText);

			}

		};

	}
</script>
</head>

<body>
	<%--text --%>
	<%-- 
	<div class="drsElement"
		style="left: 50px; top: 150px; width: 350px; height: 90px; background: white; text-align: center">
		<div class="drsMoveHandle">Text:</div>
		<div style="border: 1px solid green; background-color: #A5B7F2;">
			Font: <input id="zaz" size="5" class="color" colorType="font"
				labelId="7" value="000000"> Back: <input id="zaz" size="5"
				class="color" colorType="back" labelId="7" value="FFFFFF">
				
			Size:<input id="size7" size="1" value="18" onkeydown="changeSize(7);"
				onpaste="changeSize(7);" oninput="changeSize(7);"> Bold: <input
				type="checkbox" id="bold7" onclick="changeBold(7);">
		</div>
		<textarea id="7"style="  text-align: center; width: calc(100% - 6px); height: calc(100% - 53px); resize:none;">
Your Text
</textarea>
		
	</div>
--%>






	<%--control panel --%>
	<div id="edit" onclick="makeEdition()" class="circle"
		style="visibility: hidden; left: 20px; background: #FF3399;">
		<i class="icon1 fa fa-edit fa-lg"></i> <i
			class="icon2 fa fa-star fa-lg"></i> <span> Edit</span>
	</div>
	<div id="control">

		<div class="circle" onclick = "changeSubVisibility()" style="left: 20px; background: #253DDA;">
			<i class="icon1 fa fa-pencil fa-lg"></i> <i
				class="icon2 fa fa-star fa-lg"></i> <span> ADD</span>
		</div>
		<div id="addSub"  style="visibility:hidden">
			<div class="sub">
				<div onclick="createText();" class="circle">
					<i class="icon1 fa  fa-edit fa-lg"></i> <i
						class="icon2 fa fa-plus fa-lg"></i><span>Text</span>
				</div>
				<div onclick="createImage();" class="circle">
					<i class="icon1 fa fa-photo fa-lg"></i> <i
						class="icon2 fa fa-plus fa-lg"></i> <span>Image</span>
				</div>
				<div onclick="createCommentBox();" class="circle">
					<i class="icon1 fa  fa-comments-o fa-lg"></i> <i
						class="icon2 fa fa-plus fa-lg"></i> <span>Comments Box</span>
				</div>

				<div onclick="createGallery();" class="circle">
					<i class="icon1 fa fa-tasks fa-lg"></i> <i
						class="icon2 fa fa-plus fa-lg"></i> <span>Slider Gallery</span>
				</div>
				<div onclick="createVideo();" class="circle">
					<i class="icon1 fa fa-youtube-play fa-lg"></i> <i
						class="icon2 fa fa-plus fa-lg"></i> <span>Youtube Video</span>
				</div>


			</div>
		</div>


		<div id="save" onclick="save()" class="circle"
			style="top: 120px; background: #FF2F2F;">
			<i class="icon1 fa fa-save fa-lg"></i> <i
				class="icon2 fa fa-star fa-lg"></i> <span> Save</span>
		</div>

		<div id="view" onclick="view()" class="circle"
			style="top: 215px; background: #2BDA25;">
			<i class="icon1 fa fa-globe  fa-lg"></i> <i
				class="icon2 fa fa-star fa-lg"></i> <span> View</span>
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
		
		<div class="drsMoveHandle " style="background: #DFC;width:100%; height:calc(100% - 50px);" id="image1"  >
			<img alt="noImage" src="noImage.png " style="width: 100%;height:100%;">
		</div>

	</div>
	--%>



	<%--comment box --%>
	<%--
	<div class="drsElement"
		style="left: 150px; top: 280px; width: 500px; height: 350px; background: #DFC; text-align: center">

		<div class="drsMoveHandle">Comment Box</div>
		<form action="#" id="usrform"
			style="background: #DFC; width: 100%; height: 20%;">
			Name: <input type="text" name="usrname"> <input disabled
				class="gobutton" value="post" type="submit" style="background: blue">
		</form>
		<textarea rows="4" cols="50"
			style="width: calc(100% - 5px); height: 20%;" name="comment"
			form="usrform">
Enter text here...</textarea>
		<br> <br>
		<div class="chatBox"
			style="width: calc(100% - 70px); height: calc(60% - 70px);">
			<p class="bubbleLeft2">
				<a class="name">name</a> simple comment 1
			</p>
			<p class="bubbleLeft2">
				<a class="name">name</a>simple comment 2
			</p>
		</div>

	</div> --%>


	<%-- gallery --%>
	<%-- 
	<div class="drsElement"
		style="left: 150px; top: 280px; width: 50px; height: 100px; background: #DFC; text-align: center">
		<div class="drsMoveHandle" style="background: #DFC">Slider</div>

		<form id="1">

			<input id="sampleFile1" name="sampleFile" type="file" accept="image" />
			<br /> Image descr: <input id="imageText1" type="text" /> <input
				class="gobutton" id="uploadBtn" type="button" value="Upload"
				onClick="performAjaxSubmit(1,1);"></input>
		</form>
		<div style="width: 550px; height: 300px;">
			<div  class="slider">
				
				<img src="images/image-slider-3.jpg" />
				<img src="images/image-slider-1.jpg" />
			</div>
		</div>
	</div>--%>
	<%-- 	<div id="oo" class="drsElement"
		style="left: 150px; top: 280px; width: 500px; height: 300px; background: #DFC; text-align: center">
		<div class="drsMoveHandle" style="background: #DFC">Slider</div>

		<form id="99">

			<input id="sampleFile99" name="sampleFile" type="file" accept="image" /><input
				class="gobutton" id="uploadBtn" type="button" value="Upload"
				onClick="performAjaxSubmit(1,99);"></input> <br /> <select
				id="select99">
			</select> <input class="gobutton" type="button" value="Delete"
				onClick="deleteSelected(99);"></input>
		</form>
		<div class="slideshow"
			style="width: calc(100% - 5px); height: calc(100% - 110px);">
			<ul id="slider99">

			</ul>
		</div>
	</div>
--%>




	<%--video --%>
	<%-- 	<div class="drsElement"
		style="left: 150px; top: 280px; width: 560px; height: 350px; background: #DFC; text-align: center">
		<div class="drsMoveHandle" style="background: #DFC">Youtube Video</div>
Youtube link: <input id="videoLink1" type="text" /> <br><input
				class="gobutton"  type="button" value="Upload Video"
				onClick="uploadVideo(1);"></input>

		<div id="video1" style="width:100%;height:calc(100% - 75px);"></div>
</div>
--%>

</body>
</html>