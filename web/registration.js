/**
 * Created by nika on 5/23/15.
 */

$(document).ready(function () {
    $("#login-button").click(function () {
        $('#registration-block').hide();
        $('#authorization-block').toggle();
    });
    $("#reg-button").click(function () {
        $('#authorization-block').hide();
        $('#registration-block').toggle();
    });
});


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
    var enoughRegex = /^[a-zA-Z ]+$/i;
    var bool = enoughRegex.test(url.value);
    if(!bool){
        document.getElementById("errorMessage").innerHTML = "url-ში დაშვებულია მხოლოდ ლათინური სიმბოლოები";
    } else {
        document.getElementById("errorMessage").innerHTML = "";
    }
    return enoughRegex.test(url.value);
}

function checkName() {
    var url = document.getElementById("name");
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
	_gaq.push(['_trackEvent', 'button3', 'clicked']);
    return checkPasswords() && checkMail() && checkURL() && checkName();
}

function trySignUpFb() {
    return checkURL() && checkName();
}
