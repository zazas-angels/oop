<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 5/23/15
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<h1>
    Registration
</h1>

<form action="/registration" , method="post">
    <table>
        <tbody>
        <tr>
            <td colspan="3">
                Fill fields:
            </td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" autocomplete="off" id="email" onkeyup="return checkMail()"></td>
            <td>
                <div id="checkEmail"></div>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><a href="#">confirm</a></td>
        </tr>
        <tr>
            <form action="" method="post" id="passwordTest">
                <td>password:</td>
                <td><input type="password" id="password" onkeyup="return passwordChanged()"/></td>
                <td>
                    <div id="strength"></div>
                </td>
            </form>
        </tr>
        <tr>
            <td>repeat:</td>
            <td><input type="password" id="passwordRepeat" onkeyup="return passwordsMatch()"/></td>
            <td>
                <div id="matches"></div>
            </td>
        </tr>
        <tr>
            <td> Url:</td>
            <td><input type="text" autocomplete="off" id="url" onkeyup="return checkURL()"/></td>
            <td>
                <div id="urlCheck"></div>
            </td>
        </tr>
        <tr>
            <td></td>
            <td colspan="2">
                <button name="registration" onclick="return trySignUp()">Sign Up</button>
            </td>
        </tr>
        </tbody>
    </table>
</form>