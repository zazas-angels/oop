<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="core.user.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="registration.js"></script>
<script type="text/javascript" src="Settings.js"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>პარამეტრების შეცვლა</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="Uploader.css">
<link rel="stylesheet" type="text/css" href="back.css">

</head>
<body>
	<%
		User user = (User) request.getSession().getAttribute("user");
	%>
	<div class="rootContainer">

		<section name="changeParametres" class="banner screen"
			style="height: 1571px;">
		<div class="container">
			<div class="jumbotron shadow transparent" style="padding: 50px;">
				<h2>პარამეტრები</h2>

				<form class="form" action="?p=register" method="post" role="form">
					<div class="row">
						<div class="col-sm-6">
							<label>სახელწოდება: </label><label id="oldName"><%=user.getName()%>,</label><a
								href="#changeName-section" onclick="showChangeName()">
								შეცვლა</a> <br> <label>მეილი: </label><label id="oldMail"><%=user.getEmail()%>,</label><a
								href="#changeMail-section" onclick="showChangeMail()">
								შეცვლა</a> <br> <label>პაროლი: </label><a
								href="#changePassword-section" onclick="showChangePassword()">
								შეცვლა</a> <br> <label>URL: </label><label id="oldURL"><%=user.getURL()%>,</label><a
								href="#changeURL-section" onclick="showChangeURL()"> შეცვლა</a>

							<br> <br>
							<button type="submit" name="register" class="btn btn-default">დადასტურება</button>

						</div>

					</div>

				</form>
			</div>
		</div>
	</div>
	</section>
	<div class="modal fade in" id="changeName-section" role="dialog"
		aria-hidden="false" style="display: none; padding-right: 15px;">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						onclick="hideChangeName()">×</button>
					<h4 class="modal-title">სახელწოდების შეცვლა</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label class="control-label"> ახალი დასახელება:</label> <input
							type="text" class="form-control"
							placeholder=<%=user.getName()%> autocomplete="off" id="name"
							name="name">
						<div id="checkName"></div>
					</div>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" value="დადასტურება"
						onclick="changeName()">
				</div>
			</div>
		</div>
	</div>
	<div class="modal-backdrop fade in" id="bann-background"
		style="display: none;"></div>

	<div class="modal fade in" id="changeMail-section" role="dialog"
		aria-hidden="false" style="display: none; padding-right: 15px;">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						onclick="hideChangeMail()">×</button>
					<h4 class="modal-title">მაილის შეცვლა</h4>
				</div>
				<label id="errorMessageMail"></label>
				<div class="modal-body">
					<div class="form-group">
						<label class="control-label"> ახალი ელ-ფოსტის მისამართი:</label>

						<div class="input-group">
							<span class="input-group-addon">@</span> <input type="text"
								class="form-control" name="email" autocomplete="off" id="email"
								placeholder=<%=user.getEmail()%> onkeyup="checkMails()">

						</div>
						<div id="checkEmail"></div>
					</div>
					<div class="form-group">
						<label class="control-label"> გაიმეორეთ ელ. ფოსტა:</label>

						<div class="input-group">
							<span class="input-group-addon">@</span> <input type="text"
								class="form-control" name="re-email" id="re-email" required=""
								value="" onkeyup="mailsMatch()">

						</div>
						<div id="mailsmatches" style="float: left;"></div>
					</div>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" value="დადასტურება"
						onclick="changeMail()">
				</div>
			</div>
		</div>
	</div>
	<div class="modal-backdrop fade in" id="bann-background"
		style="display: none;"></div>

	<div class="modal fade in" id="changePassword-section" role="dialog"
		aria-hidden="false" style="display: none; padding-right: 15px;">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						onclick="hideChangePassword()">×</button>
					<h4 class="modal-title">სახელწოდების შეცვლა</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label class="control-label"> პაროლი:</label> <input
							type="password" class="form-control" name="password"
							id="password" onkeyup="return passwordChanged()">
						<div id="strength"></div>
					</div>
					<div class="form-group">
						<label class="control-label"> გაიმეორეთ პაროლი:</label> <input
							type="password" class="form-control" id="passwordRepeat"
							name="re-password" onkeyup="return passwordsMatch()">
						<div id="matches"></div>
					</div>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" value="დადასტურება"
						onclick="changePassword()">
				</div>
			</div>
		</div>
	</div>
	<div class="modal-backdrop fade in" id="bann-background"
		style="display: none;"></div>

	<div class="modal fade in" id="changeURL-section" role="dialog"
		aria-hidden="false" style="display: none; padding-right: 15px;">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						onclick="hideChangeURL()">×</button>
					<h4 class="modal-title">Url</h4>
				</div>
				<label id="errorMessageURL"></label>
				<div class="modal-body">
					<div class="form-group">
						<label class="control-label"> ახალი Url:</label> <input
							type="text" class="form-control" placeholder=<%=user.getURL()%>
							autocomplete="off" id="url" name="url">
						<div id="errorMessage"></div>
					</div>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" value="დადასტურება"
						onclick="changeURL()">
				</div>
			</div>

		</div>
	</div>
	<form style="position: absolute; top: 220px; right: 150px;"
		id="uploadBtn">
		<input id="sampleFile" name="sampleFile" type="file"
			accept="image/gif,image/jpeg,image/jpg,image/png"> <br /> <input
			class="gobutton" type="button" value="ატვირთვა"
			onClick="performAjaxSubmit();"></input>

	</form>
	<div class="modal-backdrop fade in" id="bann-background"
		style="display: none;"></div>

	<div
		style="height: 200px; width: 225px; position: absolute; top: 5px; right: 150px;"
		id="image">
		<img style="height: 200px; xwidth: 225px;" src="default.png"></img>
	</div>



	<script type="text/javascript">
		function performAjaxSubmit() {
			//alert("movida");
			var sampleFile = document.getElementById("sampleFile").files[0];

			var formdata = new FormData();

			var image = document.getElementById("image");

			formdata.append("sampleFile", sampleFile);
			//alert(1);
			var xhr;
			var response;
			if (window.XMLHttpRequest) {
				// code for IE7+, Firefox, Chrome, Opera, Safari
				xhr = new XMLHttpRequest();
				//	alert("pirveli");
			} else {
				// code for IE6, IE5
				//alert("meore");
				xhr = new ActiveXObject("Microsoft.XMLHTTP");
			}

			xhr.open("POST", "FileUploader", true);
			xhr.send(formdata);
			//alert(2);
			xhr.onreadystatechange = function(e) {
				if (xhr.readyState == 4 && xhr.status == 200) {

					image.innerHTML = '<img style=\"width: 100%; height: 100%;\" src="data:image/jpg;base64,'
							+ this.responseText + '">';
					saveImage(this.responseText);
				}

			};

		}
		function saveImage(image) {
			alert(image);
			
			var xmlhttp;
			if (window.XMLHttpRequest) {
				// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {
				// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			alert(1);
			xmlhttp.open("POST", "SaveImage", true);
			xmlhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xmlhttp.send("image="+image);

		}
	</script>
</body>
</html>