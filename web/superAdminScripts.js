/**
 * Created by nika on 6/28/15.
 */
function showAddAdmin() {
    $("#addAdmin-section").show();
    $("#bann-background").show();
}
function addAdmin() {
    var id = $("#newAdminID").val();
    if (id.length > 0) {
        $.post("superAdmin",
            {requestType: "addAdmin", userID: id},
            function (data) {
                $("#adminCombo").append('<option value="' + data.ID + '">' + data.mail + '</option>');
            }
        );
        hideAddAdmin();
        setTimeout(function () {
            if ($('#extendedSearch').css('display') == 'none') {
                searchByName();
            } else {
                extendedSearch();
            }
        }, 100);
    }
}
function hideAddAdmin() {
    $("#addAdmin-section").hide();
    $("#bann-background").hide();
}
function showDeleteAdmin() {
    $("#deleteAdmin-section").show();
    $("#bann-background").show();
}
function deleteAdmin() {
    var id = $("#adminCombo").val();
    console.log(id);
    if (id > -1) {
        $.post("superAdmin",
            {requestType: "deleteAdmin", adminID: id},
            function (data) {

            }
        );
        $("#adminCombo option[value=" + id + "]").remove();
        hideDeleteAdmin();

    }
}
function hideDeleteAdmin() {
    $("#deleteAdmin-section").hide();
    $("#bann-background").hide();
}