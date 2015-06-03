<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function onLogout() {

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
<a href="/logout" onclick=<%=logoutFunction%>>Logout</a>