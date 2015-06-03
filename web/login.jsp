<%@ page contentType="text/html; charset=UTF-8" %>

<div id="authorization-block" style="display: none">

    <!--div class="fb-login-button" data-max-rows="1" data-size="medium" data-show-faces="false" data-auto-logout-link="false"></div-->

    <%@include file="googlePlusButton.html" %>
    <%@include file="facebookSDK.html" %>
    გაიარეთ ავტორიზაცია:
    <br><br>

    <form action="login" method="post">
        <table>
            <tbody>
            <%if (request.getSession().getAttribute("wrong try to log in") == null) { %>
            <tr id="invalidUserNamePassword" style="visibility: hidden">
                    <%} else {%>
            <tr id="invalidUserNamePassword">
                <script>
                    $("#authorization-block").show();
                </script>
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
                    <button name="loginButton">შესვლა</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <br><br>

    <div>
        <table>
            <tr>
                <td>

                </td>
                <td>
                    <div class="fb-login-button" data-max-rows="1" data-size="large" data-show-faces="false"
                         data-auto-logout-link="false" scope="public_profile,email" onlogin="checkLoginState()"></div>
                </td>
            </tr>
        </table>
    </div>
</div>