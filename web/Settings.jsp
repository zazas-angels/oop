<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="core.user.User"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"><head>
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type = "text/javascript"  src="registration.js"></script>
	<script type = "text/javascript"  src="Settings.js"></script>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>პარამეტრების შეცვლა</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	
	
	
</head>
<body>
<% 	User user = (User)request.getSession().getAttribute("user"); %>
<div class="rootContainer">

	<section name="changeParametres" class="banner screen" style="height: 1571px;">
	<div class="container">
		<div class="jumbotron shadow transparent" style="padding: 50px;">
			<h2>პარამეტრები</h2>

			<form class="form" action="?p=register" method="post" role="form" >
				<div class="row">
					<div class="col-sm-6">
					<label>სახელწოდება:    </label><label id = "oldName"><%= user.getName() %>,</label><a href="#changeName-section" onclick="showChangeName()"> შეცვლა</a>
						<br>
					<label>მეილი:    </label><label id = "oldMail"><%= user.getEmail() %>,</label><a href="#changeMail-section" onclick="showChangeMail()"> შეცვლა</a>
						<br>
					<label>პაროლი:    </label><a href="#changeMail-section" onclick="showChangePassword()"> შეცვლა</a>
						
						
						<br><br>
						<button type="submit" name="register" class="btn btn-default" >დადასტურება</button>
						
					</div>
					
				</div>

			</form>
		</div>
	</div>
</div>
</section>
<div class="modal fade in" id="changeName-section" role="dialog" aria-hidden="false"
     style="display: none; padding-right: 15px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="hideChangeName()">×</button>
                <h4 class="modal-title">სახელწოდების შეცვლა</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
							<label class="control-label"> ახალი დასახელება:</label>
							<input type="text" class="form-control" placeholder=<%= user.getName() %>  autocomplete="off" id="name" name="name">
							<div id="checkName"></div>
				</div>
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-primary" value="დადასტურება" onclick="changeName()">
            </div>
        </div>
    </div>
</div>
<div class="modal-backdrop fade in" id="bann-background" style="display: none;"></div>

<div class="modal fade in" id="changeMail-section" role="dialog" aria-hidden="false"
     style="display: none; padding-right: 15px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="hideChangeMail()">×</button>
                <h4 class="modal-title">მაილის შეცვლა</h4>
            </div>
            <label id="errorMessageMail"></label>
            <div class="modal-body">
                <div class="form-group">
							<label class="control-label"> ახალი ელ-ფოსტის მისამართი:</label>

							<div class="input-group">
								<span class="input-group-addon">@</span>
								<input type="text" class="form-control" name="email" autocomplete="off" id="email" placeholder=<%=user.getEmail() %> onkeyup="checkMails()" >
								
							</div>
							<div id="checkEmail"> </div>
						</div>
						<div class="form-group">
							<label class="control-label"> გაიმეორეთ ელ. ფოსტა:</label>

							<div class="input-group">
								<span class="input-group-addon">@</span>
								<input type="text" class="form-control" name="re-email" id="re-email"  required="" value="" onkeyup="mailsMatch()">
								
							</div>
							<div id="mailsmatches" style = "float:left;"></div>
						</div>
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-primary" value="დადასტურება" onclick="changeMail()">
            </div>
        </div>
    </div>
</div>
<div class="modal-backdrop fade in" id="bann-background" style="display: none;"></div>

<div class="modal fade in" id="changePassword-section" role="dialog" aria-hidden="false"
     style="display: none; padding-right: 15px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="hideChangePassword()">×</button>
                <h4 class="modal-title">სახელწოდების შეცვლა</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
					<label class="control-label"> პაროლი:</label>
					<input type="password" class="form-control" name="password" id="password" onkeyup="return passwordChanged()" >
					<div id="strength"></div>
				</div>
				<div class="form-group">
					<label class="control-label"> გაიმეორეთ პაროლი:</label>
					<input type="password" class="form-control" id="passwordRepeat" name="re-password" onkeyup="return passwordsMatch()" >
					<div id="matches"></div>
				</div>
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-primary" value="დადასტურება" onclick="changePassword()">
            </div>
        </div>
    </div>
</div>
<div class="modal-backdrop fade in" id="bann-background" style="display: none;"></div>
</body></html>