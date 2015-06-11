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
});

/*
 $(document).ready(function () {
 $.get("http://localhost:8080/AjaxTest/Hello").done(function (response) {
 //var message = JSON.parse(response);
 $('#content').html(response.author + ": " + response.message);

 }).fail(function () {
 alert("fail");
 });

 });
 */

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
            console.log(user);
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