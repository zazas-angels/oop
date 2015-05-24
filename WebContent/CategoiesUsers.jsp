<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script  src = "NextCategories.js"></script>
<script>
function makeNextCategories(id){
	alert('it works!');
	var list = document.getElementById("categories");
	list.innerHTML=id;

	

alert(items.length);
}
</script>
<title>Categories</title>
</head>
<body>
<%@ page  import="core.CategoryTreeInterface" %>
<%@ page  import="core.CategoryInterface" %>
<%@ page  import="java.util.ArrayList" %>
<%@ page  import="java.util.List" %>
<%@ page  import="java.io.PrintWriter" %>




<%
CategoryTreeInterface categories = (CategoryTreeInterface)request.getServletContext().getAttribute("categories");
List<CategoryInterface> roots = categories.getRoots();
PrintWriter writer = response.getWriter();
writer.print("<ul id=\"categories\" >");
for(int i=0; i<roots.size(); i++){
	writer.print("<li>");
	
	writer.print("<a href='#' onclick=\"makeNextCategories("+roots.get(i).getId()+");\"> "+ roots.get(i).getName()+" </a>");
	writer.print("</li>");
	
}
writer.print("</ul>");


%>


<ul id="foo">
  <li>
  <a href="#">First </a>
  </li>
  <li>Second</li>
  <li>Third</li>
</ul>
</body>
</html>