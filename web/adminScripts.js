/**
 * Created by nika on 6/4/15.
 */
$(document).ready(function () {
    $("#enableExtended").click(function () {
        $('#searchByName').hide();
        $('#extendedSearch').toggle();
    });
    $("#disableExtended").click(function () {
        $('#extendedSearch').hide();
        $('#searchByName').toggle();
    });

   // updateReports();
    updateWantedCategories();
    updateNotifications();
    setInterval(function () {
    //    updateReports();
        updateWantedCategories();
        updateNotifications();
    }, 10000);
});

function hideBannSection() {
    $("#bann-section").hide();
    $("#bann-background").hide();
}

function hideAddCategory() {
    $("#addCategory-section").hide();
    $("#bann-background").hide();
}

function showAddCategory() {
    $("#addCategory-section").show();
    $("#bann-background").show();
}


function hideDeleteUser() {
    $("#deleteUser-section").hide();
    $("#bann-background").hide();
}

function showDeleteUser() {
    $("#deleteUser-section").show();
    $("#bann-background").show();
}

function showBannSection(username, url, isActive, type, rating, ID) {
    userID = ID;
    $("#bann-userName").text(username);
    $("#bann-userLink").text(url);
    if (isActive === "active")
        $("#bann-userIsActive").text("გააქტიურებულია");
    else
        $("#bann-userIsActive").text("არაა გააქტიურებული");
    $("#bann-userType").text(type);
    $("#bann-userRating").text("" + rating);
    $("#bann-section").show();
    $("#bann-background").show();
}

function updateNotifications() {
    var link = "wc-rep-not.jsp?toUpdate=not";
    $.get(link)
        .done(function (response) {
            var data = "";
            var arr = response;
            if (arr.length === 0) {
                data = "სიახლე არაა";
            } else {
                for (var i = 0; i < arr.length; i++) {
                    var notification = arr[i];
                    var userID = notification.userID;
                    var tmp = "";
                    console.log(notification.notification);
                    if (notification.notification === "createdUser") {
                        tmp += "ახალი მომხმარებელი: ";
                    } else {
                        if (notification.notification === "deletedUser") {
                            tmp += "წაიშალა მომხმარებელი ";
                        }
                        else {
                            if (notification.notification === "deletedAdmin") {
                                tmp += "წაიშალა ადმინი ";
                            }
                            else {
                                if (notification.notification === "addedAdmin") {
                                    tmp += "დაინიშნა ადმინი ";
                                }
                                else {
                                    tmp += notification.notification + " ";
                                }
                            }
                        }
                    }
                    if (userID == -1) {
                        tmp += notification.author;
                    } else {
                        tmp += "<a href='/UsersForAdmins?ID=" + userID + "'> " + notification.author + "</a>";
                    }
                    data += tmp;
                    data += "<br><br>";
                }
            }
            $('#notifications').html(data);
        });
}

function deleteWantedCategory(wcID) {
    $.post("admin",
        {requestType: "delete-wc", wcID: wcID},
        function (data) {

        }
    );
    updateWantedCategories();
}


function deleteUser() {
    var id = $("#deleteUserID").val();
    if (id.length > 0) {
        $.post("admin",
            {requestType: "deleteUser", userID: id}
        );
        hideDeleteUser();
        setTimeout(function () {
            if ($('#extendedSearch').css('display') == 'none') {
                searchByName();
            } else {
                extendedSearch();
            }
        }, 100);
    }
}


function deleteReport(ID) {
    $.post("admin",
        {requestType: "delete-report", ID: ID},
        function (data) {
        }
    );
    updateReports();
}


function updateWantedCategories() {
    var link = "wc-rep-not.jsp?toUpdate=wc";
    $.get(link)
        .done(function (response) {
            var data = "";
            var arr = response;
            if (arr.length === 0) {
                data = "არაა მოთხოვნა"
            } else {
                for (var i = 0; i < arr.length; i++) {
                    var wantedCategory = arr[i];

                    var tmp = "<div>ავტორი: ";
                    if (wantedCategory.userID == -1) {
                        tmp += wantedCategory.author;
                    } else {
                        tmp += "<a href='/UsersForAdmins?ID=" + wantedCategory.userID + "'>" + wantedCategory.author + "</a>";
                    }
                    tmp += "<br>კატეგორია:   " + wantedCategory.categoryName + "" +
                        "<br>";
                    tmp += "<button onclick=deleteWantedCategory('" + wantedCategory.ID + "');>წაშლა</button></div>";
                    data += tmp;
                    data += "<br><br>"
                }
            }
            $('#wantedCategories').html(data);
        });
}

