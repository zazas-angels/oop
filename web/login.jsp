<%@ page contentType="text/html; charset=UTF-8" %>
<%
    if (request.getSession().getAttribute("logged in") != null && (Boolean) request.getSession().getAttribute("logged in")) {
        request.getRequestDispatcher("userPage.jsp").forward(request, response);
    }
%>
<div id="login" charset="UTF-8">
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
                    <button name="loginButton">შესვლა</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <br><br>

    <p><a href="#" onclick="loadRegistrationHtml()">რეგისტრაცია</a></p>
</div>
