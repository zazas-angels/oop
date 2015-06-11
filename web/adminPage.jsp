<%@ page import="core.SiteConstants" %>
<%@ page import="core.database.DBConnection" %>
<%@ page import="java.sql.ResultSet" %>
<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 6/4/15
  Time: 5:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="adminScripts.js"></script>
    <link rel="stylesheet" type="text/css" href="adminPageStyle.css">
</head>
<body>
<h1>გამარჯობა შე მსუქანო ადმინისტრატორო</h1>

<div id="logout">
    <%@include file="logout.jsp" %>
</div>

<div id="search">
    <div id="searchByName">
        <table>
            <tr>
                <td>სახელი:</td>
                <td><input id="name" type="text">
                </td>
            </tr>
            <tr>
                <td>
                    <a href="#" id="enableExtended">გაფართოებული ძებნა</a>
                </td>
                <td>
                    <input type="button" value="ძებნა" onclick="searchByName()">
                </td>
            </tr>
        </table>
    </div>
    <div id="extendedSearch" style="display: none">
        <table>
            <tr>
                <td>სახელი:</td>
                <td><input type="text" id="nameExtendedSearch"></td>
            </tr>
            <tr>
                <td>
                    <select id="categoryCombo">
                        <option value="default">აირჩიეთ კატეგორია</option>
                        <%
                            ServletContext context = request.getServletContext();
                            DBConnection database = (DBConnection) context.getAttribute(SiteConstants.DATABASE);
                            ResultSet set = database.getCategories();
                            while (set.next()) {
                                String category = set.getString("name");
                                String categoryId = set.getString("ID");
                        %>
                        <option value="<%=categoryId%>"><%=category%>
                        </option>
                        <%
                            }
                        %>
                    </select>
                </td>
                <td>
                    ბანი:
                    <select id="bannCombo">
                        <option value="all">ყველა</option>
                        <option value="on">კი</option>
                        <option value="off">არა</option>
                    </select>
                </td>
                <td>
                    გააქტიურებულია:
                    <select id="activeCombo">
                        <option value="all">ყველა</option>
                        <option value="on">კი</option>
                        <option value="off">არა</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><a href="#" id="disableExtended">სახელით ძებნა</a></td>
                <td></td>
                <td>
                    <input type="button" value="ძებნა" onclick="extendedSearch()">
                </td>
            </tr>
        </table>
    </div>
</div>


<div id="left-panel">
    <div id="reports">
        <h2>reports</h2></div>
    <div id="wantedCategories">
        <h2>wanted categories</h2>
    </div>
</div>
<div id="notifications">
    <h2>notifications</h2></div>
<div id=main-section>
    <h2> main section </h2>
    aasdasdas
    adasdasd
    asdasdasdas
    das
    dasd<br> aasdasdas
    adasdasd
    asdasdasdas
    das
    dasd<br> aasdasdas
    adasdasd
    asdasdasdas
    das
    dasd<br> aasdasdas
    adasdasd
    asdasdasdas
    das
    dasd<br> aasdasdas
    adasdasd
    asdasdasdas
    das
    dasd<br> aasdasdas
    adasdasd
    asdasdasdas
    das
    dasd<br> aasdasdas
    adasdasd
    asdasdasdas
    das
    dasd<br> aasdasdas
    adasdasd
    asdasdasdas
    das
    dasd<br> aasdasdas
    adasdasd
    asdasdasdas
    das
    dasd<br> aasdasdas
    adasdasd
    asdasdasdas
    das
    dasd<br> aasdasdas
    adasdasd
    asdasdasdas
    das
    dasd<br> aasdasdas
    adasdasd
    asdasdasdas
    das
    dasd<br>
</div>


</body>
</html>
