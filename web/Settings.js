/**
 * 
 */
var imported = document.createElement('script');
imported.src = 'registration.js';
document.head.appendChild(imported);

function mailsMatch() {
    var pwd1 = document.getElementById("email");
    var pwd2 = document.getElementById("re-email");
    var mtch = document.getElementById("mailsmatches");

    if (pwd1.value.length == 0 && pwd1.value.length == 0) {
        mtch.innerHTML = '';
        return false;
    }

    if (pwd1.value == pwd2.value) {
        mtch.innerHTML = '<span style="color:green">ემთხვევა</span>';
        return true;
    } else {
        mtch.innerHTML = '<span style="color:red">არ ემთხვევა</span>';
        return false;
    }
}

function hideChangePassword() {
    $("#changePassword-section").hide();
    $("#bann-background").hide();
}

function showChangePassword() {
    $("#changePassword-section").show();
    $("#bann-background").show();
}

function hideChangeMail() {
    $("#changeMail-section").hide();
    $("#bann-background").hide();
}

function showChangeMail() {
    $("#changeMail-section").show();
    $("#bann-background").show();
}

function hideChangeName() {
    $("#changeName-section").hide();
    $("#bann-background").hide();
}

function showChangeName() {
    $("#changeName-section").show();
    $("#bann-background").show();
}

function changeName(){
    var name = $("#name").val();
    if(checkName()){
	    $.post("Settings",
	        {requestType: "changeName", name: name},
	        function (data) {
	
	        }
	    );
	    hideChangeName();
	}
}

function changeMail(){
    var mail = $("#email").val();
    if(checkMails() && validateEmail(mail)){
	    $.post("Settings",
	        {requestType: "changeMail", mail: mail},
	        function (data) {
	
	        }
	    );
	    hideChangeMail();
    }
}

function changePassword(){
    var paswrd = $("#email").val();
    if(checkPasswords()){
	    $.post("Settings",
	        {requestType: "changePassword", password: paswrd},
	        function (data) {
	
	        }
	    );
	    hideChangePassword();
    }
}

function checkMails() {
    var mail1 = document.getElementById("email");
    var mail2 = document.getElementById("re-email");
    var a = validateEmail(mail1.value);
    return mailsMatch() && a;
}

