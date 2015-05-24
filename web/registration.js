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

function passwordChanged() {
    var strength = document.getElementById('strength');
    var strongRegex = new RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$", "g");
    var mediumRegex = new RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$", "g");
    var enoughRegex = new RegExp("(?=.{6,}).*", "g");
    var pwd = document.getElementById("password");
    if (pwd.value.length == 0) {
        strength.innerHTML = 'Type Password';
    } else if (false == enoughRegex.test(pwd.value)) {
        strength.innerHTML = '<span style="color:red">very short</span>';
    } else if (strongRegex.test(pwd.value)) {
        strength.innerHTML = '<span style="color:green">Strong!</span>';
    } else if (mediumRegex.test(pwd.value)) {
        strength.innerHTML = '<span style="color:greenyellow">Medium!</span>';
    } else {
        strength.innerHTML = '<span style="color:orange">Weak!</span>';
    }
    passwordsMatch();
}

function passwordsMatch() {
    var pwd1 = document.getElementById("password");
    var pwd2 = document.getElementById("passwordRepeat");
    var mtch = document.getElementById("matches");

    if (pwd1.value.length == 0 && pwd1.value.length == 0) {
        mtch.innerHTML = '';
        return;
    }

    if (pwd1.value == pwd2.value) {
        mtch.innerHTML = '<span style="color:green">matches</span>';
    } else {
        mtch.innerHTML = '<span style="color:red">does not match</span>';
    }
}