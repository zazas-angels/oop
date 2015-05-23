/**
 * Created by nika on 5/23/15.
 */
function loadRegistrationHtml() {
    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            document.getElementById("login").innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET", "registration.jsp", true);
    xmlhttp.send();
}