function updateReports() {
    var link = "wc-rep-not.jsp?toUpdate=rep";
    $.get(link)
        .done(function (response) {
            var data = "";
            var arr = response;
            if (arr.length === 0) {
                data = "არაა რეპორტები"
            } else {
                for (var i = 0; i < arr.length; i++) {
                    var report = arr[i];
                    var tmp = "";
                    if (report.userID == -1) {
                        tmp += report.author + "";
                    } else {
                        tmp += "<a href='/UsersForAdmins?ID=" + report.userID + "'> " + report.author + "</a>";
                    }
                    tmp += "<p>" + report.text + "<br>" + report.date;
                    tmp += "<button onclick=deleteReport('" + report.ID + "');>წაშლა</button></p>";
                    data += tmp;
                    data += "<br>";
                }
            }
            $('#reports').html(data);
        });
}

function searchByName() {
    var link = "admin?name=" + $("#name").val() + "&category=default&bann=all&active=all";
    $.get(link)
        .done(function (response) {
            update(response);
        })
        .fail(function () {
            alert("search fail");
        });
}

function extendedSearch() {
    var link = "admin?name=" + $("#nameExtendedSearch").val() + "&category=" + $("#categoryCombo").val() + "&bann=" + $("#bannCombo").val() + "&active=" + $("#activeCombo").val();
    $.get(link)
        .done(function (response) {
            update(response);
        })
        .fail(function () {
            alert("search fail");
        });
}

function addCategory() {
    var name = $("#newCategotyName").val();
    var parentID = $("#addCategoryCombo").val();
    $.post("admin",
        {requestType: "addCategory", name: name, parentID: parentID},
        function (data) {

        }
    );
    hideAddCategory();
}

var userID;
function addBann() {
    var type = $("#bann-type").val();
    $.post("admin",
        {requestType: "bann", userID: userID, bannType: type},
        function (data) {
            if (data === "added") {
            }
        }
    );
    hideBannSection();
    if ($('#extendedSearch').css('display') == 'none') {
        searchByName();
    } else {
        extendedSearch();
    }
}

function releaseBann(userID) {
    $.post("admin",
        {requestType: "release-bann", userID: userID},
        function (data) {
            if (data === "added") {
            }
        }
    );
    hideBannSection();
    if ($('#extendedSearch').css('display') == 'none') {
        searchByName();
    } else {
        extendedSearch();
    }
}

function update(response) {
    var data = "";
    var arr = response;
    if (arr.length === 0) {
        data = "ასეთი მომხმარებელი არ არსებობს"
    } else {
        data = "<ul>";
        for (var i = 0; i < arr.length; i++) {
            var user = arr[i];
            var url = user.url;
            var tmp = "<li><a class='normal' href='/UsersForAdmins?ID=" + user.ID + "'><img src=\"" + user.avatarFile + "\" alt=\"" + user.name + " \"style=\"width:180px;height:180px\" x='0px' y='0px'></a>";
            tmp += "<div class='info'><h3>"
                + user.name + "</h3><p>"
                + "ID  =  " + user.ID + "<br>"
                + url + "<br>"
                + user.isBanned + "<br>"
                + user.isActive + "<br>"
                + "type: " + user.type + "<br>"
                + "rating " + user.rating + "<br>"
                + "</p></div>";

            if (user.isBanned === "banned") {
                tmp += "<button class='bann-button' onclick=releaseBann('" + user.ID + "');>ბანის მოხსნა</button></li>";
            } else {
                var isActive;
                if (user.isActive === "active") {
                    isActive = "active"; // remove whitespace
                } else {
                    isActive = "notActive";
                }
                tmp += "<button class='bann-button' onclick=showBannSection('" + user.name + "','" + user.url + "','" + isActive + "','" + user.type + "','" + user.rating + "','" + user.ID + "');>ბანის დადება</button></li>";
            }
            data += tmp;
        }
        data += "</ul>" +
            "<script src=\"searchResults.js\"></script><link rel=\"stylesheet\" href=\"searchResults.css\">";
    }
    $('#main-section').html(data);
}