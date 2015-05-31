<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 5/27/15
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    if (request.getSession().getAttribute("logged in") != null && (Boolean) request.getSession().getAttribute("logged in")) {
        request.getRequestDispatcher("userPage.jsp").forward(request, response);
    }%>

<div id="login">
    <table>
    <tr>

        <td>
            <button id="login-button">შესვლა</button>
        </td>
        <td>
            <button id="reg-button">რეგისტრაცია</button>
        </td>
    </tr>
    </table>
</div>

<%@include file="login.jsp" %>
<%@include file="registration.jsp" %>

<% if (request.getSession().getAttribute("registration") != null) {
    request.getSession().setAttribute("registration", null);%>

<script>
    $("#registration-block").show();
</script>
<%}%>