<%@ page contentType="text/html; charset=UTF-8" %>

<div id="authorization-block" style="display: none">
<%
    if (request.getSession().getAttribute("logged in") != null && (Boolean) request.getSession().getAttribute("logged in")) {
        request.getRequestDispatcher("userPage.jsp").forward(request, response);
    }%>
    <% if (request.getSession().getAttribute("registration") != null) {
        request.getSession().setAttribute("registration", null);%>

<script>loadRegistrationHtml();</script>
<%} else {%>
    <!--div class="fb-login-button" data-max-rows="1" data-size="medium" data-show-faces="false" data-auto-logout-link="false"></div-->

    <%@include file="facebookSDK.html" %>
    გაიარეთ ავტორიზაცია:
    <br><br>

    <form action="login" method="post">
        <table>
            <tbody>
            <%if (request.getSession().getAttribute("wrong try to log in") == null) { %>
            <tr id="invalidUserNamePassword" style="visibility: hidden">
                    <%} else { %>
            <tr id="invalidUserNamePassword">
                <%}%>
                <td colspan="2">
                    არასწორი ელ.ფოსტა ან პაროლი
                </td>
            </tr>
            <tr>
                <td>ელ.ფოსტა:</td>
                <td><input type="text" autocomplete="off" name="email"></td>
            </tr>
            <tr>
                <td>პაროლი:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button name="loginButton" onclick="return loadLoginHtml()">შესვლა</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <br><br>

    <div>
        <button id="signinButton">google +</button>
        <div class="fb-login-button" data-max-rows="1" data-size="medium" data-show-faces="false"
             data-auto-logout-link="false"></div>
    </div>
<%}%>
</div>