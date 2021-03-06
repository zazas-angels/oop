<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
 <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
 <title>Div Drag/Resize Demo</title>
 <script type="text/javascript" src="dragresize.js"></script>
<link rel="stylesheet" type="text/css" href="DragResizeStyle.css">
<style type="text/css">


</style>

<script type="text/javascript">
//<![CDATA[

// Using DragResize is simple!
// You first declare a new DragResize() object, passing its own name and an object
// whose keys constitute optional parameters/settings:

var dragresize = new DragResize('dragresize',
 { minWidth: 50, minHeight: 50, minLeft: 20, minTop: 20, maxLeft: 600, maxTop: 600 });

// Optional settings/properties of the DragResize object are:
//  enabled: Toggle whether the object is active.
//  handles[]: An array of drag handles to use (see the .JS file).
//  minWidth, minHeight: Minimum size to which elements are resized (in pixels).
//  minLeft, maxLeft, minTop, maxTop: Bounding box (in pixels).

// Next, you must define two functions, isElement and isHandle. These are passed
// a given DOM element, and must "return true" if the element in question is a
// draggable element or draggable handle. Here, I'm checking for the CSS classname
// of the elements, but you have have any combination of conditions you like:

dragresize.isElement = function(elm)
{
 if (elm.className && elm.className.indexOf('drsElement') > -1) return true;
};
dragresize.isHandle = function(elm)
{
 if (elm.className && elm.className.indexOf('drsMoveHandle') > -1) return true;
};

// You can define optional functions that are called as elements are dragged/resized.
// Some are passed true if the source event was a resize, or false if it's a drag.
// The focus/blur events are called as handles are added/removed from an object,
// and the others are called as users drag, move and release the object's handles.
// You might use these to examine the properties of the DragResize object to sync
// other page elements, etc.

dragresize.ondragfocus = function() { };
dragresize.ondragstart = function(isResize) { };
dragresize.ondragmove = function(isResize) { };
dragresize.ondragend = function(isResize) { };
dragresize.ondragblur = function() { };

// Finally, you must apply() your DragResize object to a DOM node; all children of this
// node will then be made draggable. Here, I'm applying to the entire document.
dragresize.apply(document);

//]]>
</script>

</head>
<body style="font: 13px/20px sans-serif; background-color: #FFF">


<!--
 Here's our draggable elements.
 All that's required is that they have a relative or absolute CSS 'position',
 and are matched by the isElement/isHandle methods of a DragResize object.
 Easy!
-->

<div class="drsElement"
 style="left: 50px; top: 150px; width: 250px; height: 120px;
 background: white; text-align: center">
 <div class="drsMoveHandle">Color:</div>


<p contenteditable="true">This is a paragraph. It is editable. Try to change this text.</p>
</div>
<div class="drsElement"
 style="left: 20px; top: 300px; width: 150px; height: 200px;
 background:white; text-align: center"> 
 <div class="drsMoveHandle">Div 1</div>
 
 Content
</div>

<div class="drsElement drsMoveHandle"
 style="left: 150px; top: 280px; width: 50px; height: 100px;
 background: #DFC; text-align: center">
 Div 2
 Content
</div>


</body>
</html>