<%@ page import="core.SiteConstants" %>
<%@ page import="core.database.DBConnection" %>
<%@ page import="java.sql.ResultSet" %>
<!--
Created by IntelliJ IDEA.
User: nika
Date: 6/4/15
Time: 5:38 PM
To change this template use File | Settings | File Templates.
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title></title>
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="adminScripts.js"></script>
    <link rel="stylesheet" type="text/css" href="adminPageStyle.css">

    <%if (request.getSession().getAttribute("type") != null && request.getSession().getAttribute("type").equals("superAdmin")) {%>
    <script src="superAdminScripts.js"></script>
    <%}%>
</head>
<body>

<%if (request.getSession().getAttribute("type") != null && request.getSession().getAttribute("type").equals("superAdmin")) {%>
<h1>სუპერ-ადმინისტრატორის გვერდი</h1>
<%} else {%>
<h1>ადმინისტრატორის გვერდი</h1>
<%}%>

<div id="upPanel">
    <div id="logout">
        <%@include file="logout.jsp" %>
    </div>

    <div id="search">
        <div id="searchByName">
            <table class="up-panel-text">
                <tr>
                    <td>სახელი:</td>
                    <td><input id="name" type="text" style="color: black">
                    </td>
                </tr>
                <tr>
                    <td>
                        <a style="color:white" href="#" id="enableExtended">გაფართოებული ძებნა</a>
                    </td>
                    <td>
                        <input type="button" class="btn btn-danger btn-sm" value="მომხმარებლების ძებნა"
                               onclick="searchByName()">
                    </td>
                </tr>
            </table>
        </div>
        <div id="extendedSearch" style="display: none">
            <table class="up-panel-text">
                <tr>
                    <td>სახელი:</td>
                    <td><input type="text" id="nameExtendedSearch" style="color: black"></td>
                </tr>
                <tr>
                    <td>
                        <select id="categoryCombo" style="color: rgba(17, 17, 17, 0.64); max-width: 250px;">
                            <option value="default">აირჩიეთ კატეგორია</option>
                            <%
                                ServletContext context = request.getServletContext();
                                DBConnection database = (DBConnection) context.getAttribute(SiteConstants.DATABASE);
                                ResultSet set = null;
                                set = database.getCategories();
                                if (set != null) {
                                    while (set.next()) {
                                        String category = set.getString("name");
                                        String categoryId = set.getString("ID");
                            %>
                            <option value="<%=categoryId%>"><%=category%>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </td>
                    <td>
                        ბანი:
                        <select id="bannCombo" style="color: rgba(17, 17, 17, 0.64)">
                            <option value="all">ყველა</option>
                            <option value="on">კი</option>
                            <option value="off">არა</option>
                        </select>
                    </td>
                    <td>
                        გააქტიურებულია:
                        <select id="activeCombo" style="color: rgba(17, 17, 17, 0.64)">
                            <option value="all">ყველა</option>
                            <option value="on">კი</option>
                            <option value="off">არა</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><a style="color:white" href="#" id="disableExtended">სახელით ძებნა</a></td>
                    <td></td>
                    <td>
                        <input type="button" class="btn btn-danger btn-sm" value="მომხმარებლების ძებნა"
                               onclick="extendedSearch()">
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<div id="left-panel">
    <h2 class="title">რეპორტები</h2>

    <div id="reports"></div>

    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

    <div>
        <h2 class="title">მოთხოვნილი კატეგორიები</h2>
        <button class="btn btn-primary btn-large" onclick="showAddCategory()">კატეგორიის დამატება</button>
    </div>

    <div id="wantedCategories" style="overflow-y: scroll; max-height:300px;"></div>
</div>
<div id="right-Panel">
    <h2 class="title">სიახლეები</h2>

    <div id="notifications">
    </div>
</div>
<h4><label style="float: left">მომხმარებლები:</label>
    <button type="button" class="btn btn-danger" style="float: left" onclick="showDeleteUser()">წაშლა</button>
    <%if (request.getSession().getAttribute("type") != null && request.getSession().getAttribute("type").equals("superAdmin")) {%>
    <button type="button" class="btn btn-danger" style="float: right" onclick="showDeleteAdmin()">წაშლა</button>
    <button type="button" class="btn btn-success" style="float: right" onclick="showAddAdmin()">დანიშვნა</button>
    <label style="float: right;">ადმინის:</label>
    <%}%>
</h4>

<div id=main-section>
    <script>searchByName();</script>
</div>

<div class="modal fade in" id="bann-section" role="dialog" aria-hidden="false"
     style="display: none; padding-right: 15px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="hideBannSection()">×</button>
                <h4 class="modal-title">ბანის დადება</h4>
            </div>
            <div class="modal-body">
                <label style="margin-right: 70px;">მომხმარებელი:</label><label id="bann-userName">სახელი</label><br>
                <label style="margin-right: 128px;">ლინკი: </label><label id="bann-userLink">სახელი</label><br>
                <label style="margin-right: 40px;" id="bann-userIsActive"></label><br>
                <label style="margin-right: 40px;">მომხმარებლის ტიპი </label><label id="bann-userType"></label><br>
                <label style="margin-right: 10px;">მომხმარებლის რეიტინგი </label><label
                    id="bann-userRating"></label><br>

                <label>ბანის ტიპი:</label>
                <select id="bann-type">
                    <option value="undefined">უვადო</option>
                    <option value="30">30 დღე</option>
                    <option value="10">10 დღე</option>
                </select>
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-primary" value="ბანის დადება" onclick="addBann()">
            </div>
        </div>
    </div>
</div>

<div class="modal fade in" id="addCategory-section" role="dialog" aria-hidden="false"
     style="display: none; padding-right: 15px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="hideAddCategory()">×</button>
                <h4 class="modal-title">კატეგორიის დამატება</h4>
            </div>
            <div class="modal-body">
                <label style="margin-right: 70px;">კატეგორიის სახელი:</label>
                <input type="text" class="form-control" id="newCategotyName" name="username" required=""
                       placeholder="ახალი კატეგორიის სახელი" title="შეიყვანეთ ახალი კატეგორიის სახელი">
                <select id="addCategoryCombo" style="color: rgba(17, 17, 17, 0.64); max-width: 250px !important;">
                    <option value=-1>მშობლის გარეშე</option>
                    <%
                        set = database.getCategories();
                        if (set != null) {
                            while (set.next()) {
                                String category = set.getString("name");
                                String categoryId = set.getString("ID");
                    %>
                    <option value="<%=categoryId%>"><%=category%>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-primary" value="კატეგორიის დამატება" onclick="addCategory()">
            </div>
        </div>
    </div>
</div>
<div class="modal fade in" id="deleteUser-section" role="dialog" aria-hidden="false"
     style="display: none; padding-right: 15px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="hideDeleteUser()">×</button>
                <h4 class="modal-title">მომხმარებლის წაშლა</h4>
            </div>
            <div class="modal-body">
                <label style="margin-right: 80px;">წასაშლელი მომხმარებლის ID:</label>
                <input type="text" class="form-control" id="deleteUserID" name="deleteUser" required=""
                       placeholder="წასაშლელი მომხმარებლის ID" title="წასაშლელი მომხმარებლის ID">
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-success" value="მომხმარებლის წაშლა" onclick="deleteUser()">
            </div>
        </div>
    </div>
</div>

<%if (request.getSession().getAttribute("type") != null && request.getSession().getAttribute("type").equals("superAdmin")) {%>
<div class="modal fade in" id="addAdmin-section" role="dialog" aria-hidden="false"
     style="display: none; padding-right: 15px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="hideAddAdmin()">×</button>
                <h4 class="modal-title">ადმინისტრატორის დამატება</h4>
            </div>
            <div class="modal-body">
                <label style="margin-right: 70px;">ახალი ადმინის ID:</label>
                <input type="text" class="form-control" id="newAdminID" name="newAdmin" required=""
                       placeholder="ახალი ადმინის ID" title="ახალი ადმინის ID">
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-success" value="ადმინის დამატება" onclick="addAdmin()">
            </div>
        </div>
    </div>
</div>
<div class="modal fade in" id="deleteAdmin-section" role="dialog" aria-hidden="false"
     style="display: none; padding-right: 15px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="hideDeleteAdmin()">×</button>
                <h4 class="modal-title">ადმინის წაშლა</h4>
            </div>
            <div class="modal-body">
                <label style="margin-right: 70px;">აირჩიეთ ადმინი</label>
                <select id="adminCombo" style="color: rgba(17, 17, 17, 0.64); max-width: 250px !important;">
                    <option value=-1>აირჩიეთ ადმინი</option>
                    <%
                        set = database.getAdmins();
                        if (set != null) {
                            while (set.next()) {
                                String mail = set.getString("mail");
                                String adminID = set.getString("ID");
                    %>
                    <option value="<%=adminID%>"><%=mail%>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-primary" value="ადმინის წაშლა" onclick="deleteAdmin()">
            </div>
        </div>
    </div>
</div>
<%}%>
<div class="modal-backdrop fade in" id="bann-background" style="display: none;"></div>
</body>
</html>
