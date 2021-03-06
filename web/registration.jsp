<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 5/23/15
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<div id="registration-block" style="display: none">
    <h1>
        რეგისტრაცია
    </h1>

    <form action="registration"  method="post">
        <table>
            <tbody>
            <tr>
                <td colspan="2" style="color: red;"><label id = "errorMessage">
                <%
                    if (request.getSession().getAttribute("message") != null) {
                        String message = (String) request.getSession().getAttribute("message");
                        request.getSession().setAttribute("message", null);
                %><%=message%></label></td>
                <script>
                    $("#registration-block").show();
                </script>
                <%} else {%>
                </label></td>
                <%}%>
            </tr>
            <tr>
                <td>დასახელება:</td>
                <td><input type="text" placeholder="მინ. 1 სიმბოლო" autocomplete="off" id="name" name="name"/></td>
                <td>
                    <div id="checkName"></div>
                </td>
            </tr>
            <tr>
                <td>ელ. ფოსტა:</td>
                <td><input type="text" autocomplete="off" id="email" name="email" onkeyup="return checkMail()"></td>
                <td>
                    <div id="checkEmail"></div>
                </td>
            </tr>
            <tr>
                <form action="" method="post" id="passwordTest">
                    <td>პაროლი:</td>
                    <td><input type="password" id="password" name="password" onkeyup="return passwordChanged()"/></td>
                    <td>
                        <div id="strength"></div>
                    </td>
                </form>
            </tr>
            <tr>
                <td>გაიმეორეთ პაროლი:</td>
                <td><input type="password" id="passwordRepeat" onkeyup="return passwordsMatch()"/></td>
                <td>
                    <div id="matches"></div>
                </td>
            </tr>
            <tr>
                <td> Url:</td>
                <td><input type="text" placeholder="მინ. 1 სიმბოლო" autocomplete="off" id="url" name="url"/></td>
                <td>
                    <div id="urlCheck">.arran.ge</div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="2">
                    <button name="registration" type="submit" class="btn btn-success" onclick="return trySignUp()">რეგისტრაცია</button>
                </td>
            </tr>
            </tbody>
        </table>
        <div>
            <%@include file="googlePlusButton.html" %>
        </div>
        <div>
            <%@include file="facebookButton.html" %>
        </div>
    </form>
</div>
