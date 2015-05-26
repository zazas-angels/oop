/**
 * Javascript function for getting child categories
 * Author:glaba13
 */
	function makeNextCategories(id) {
		alert('it works!');
		var list = document.getElementById("categories");

		var xmlHttp;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlHttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				list.innerHTML = xmlHttp.responseText;
			}
		};

		xmlHttp.open("POST", "CategoriesServlet?id=" + id, true);
		xmlHttp.send();
		makeUsersForCategory(id);

		alert(items.length);
	}

	function makeUsersForCategory(id) {
		alert('users works!');
		var list = document.getElementById("users");
		var xmlHttp;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlHttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				list.innerHTML = xmlHttp.responseText;
			}
		};

		xmlHttp.open("POST", "UsersServlet?categoryId=" + id, true);
		xmlHttp.send();
	}