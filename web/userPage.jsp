<%@ page import="core.user.User" %>
<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 5/24/15
  Time: 11:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>
    Wellcome <%=((User) request.getSession().getAttribute("user")).getName()%>
</h1>

</body>
<%@include file="logout.jsp" %>
</html>
