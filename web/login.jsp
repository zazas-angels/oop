<!--
if(request.getSession().getAttribute("logged in") != null && (boolean)request.getSession().getAttribute("logged in")){
request.getRequestDispatcher("logged in.jsp").forward(request, response);
}
%>
<b>
if(request.getAttribute("wrong try to log in") == null){ %>
Please log in:
} else { %>
Either your user name or password is incorrect. Please try again.
}%>
<br><br>
User Name: <input type="text" name="username"> <br><br>
Password: <input type="password" name="password"> <button name="loginButton">Login</button><br><br>
<p><a href="createAccount.jsp">Create New Account</a></p>
</b>
-->
<div id="login">
    Please log in:
    <br><br>
    <table>
        <tbody>
        <tr id="invalidUserNamePassword">
            <td colspan="2">
                Invalid username or password
            </td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form action="login" method="post">
                    <button name="loginButton">Login</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
    <br><br>

    <p><a href="#" onclick="loadRegistrationHtml()">registration</a></p>
</div>
