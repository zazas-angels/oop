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
    debugger;
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
    debugger;
    var data = "";
    var arr = response;
    console.log(response);
    if (arr.length === 0) {
        data = "ასეთი მომხმარებელი არ არსებობს"
    }
    for (var i = 0; i < arr.length; i++) {
        var user = arr[i];
        var url = user.url;
        var tmp = "<div class= \"col span_1_of_4\">" + user.name + "<br>";
        tmp += "<a href=\"" + user.url + "\">"
        tmp += "<img src=\"" + user.avatarFile + "\" alt=\"" + user.name + "\" style=\"width:150px;height:150px;\"></a></div>";
        data += tmp;
    }
    $('#main-section').html(data);
}