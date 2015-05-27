<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- Author glaba13 --%>
<%-- Java script fuctions --%>
<%-- dummy --%>
<script src="NextCategories.js"></script>
<script>
	/* checking if clicked was reaaly on this div and not on super.
	 * From stack overflow
	 http://stackoverflow.com/questions/2015041/two-differents-onclick-on-two-divs-one-over-the-other
	 */
	function amIclicked(e, id) {
		e = e || event;
		var target = e.target || e.srcElement;
		if (target.id == id)
			return true;
		else
			return false;
	}
	//sending request is from W3School tutorial
	//get expanded categories
	function expandCategory(event, id, hasChilds) {
		alert(id);
		if (hasChilds == false)
			return;
		if (!amIclicked(event, id))
			return;

		var element = document.getElementById(id + "");
		//shrink this way
		if (element.getAttribute("isExpanded")=="true") {
			element.setAttribute("isExpanded","false");
			shrinkCategory(event, id, hasChilds);
			return;
		}
		element.setAttribute("isExpanded","true");
		//change on click function
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
		//we could do so but it will goes for all sub divs and ned more complexity
		//element.onclick=function(){shrinkCategory(event,id,hasChilds); } ;

	}
	function shrinkCategory(event, id, hasChilds) {
		alert("Axali" + id);
		if (!amIclicked(event, id))
			return;

		var element = document.getElementById(id + "");
		element.innerHTML = element.getAttribute("categoryName");
	}
</script>
<style type="text/css">
.not-active {
	pointer-events: none;
	cursor: default;
}
</style>
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
			int id = roots.get(i).getId();
			String categoryName = roots.get(i).getName();
			//reaaly every root has chils , but work for every case
			writer.print("<div id =\""
					+ id
					+ "\"  categoryName=\""
					+ categoryName
					+ "\"  isExpanded = \"false\" onclick=\"expandCategory(event,"
					+ id + ",true);\"> " + categoryName + " </div>");
			writer.print("</li>");

		}
		writer.print("</ul>");
	%>

</body>
</html>