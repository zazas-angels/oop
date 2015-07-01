<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 5/31/15
  Time: 11:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="back.css">
<html>
<head>

    <title>რეგისტრაცია</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="registration.js"></script>
</head>
<body>
<div align="center"
     style="background-color: rgba(145, 232, 244, 0.3); position: absolute; top: 20%; left: calc(50% - 200px);">
    <h1>რეგისტრაცია</h1>


    <form action="fbG+Servlet" , method="post">
        <table>
            <tbody>
            <td colspan="2" style="color: red;"><label id = "errorMessage">
                <%
                    if (request.getSession().getAttribute("message") != null) {
                        String message = (String) request.getSession().getAttribute("message");
                        request.getSession().setAttribute("message", null);
                %><%=message%></label></td>
            <%} else {%>
            </label></td>
            <%}%>
            <tr>
                <td>დასახელება:</td>
                <td><input type="text" placeholder="მინ. 1 სიმბოლო"
                           autocomplete="off" id="name" name="name"/></td>
                <td>
                    <div id="checkName"></div>
                </td>
            </tr>
            <tr>
                <td>Url:</td>
                <td><input type="text" placeholder="მინ. 1 სიმბოლო"
                           autocomplete="off" id="url" name="url"/></td>
                <td>
                    <div id="urlCheck">.arran.ge</div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="2">
                    <button type="submit" class="btn btn-success" name="registration" onclick="return trySignUpFb()">
                        რეგისტრაცია
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
