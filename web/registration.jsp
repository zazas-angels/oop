<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 5/23/15
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<h1>
    რეგისტრაცია
</h1>

<form action="/registration" , method="post">
    <table>
        <tbody>
        <tr>
            <td colspan="3">
                შეავსეთ ველები:
            </td>
        </tr>
        <tr>
            <td>ელ. ფოსტა:</td>
            <td><input type="text" autocomplete="off" id="email" onkeyup="return checkMail()"></td>
            <td>
                <div id="checkEmail"></div>
            </td>
        </tr>
        <tr>
            <form action="" method="post" id="passwordTest">
                <td>პაროლი:</td>
                <td><input type="password" id="password" onkeyup="return passwordChanged()"/></td>
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
            <td><input type="text" autocomplete="off" id="url"/></td>
            <td>
                <div id="urlCheck">.chveniSaiti.ge</div>
            </td>
        </tr>
        <tr>
            <td></td>
            <td colspan="2">
                <button name="registration" onclick="return trySignUp()">რეგისტრაცია</button>
            </td>
        </tr>
        </tbody>
    </table>
</form>