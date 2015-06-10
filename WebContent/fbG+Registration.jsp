<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 5/31/15
  Time: 11:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>რეგისტრაცია</title>
</head>
<body>
<h1>
    რეგისტრაცია
</h1>


<form action="fbG+Servlet" , method="post">
    <table>
        <tbody>
        <tr>
            <td>დასახელება:</td>
            <td><input type="text" placeholder="მინ. 1 სიმბოლო" autocomplete="off" id="name" name="name"/></td>
            <td>
                <div id="checkName"></div>
            </td>
        </tr>
        <tr>
            <td> Url:</td>
            <td><input type="text" placeholder="მინ. 1 სიმბოლო" autocomplete="off" id="url" name="url"/></td>
            <td>
                <div id="urlCheck">.chveniSaiti.ge</div>
            </td>
        </tr>
        <tr>
            <td></td>
            <td colspan="2">
                <button name="registration" onclick="return trySignUpFb()">რეგისტრაცია</button>
            </td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
