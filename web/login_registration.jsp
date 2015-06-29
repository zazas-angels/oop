<%@ page import="core.SiteConstants" %>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 5/27/15
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    boolean b = true;
    if (request.getSession().getAttribute("logged in") != null && (Boolean) request.getSession().getAttribute("logged in")) {
        String nextPage = "userPage.jsp";
        if (request.getSession().getAttribute("type") != null){
            if (request.getSession().getAttribute("type").equals("admin") || request.getSession().getAttribute("type").equals("superAdmin") ) {
                nextPage = "adminPage.jsp";
            }
    }
        request.getRequestDispatcher(nextPage).forward(request, response);
    } else {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(SiteConstants.LOGIN_COOKIE_NAME) && !cookie.getValue().equals("")) {
%>
<script>window.location = 'login';</script>
<%
                    b = false;
                }
            }
        }
    }
    if (b) {

%>

<div id="login">
    <table>
        <tr>

            <td>
                <button id="login-button" type="button" class="btn btn-info">შესვლა</button>
            </td>
            <td>
                <button id="reg-button" type="button" class="btn btn-info">რეგისტრაცია</button>
            </td>
        </tr>
    </table>
</div>

<%@include file="login.jsp" %>
<%@include file="registration.jsp" %>

<% if (request.getSession().getAttribute("registration") != null && !(Boolean) request.getSession().getAttribute("registration")) {
    request.getSession().setAttribute("registration", null);%>
<%
        }
    }
%>