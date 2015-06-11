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
    setInterval(function () {
        updateReports()
    }, 10000);
});

function updateReports() {
    var link = "http://localhost:8080/reports.jsp";
    $.get(link)
        .done(function (response) {
            var data = "";
            var arr = response;
            if (arr.length === 0) {
                data = "no reports"
            } else {
                data = "";
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
                }
                data += "<br><br><br>";
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