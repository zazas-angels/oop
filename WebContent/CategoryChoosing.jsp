<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- Java script fuctions --%>
<%-- dummy --%>
<script src="NextCategories.js"></script>
<script>
	//sending request is from W3School tutorial
	//get expanded categories
	function expandCategory(id) {
		alert('it works!');
		var element = document.getElementById("id");

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
				element.innerHTML += xmlHttp.responseText;
			}
		};

		xmlHttp.open("POST", "ExpandCategoriesServlet?id=" + id, true);
		xmlHttp.send();
		alert(items.length);
	}
</script>
<title>Category Chooser</title>
</head>
<body>
<%-- shows all root categories --%>
<%@ page import="core.category.CategoryTreeInterface"%>
	<%@ page import="core.category.CategoryInterface"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.io.PrintWriter"%>
	<%

		CategoryTreeInterface categories = (CategoryTreeInterface) request
				.getServletContext().getAttribute("categories");
		List<CategoryInterface> roots = categories.getRoots();
		PrintWriter writer = response.getWriter();
		writer.print("<ul id=\"categories\" >");
		for (int i = 0; i < roots.size(); i++) {
			writer.print("<li>");
			int id = roots.get(i).getId() ;

			writer.print("<a id =\""+id+"\" href='#' onclick=\"expandCategory("
					+id + ");\"> "
					+ roots.get(i).getName() + " </a>");
			writer.print("</li>");

		}
		writer.print("</ul>");
	%>

</body>
</html>