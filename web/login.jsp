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
    Email: <input type="text" name="username"> <br><br>
    Password: <input type="password" name="password">
    <button name="loginButton">Login</button>
    <br><br>

    <p><a href="#" onclick="loadRegistrationHtml()">registration</a></p>
</div>
