/**
 * Created by nika on 5/23/15.
 */

$(document).ready(function () {
    $("#login-button").click(function () {
        $('#registration-block').hide();
        $('#authorization-block').toggle();
    });
});

$(document).ready(function () {
    $("#reg-button").click(function () {
        $('#authorization-block').hide();
        $('#registration-block').toggle();
    })
});
/*
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

function loadLoginHtml() {
 /*$.get("login.jsp").done(function(response){
 $('#login').html(response);
 });
 $.ajax * /

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
    xmlhttp.open("GET", "login.jsp", true);
    xmlhttp.send();
}
 */
function passwordChanged() {
    var strength = document.getElementById('strength');
    var strongRegex = new RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$", "g");
    var mediumRegex = new RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$", "g");
    var enoughRegex = new RegExp("(?=.{6,}).*", "g");
    var pwd = document.getElementById("password");
    if (pwd.value.length == 0) {
        strength.innerHTML = 'აკრიფეთ პაროლი';
    } else if (false == enoughRegex.test(pwd.value)) {
        strength.innerHTML = '<span style="color:red">ძალზე მოკლეა</span>';
    } else if (strongRegex.test(pwd.value)) {
        strength.innerHTML = '<span style="color:green">ძლიერი!</span>';
    } else if (mediumRegex.test(pwd.value)) {
        strength.innerHTML = '<span style="color:greenyellow">საშუალო!</span>';
    } else {
        strength.innerHTML = '<span style="color:orange">სუსტი!</span>';
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
        mtch.innerHTML = '<span style="color:green">ემთხვევა</span>';
    } else {
        mtch.innerHTML = '<span style="color:red">არ ემთხვევა</span>';
    }
}

function checkPasswords() {
    var pwd1 = document.getElementById("password");
    var pwd2 = document.getElementById("passwordRepeat");
    var mtch = document.getElementById("matches");
    var strength = document.getElementById('strength');

    return pwd1.value == pwd2.value && pwd1.value.length >= 6;
}


// to change as .domain
var chveniSaiti = ".chveniSaiti.ge";
function checkURL() {
    var url = document.getElementById("url");
    var enoughRegex = new RegExp("(?=.{1,}).*", "g");
    return enoughRegex.test(url.value);
}

function validateEmail(email) {
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    if (re.test(email)) {
        document.getElementById("checkEmail").innerHTML = "";
        return true;
    } else {
        document.getElementById("checkEmail").innerHTML = "არასწორი მეილი";
        return false;
    }
}

function checkMail() {
    var email = document.getElementById("email").value;
    return validateEmail(email);
}

function trySignUp() {
    return checkPasswords() && checkMail() && checkURL();
}

/**
var signinCallback = function signinCallback(authResult) {
    if (authResult['status']['signed_in']) {
        // Update the app to reflect a signed in user
        // Hide the sign-in button now that the user is authorized, for example:
        document.getElementById('signinButton').setAttribute('style', 'display: none');
        console.log('Sign-in state: zaza');
    } else {
        // Update the app to reflect a signed out user
        // Possible error values:
        //   "user_signed_out" - User is signed-out
        //   "access_denied" - User denied access to your app
        //   "immediate_failed" - Could not automatically log in the user
        console.log('Sign-in state: ' + authResult['error']);
    }
}**/