<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="sss.min.js"></script>
<link rel="stylesheet" href="sss.css" type="text/css" media="all">


</head>
<body>
<div class="slider" style="width:30; heigth:60;">
<img src="images/image-slider-1.jpg" />
<img src="images/image-slider-2.jpg"  />
<img src="images/image-slider-5.jpg"  />
<div class="just_text">This one's just text.</div>
<img src="images/image-slider-3.jpg" />
<div><img src="images/image-slider-4.jpg" /><span class="caption">This one has a caption</span></div>
</div>


<div class="slider">
<img src="images/image-slider-1.jpg" />
<img src="images/image-slider-2.jpg"  />
<div class="just_text">zaza.</div>
<img src="images/image-slider-3.jpg" />
<div><img src="images/image-slider-4.jpg" /><span class="caption">This one has a caption</span></div>
</div>
<script>
jQuery(function($) {
$('.slider').sss();
});
</script>
</body>
</html>