<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function onLogout() {
        console.log("ignore");
    }
    function logout() {
        console.log("ignore");
    }
</script>
<%
    String logoutFunction = "onLogout()";
    if (request.getSession().getAttribute("type") != null) {
        String type = (String) request.getSession().getAttribute("type");
        if (type.equals("fb")) {
            logoutFunction = "onLogoutFB();";
        } else {
            if (type.equals("gp")) {
                logoutFunction = "logout();";
            } else {
                // is signed in with email
            }
        }
    }
%>
<a href="/logout">
<button type="button" class="btn btn-default btn-sm" onclick=<%=logoutFunction%>>
        <span class="glyphicon glyphicon-log-out"></span> გასვლა
    </button>
</a>