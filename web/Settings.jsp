<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="core.database.Connection" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="registration.js"></script>
    <script type="text/javascript" src="Settings.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>პარამეტრების შეცვლა</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="Uploader.css">
    <link rel="stylesheet" type="text/css" href="back.css">

</head>
<body>
<%@ page import="java.sql.ResultSet" %>
<%
    Connection database = (Connection) request.getServletContext()
            .getAttribute("database");
%>
<%
    ResultSet users = database.getUsers((int) request.getSession()
            .getAttribute("userId"));

    if (users != null) {
        if (users.next()) {
%>

<div class="rootContainer">

    <section name="changeParametres" class="banner screen"
             style="height: 1571px;">
        <div class="container">
            <div class="jumbotron shadow transparent" style="padding: 50px; min-height: 400px;">
                <h2>პარამეტრები</h2>
                <h4><a href="#" onclick="showAddCategorySection()">კატეგორიის დამატება</a></h4>

                <br>
                <label>კატეგორიის მოთხოვნა</label><input id = "wc" type="text"><button onclick="addWantedCategory()">მოთხოვნა</button>
                <br>

                <form class="form" action="userPage.jsp" method="" role="form">
                    <div class="row">
                        <div class="col-sm-6">
                            <table>
                                <tr>
                                    <td><label>სახელწოდება: </label></td>
                                    <td><label id="oldName"><%=users.getString("name")%>
                                    </label></td>
                                    <td><a href="#changeName-section" onclick="showChangeName()">
                                        შეცვლა</a></td>
                                </tr>
                                <%
                                    if (users.getString("type").equals("email")) {
                                %>
                                <tr>
                                    <td><label>მეილი: </label></td>
                                    <td><label id="oldMail"><%=users.getString("mail")%></label></td>
                                    <td><a href="#changeMail-section" onclick="showChangeMail()">
                                        შეცვლა</a></td>
                                </tr>
                                <tr>
                                    <td><label>პაროლი: </label></td>
                                    <td><a href="#changePassword-section" onclick="showChangePassword()">
                                        შეცვლა</a></td>
                                </tr>
                                <%
                                    }
                                %>
                                <tr>
                                    <td><label>URL: </label></td>
                                    <td><label id="oldURL"><%=users.getString("url")%></label></td>
                                    <td><a href="#changeURL-section" onclick="showChangeURL()"> შეცვლა</a></td>
                                </tr>
                            </table>
                            <br> <br>
                            <button type="submit" name="register" class="btn btn-default">დადასტურება</button>

                        </div>

                    </div>

                </form>
            </div>
        </div>
    </section>
</div>
<div class="modal fade in" id="changeName-section" role="dialog"
     aria-hidden="false" style="display: none; padding-right: 15px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        onclick="hideChangeName()">×
                </button>
                <h4 class="modal-title">სახელწოდების შეცვლა</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="control-label"> ახალი დასახელება:</label> <input
                        type="text" class="form-control"
                        placeholder=<%=users.getString("name")%> autocomplete="off"
                        id="name" name="name">

                    <div id="checkName"></div>
                </div>
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-primary" value="დადასტურება"
                       onclick="changeName()">
            </div>
        </div>
    </div>
</div>
<div class="modal-backdrop fade in" id="bann-background"
     style="display: none;"></div>

<div class="modal fade in" id="changeMail-section" role="dialog"
     aria-hidden="false" style="display: none; padding-right: 15px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        onclick="hideChangeMail()">×
                </button>
                <h4 class="modal-title">მაილის შეცვლა</h4>
            </div>
            <label id="errorMessageMail"></label>

            <div class="modal-body">
                <div class="form-group">
                    <label class="control-label"> ახალი ელ-ფოსტის მისამართი:</label>

                    <div class="input-group">
                        <span class="input-group-addon">@</span> <input type="text"
                                                                        class="form-control" name="email"
                                                                        autocomplete="off" id="email"
                                                                        placeholder=<%=users.getString("mail")%> onkeyup="checkMails()">

                    </div>
                    <div id="checkEmail"></div>
                </div>
                <div class="form-group">
                    <label class="control-label"> გაიმეორეთ ელ. ფოსტა:</label>

                    <div class="input-group">
                        <span class="input-group-addon">@</span> <input type="text"
                                                                        class="form-control" name="re-email"
                                                                        id="re-email" required=""
                                                                        value="" onkeyup="mailsMatch()">

                    </div>
                    <div id="mailsmatches" style="float: left;"></div>
                </div>
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-primary" value="დადასტურება"
                       onclick="changeMail()">
            </div>
        </div>
    </div>
</div>
<div class="modal-backdrop fade in" id="bann-background"
     style="display: none;"></div>

<div class="modal fade in" id="changePassword-section" role="dialog"
     aria-hidden="false" style="display: none; padding-right: 15px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        onclick="hideChangePassword()">×
                </button>
                <h4 class="modal-title">სახელწოდების შეცვლა</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="control-label"> პაროლი:</label> <input
                        type="password" class="form-control" name="password"
                        id="password" onkeyup="return passwordChanged()">

                    <div id="strength"></div>
                </div>
                <div class="form-group">
                    <label class="control-label"> გაიმეორეთ პაროლი:</label> <input
                        type="password" class="form-control" id="passwordRepeat"
                        name="re-password" onkeyup="return passwordsMatch()">

                    <div id="matches"></div>
                </div>
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-primary" value="დადასტურება"
                       onclick="changePassword()">
            </div>
        </div>
    </div>
</div>
<div class="modal fade in" id="addCategory-section" role="dialog"
     aria-hidden="false" style="display: none; padding-right: 15px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        onclick="hideAddCategorySection()">×
                </button>
                <h4 class="modal-title">კატეგორიის დამატება</h4>
            </div>
            <select id="addCategoryCombo" style="color: rgba(17, 17, 17, 0.64); max-width: 250px !important;">
                <option value=-1>აირჩიეთ კატეგორია</option>
                <%
                    ResultSet set = database.getCategories();
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

            <div class="modal-footer">
                <input type="submit" class="btn btn-primary" value="დამატება"
                       onclick="addCategoryToUser()">
            </div>
        </div>
    </div>
</div>
<div class="modal-backdrop fade in" id="bann-background"
     style="display: none;"></div>

<div class="modal fade in" id="changeURL-section" role="dialog"
     aria-hidden="false" style="display: none; padding-right: 15px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        onclick="hideChangeURL()">×
                </button>
                <h4 class="modal-title">Url</h4>
            </div>
            <label id="errorMessageURL"></label>

            <div class="modal-body">
                <div class="form-group">
                    <label class="control-label"> ახალი Url:</label> <input
                        type="text" class="form-control"
                        placeholder=<%=users.getString("url")%> autocomplete="off"
                        id="url" name="url">

                    <div id="errorMessage"></div>
                </div>
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-primary" value="დადასტურება"
                       onclick="changeURL()">
            </div>
        </div>

    </div>
</div>
<form style="position: absolute; top: 220px; right: 150px;"
      id="uploadBtn">
    <input id="sampleFile" name="sampleFile" type="file"
           accept="image/gif,image/jpeg,image/jpg,image/png"> <br/> <input
        class="gobutton" type="button" value="ატვირთვა"
        onClick="performAjaxSubmit();">

</form>
<div class="modal-backdrop fade in" id="bann-background"
     style="display: none;"></div>

<div
        style="height: 200px; width: 225px; position: absolute; top: 5px; right: 150px;"
        id="image">

    <img style="height: 200px; width: 225px;"
         src="<%=users.getString("avatarFile")%>">
    <%
            }
        }
    %>

</div>


<script type="text/javascript">
    function performAjaxSubmit() {
        //alert("movida");
        var sampleFile = document.getElementById("sampleFile").files[0];

        var formdata = new FormData();

        var image = document.getElementById("image");

        formdata.append("sampleFile", sampleFile);
        //alert(1);
        var xhr;
        var response;
        if (window.XMLHttpRequest) {
            // code for IE7+, Firefox, Chrome, Opera, Safari
            xhr = new XMLHttpRequest();
            //	alert("pirveli");
        } else {
            // code for IE6, IE5
            //alert("meore");
            xhr = new ActiveXObject("Microsoft.XMLHTTP");
        }

        xhr.open("POST", "FileUploader", true);
        xhr.send(formdata);
        //alert(2);
        xhr.onreadystatechange = function (e) {
            if (xhr.readyState == 4 && xhr.status == 200) {

                image.innerHTML = '<img style=\"width: 100%; height: 100%;\" src="data:image/jpg;base64,'
                        + this.responseText + '">';
                saveImage(this.responseText);
            }

        };

    }
    function saveImage(imageText) {
        //alert(imageText);

        $.post("SaveImage", {
            image: imageText,
            view: 0
        }, function (result) {
            //alert(1);
        });

    }
</script>
</body>
</html>