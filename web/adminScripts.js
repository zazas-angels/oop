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
    updateReports();
    updateWantedCategories();
    updateNotifications();
    setInterval(function () {
        updateReports();
        updateWantedCategories();
        //updateNotifications();
    }, 10000);
});

function updateNotifications() {
    var link = "http://localhost:8080/wc-rep-not.jsp?toUpdate=not";
    $.get(link)
        .done(function (response) {
            var data = "<h2>Notifications</h2>";
            var arr = response;
            if (arr.length === 0) {
                data = "no notification categories"
            } else {
                for (var i = 0; i < arr.length; i++) {
                    var notification = arr[i];
                    var url = notification.url;
                    var tmp = notification.notification + " ";
                    tmp += "author: ";
                    if (url === "#") {
                        tmp += notification.author;
                    } else {
                        tmp += "<a href='" + url + "'> " + notification.author + "</a>";
                    }
                    data += tmp;
                    data += "<br><br>"
                }
            }
            $('#notifications').html(data);
        });
}

function updateWantedCategories() {
    var link = "http://localhost:8080/wc-rep-not.jsp?toUpdate=wc";
    $.get(link)
        .done(function (response) {
            var data = "<h2>Wanted Categories</h2>";
            var arr = response;
            if (arr.length === 0) {
                data = "no wanted categories"
            } else {
                for (var i = 0; i < arr.length; i++) {
                    var wantedCategory = arr[i];
                    var url = wantedCategory.url;
                    var tmp = "author: ";
                    if (url === "#") {
                        tmp += wantedCategory.author;
                    } else {
                        tmp += "<a href='" + url + "'> " + wantedCategory.author + "</a>";
                    }
                    tmp += "<br>wanted categoty:   " + wantedCategory.categoryName + "" +
                        "<br>";
                    if (wantedCategory.parentCategory === null) {
                        tmp += "parent category: none";
                    } else {
                        tmp += "parent categoty: " + wantedCategory.parentCategory + " (ID = " + wantedCategory.parentCategoryID + ")";
                    }
                    data += tmp;
                    data += "<br><br>"
                }
            }
            $('#wantedCategories').html(data);
        });
}

function updateReports() {
    var link = "http://localhost:8080/wc-rep-not.jsp?toUpdate=rep";
    $.get(link)
        .done(function (response) {
            var data = "<h2>Reports</h2>";
            var arr = response;
            if (arr.length === 0) {
                data = "no reports"
            } else {
                for (var i = 0; i < arr.length; i++) {
                    var report = arr[i];
                    var url = report.url;
                    var tmp = "";
                    if (url === "#") {
                        tmp += report.author + "";
                    } else {
                        tmp += "<a href='" + url + "'> " + report.author + "</a>";
                    }
                    tmp += "<p>" + report.text + "<br>" + report.date + "</p>";
                    data += tmp;
                    data += "<br>";
                }
            }
            $('#reports').html(data);
        });
}

function searchByName() {
    var link = "http://localhost:8080/admin?name=" + $("#name").val() + "&category=default&bann=all&active=all";
    $.get(link)
        .done(function (response) {
            update(response);
        })
        .fail(function () {
            alert("fail");
        });
}

function extendedSearch() {
    var link = "http://localhost:8080/admin?name=" + $("#nameExtendedSearch").val() + "&category=" + $("#categoryCombo").val() + "&bann=" + $("#bannCombo").val() + "&active=" + $("#activeCombo").val();
    console.log(link);
    $.get(link)
        .done(function (response) {
            update(response);
        })
        .fail(function () {
            alert("fail");
        });
}

function update(response) {
    var data = "";
    var arr = response;
    console.log(response);
    if (arr.length === 0) {
        data = "ასეთი მომხმარებელი არ არსებობს"
    } else {
        data = "<ul>";
        for (var i = 0; i < arr.length; i++) {
            var user = arr[i];
            var url = user.url;
            var tmp = "<li><a class='normal' href='" + url + "'><img src=\"" + user.avatarFile + "\" alt=\"" + user.name + " \"style=\"width:180px;height:180px\" x='0px' y='0px'></a>";
            tmp += "<div class='info'><h3>" + user.name + "</h3><p>"
                + url + "<br>"
                + user.isBanned + "<br>"
                + user.isActive + "<br>"
                + "type: " + user.type + "<br>"
                + "rating " + user.rating + "<br>"
                + "</p></div></li>";
            data += tmp;
        }
        data += "</ul>" +
            "<script src=\"searchResults.js\"></script><link rel=\"stylesheet\" href=\"searchResults.css\">";
    }
    $('#main-section').html(data);
}