<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="UTF-8">
<title></title>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="registration.js"></script>
 
<%-- Java script fuctions --%>
<script src="NextCategories.js"></script>
<script>
	//sending request is from W3School tutorial
	//make next categories and make button (set it disabled or not)
	function makeNextCategories(id) {
		alert('it works!'); 
		var list = document.getElementById("categories");
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
		alert(items.length);
	}
	//make users which are connected to these categoreis
	function makeUsersForCategory(id) {
		alert('users works!');
		var list = document.getElementById("users");
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

		xmlHttp.open("POST", "UsersServlet?categoryId=" + id, true);
		xmlHttp.send();
	}
</script>
<title>Categories</title>

<style type="text/css">
img {
	padding: 4px;
	border: 4px solid;
	border-color: red;
	box-shadow: 10px 10px 5px grey;
	border-radius: 50%;
	cursor: pointer;
}
</style>
</head>
<body>
	<%@include file="login_registration.jsp"%>
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
	<%="<ul id=\"categories\" >"%>
	<%
		for (int i = 0; i < roots.size(); i++) {
	%>
	<%="<li>"%>
	<%="<a href='#' onclick=\"makeNextCategories("
						+ roots.get(i).getId() + ");\"> "
						+ roots.get(i).getName() + " </a>"%>
	<%="</li>"%>
	<%
		}
	%> 
	<%="</ul>"%>

	<%-- Shows all users by rating --%>
	<%
		Connection database = (Connection) request.getServletContext()
				.getAttribute("database");
	%><%= "<ul id=\"users\" >"%><% 
		ResultSet users = database.getUsers();

		if (users != null) {
			while (users.next()) {
				%><%="<li>"%><%=("<a href='Visitor.jsp?id="+users.getString("ID")+"' > "
						+ "<img src=\""
						+ users.getString("avatarFile")
						+ "\" height=\""
						+ SiteConstants.USER_IMG_HEIGTH
						+ "\" width=\""
						+ SiteConstants.USER_IMG_WIDTH
						+ "\"> " + users.getString("name") + " </a>")%><%=
				"</li>"%><% 

			}
		}%><%="</ul>"
	%>
</body>
</html>