<%
    if (request.getSession().getAttribute("logged in") != null && (Boolean) request.getSession().getAttribute("logged in")) {
        request.getRequestDispatcher("userPage.jsp").forward(request, response);
    }
%>
<div id="login">
    Please log in:
    <br><br>

    <form action="login" method="post">
        <table>
            <tbody>
            <%if (request.getAttribute("wrong try to log in") == null) { %>
            <tr id="invalidUserNamePassword" style="visibility: hidden">
                    <%} else { %>
            <tr id="invalidUserNamePassword">
                <%}%>
                <td colspan="2">
                    Invalid email or password
                </td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button name="loginButton">Login</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <br><br>

    <p><a href="#" onclick="loadRegistrationHtml()">registration</a></p>
</div>
