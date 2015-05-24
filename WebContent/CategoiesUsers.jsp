<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
function makeNextCatfegories(){
	alert('it works!');
	document.getElementById('categories').innerHTML = Date();
	//document.getElementById('categories').innerHTML = Date();
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

<ul id="categories">

<%
CategoryTreeInterface categories = (CategoryTreeInterface)request.getServletContext().getAttribute("categories");
List<CategoryInterface> roots = categories.getRoots();
PrintWriter writer = response.getWriter();
for(int i=0; i<roots.size(); i++){
	writer.println("<li>");
	
	writer.println("<a href='#' onclick=\"makeNextCatfegories();\"> "+ roots.get(i).getName()+" </a>");
	writer.println("</li>");
	
}
%>

</ul>
</body>
</html>