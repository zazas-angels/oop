<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<link rel="stylesheet" type="text/css" href="userShow.css">
<link rel="stylesheet" type="text/css" href="categoryView.css">
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" type="text/css" href="back.css">
<meta charset="UTF-8">
<title></title>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="http://ricostacruz.com/jquery.transit/jquery.transit.min.js"></script>
<script src="registration.js"></script>

<%-- Java script fuctions --%>
<script src="NextCategories.js"></script>
<script>
	//sending request is from W3School tutorial
	//make next categories and make button (set it disabled or not)
	function makeNextCategories(id) {
		//alert('it works!');
		var list = document.getElementById("cat");
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
				list.innerHTML = xmlHttp.responseText;
			}
		};

		xmlHttp.open("POST", "CategoriesServlet?id=" + id, true);
		xmlHttp.send();
		makeUsersForCategory(id);
		if (id == 0)
			document.getElementById("upButton").disabled = true;
		
	}
	//make users which are connected to these categoreis
	function makeUsersForCategory(id) {
		//alert('users works!');
		var list = document.getElementById("athlete-images");
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
			//	alert(1);
			//	alert(xmlHttp.responseText);
				list.innerHTML = xmlHttp.responseText;
			}
		};

		xmlHttp.open("POST", "UsersServlet?categoryId=" + id, true);
		xmlHttp.send();
	}
</script>
<title>Categories</title>

<style type="text/css">
img {
	padding: 4px;
	border: 4px solid;
	border-color: #02abfd;
	box-shadow: 10px 10px 5px grey;

	cursor: pointer;
}
.siteName { color: #fff; font-family: 'Righteous', cursive; font-size: 65px; font-weight: normal; line-height: 60px; margin: 10px 0 20px; text-transform: uppercase; text-shadow: 2px 2px 0 #000; margin: 10px 0 24px; text-align: right; }
</style>
</head>

<body>
<script src="//cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

<div id="header">

	<%@include file="login_registration.jsp"%>
	<h1 class="siteName">arran.ge</h1>
	<div style="position:absolute;right:0; top:0;"class="logopt"></div>
	</div>
	<%@ page import="core.category.CategoryTreeInterface"%>
	<%@ page import="core.category.CategoryInterface"%>
	<%@ page import="core.database.Connection"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.io.PrintWriter"%>
	<%@ page import="java.sql.ResultSet"%>
	<%@ page import="core.SiteConstants"%>

	<div id=button></div>

	<%-- shows all root categories --%>
	<%
		CategoryTreeInterface categories = (CategoryTreeInterface) request
				.getServletContext().getAttribute("categories");
		List<CategoryInterface> roots = categories.getRoots();
		PrintWriter writer = response.getWriter();
	%>
	<%="<div class=\"box\">"%>
			<%=" <div  class=\"box-content\">"%>
					<%="  <div id=\"categories\">"%>
	<%="<ul id=\"cat\" >"%>
	<%
		for (int i = 0; i < roots.size(); i++) {
	%>
	<%="<li>"%>
	<%="<a class=\"outfitshome\" href='#' onclick=\"makeNextCategories("
						+ roots.get(i).getId() + ");\"> "
						+ roots.get(i).getName() + " </a>"%>
	<%="</li>"%>
	<%
		}
	%>
	<%="</ul></div></div></div>"%>


	<%="<div style=\"background-color: rgba(252, 252, 252, 0.86);\" class=\"athletes-list-wrapper\">"%>
			<%="<div class=\"nav-block left\"></div>"%>
					<%="<div class=\"athlete-list with-buttons\">"%>
							<%="<div id=\"list-holder\">"%>
									<%="	<ul id=\"athlete-images\">"%>
					
		
	
		<%-- Shows all users by rating --%>
		<%
			Connection database = (Connection) request.getServletContext()
					.getAttribute("database");
		%><%="<ul id=\"users\" >"%>
		<%
			ResultSet users = database.getUsers();

			if (users != null) {
				while (users.next()) {
		%><%="<li>"%><%="<a href='Visitor.jsp?id="
							+ users.getString("ID") + "' > " + "<img src=\""
							+ users.getString("avatarFile") + "\""  + "> <span>"
							+ users.getString("name") + "</span> </a>"%><%=
				"</li>"%>
		<% 

			}
		}%>
			<%="	</ul>"%>
					<%="</div>"%>
							<%="</div>"%>
									<%="<div class=\"nav-block right\"></div>"%>
											<%="</div>"%>
		
		<script type="text/javascript" src="userShow.js"></script>
		<script type="text/javascript" src="back.js"></script>
</body>
</html>