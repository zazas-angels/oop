<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="NextCategories.js"></script>
<script>
	//sending request is from W3School tutorial
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
		if(id==0)
		document.getElementById("upButton").disabled=true;
		alert(items.length);
	}

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
</head>
<body>
	<%@ page import="core.CategoryTreeInterface"%>
	<%@ page import="core.CategoryInterface"%>
	<%@ page import="core.Connection"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.io.PrintWriter"%>
	<%@ page import="java.sql.ResultSet"%>

<div id = button></div>


	<%
		CategoryTreeInterface categories = (CategoryTreeInterface) request
				.getServletContext().getAttribute("categories");
		List<CategoryInterface> roots = categories.getRoots();
		PrintWriter writer = response.getWriter();
		writer.print("<ul id=\"categories\" >");
		for (int i = 0; i < roots.size(); i++) {
			writer.print("<li>");

			writer.print("<a href='#' onclick=\"makeNextCategories("
					+ roots.get(i).getId() + ");\"> "
					+ roots.get(i).getName() + " </a>");
			writer.print("</li>");

		}
		writer.print("</ul>");
	%>


	<%
		Connection database = (Connection) request.getServletContext()
				.getAttribute("database");
		writer.print("<ul id=\"users\" >");
		ResultSet users = database.getUsers();

		if (users != null) {
			while (users.next()) {
				writer.print("<li>");

				writer.print("<a href='#' onclick=\"alert('notImlemented yet')\"> "
						+ users.getString(2) + " </a>");
				writer.print("</li>");

			}
		}
		writer.print("</ul>");
	%>
</body>
</html>