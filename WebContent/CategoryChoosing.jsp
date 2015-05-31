<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- Author glaba13 --%>
<%-- Java script fuctions --%>
<%-- dummy --%>
<script src="NextCategories.js"></script>
<script>
	//set for checked id's: this technique as set is from http://stackoverflow.com/questions/7958292/mimicking-sets-in-javascript
	var checkedSet = {};
	/* checking if clicked was reaaly on this div and not on super.
	 * From stack overflow
	 http://stackoverflow.com/questions/2015041/two-differents-onclick-on-two-divs-one-over-the-other
	 */
	function amIclicked(e, id) {
		e = e || event;
		var target = e.target || e.srcElement;
		if (target.id == id)
			return true;
		else
			return false;
	}
	//sending request is from W3School tutorial
	//get expanded categories
	function expandCategory(event, id, hasChilds) {
		if (hasChilds == false)
			return;
		if (!amIclicked(event, id))
			return;

		var element = document.getElementById(id + "");
		//shrink this way
		if (element.getAttribute("isExpanded") == "true") {
			element.setAttribute("isExpanded", "false");
			shrinkCategory(event, id, hasChilds);
			return;
		}
		element.setAttribute("isExpanded", "true");
		//change on click function
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
				element.innerHTML += xmlHttp.responseText;
				var checkedBoxes = element.getElementsByTagName("input");
				//adds checked if it was checked
				for (var i = 0; i < checkedBoxes.length; i++) {
					var checkBox = checkedBoxes[i];
					if (checkBox.getAttribute("id") in checkedSet) {
						checkBox.checked = true;
					}
				}
			}
		};

		xmlHttp.open("POST", "ExpandCategoriesServlet?id=" + id, true);
		xmlHttp.send();
		//we could do so but it will goes for all sub divs and ned more complexity
		//element.onclick=function(){shrinkCategory(event,id,hasChilds); } ;

	}
	//this function shrinksCategory which it was really clicked

	function shrinkCategory(event, id, hasChilds) {
		if (!amIclicked(event, id))
			return;

		var element = document.getElementById(id + "");
		//set true if it was true
		var isChecked = false;
		if (("check" + id) in checkedSet) {
			isChecked = true;
		}
		var parentID = document.getElementById("check" + id).getAttribute(
				"parentId");
		element.innerHTML = element.getAttribute("categoryName")
				+ " <input type=\"checkbox\"" + "  parentId=\"" + parentID
				+ "\" onclick=\"changeCheckedSet(event," + id
				+ ",0);\"  id=\"check" + id + "\">";
		document.getElementById("check" + id).checked = isChecked;

	}
	//This function adds id in checkedList if list is checked or removed it if it's uncheched
	// it contains direct cklick which is 0 if it is really direct chlick
	//it is 1 if it isn't and have to be checked ande for -1 opposite
	function changeCheckedSet(event, id, directClick) {

		if (directClick == 0 && !amIclicked(event, "check" + id))
			return;
		//if it isn't clicked directli and id is 0 (roots parent,which doesn't exists)
		if (id == 0) {//no real id (root's parent)
			return;
		}
		var element = document.getElementById("check" + id);

		var wasChecked = element.getAttribute("id") in checkedSet;
		if (directClick == 1 && !wasChecked) {
			element.checked = true;
		} else {
			if (directClick == -1 && wasChecked)
				element.checked = false;
		}
		var isChecked = element.checked;
		var parentId = element.getAttribute("parentId");
		if (isChecked == true)
			changeCheckedSet(event, parentId, 1);
		makeChangedStructure(id, parentId, isChecked, wasChecked);

	}
	//This function makes checked change for other structures: for set of choosed id's and for adding in added categories view
	function makeChangedStructure(id, parentId, isChecked, wasChecked) {
		if (isChecked && !wasChecked) {
			checkedSet["check" + id] = true;
			var addesSuper;

			if (parentId == 0) {
				addesSuper = document.getElementById("addedCategories");

			} else {
				addesSuper = document
						.getElementById("addedCategory" + parentId)
						.getElementsByTagName("ul")[0];
			}
			addesSuper.innerHTML += "<li id=\"addedCategory"+id+"\"  >"
					+ document.getElementById(id).getAttribute("categoryName")
					+ "  <a onclick=\"makeRemovedChoosedCategy(" + id + ")\" >"
					+ " <b style=\"color:red;cursor:pointer;\">X</b></a>"
					+ "<ul></ul></li>";

		} else {

			if (!isChecked && wasChecked) {
				makeRemovedChoosedCategy(id);
			}
		}

	}
	//This functuon removed added category and unchecked suitable checkboxs and make remove from set
	function makeRemovedChoosedCategy(id) {
		var checkBox = document.getElementById("check" + id);
		if (checkBox != null)
			checkBox.checked = false;
		delete checkedSet["check" + id];
		removeAddedCategory(id);
	}
	//This function just removes category from addedCatgeory view list
	function removeAddedCategory(id) {
		var choosedCategory = document.getElementById("addedCategory" + id);
		var subList = choosedCategory.getElementsByTagName("li");
		//adds checked if it was checked
		while (subList.length != 0) {
			var subCategory = subList[0];//beacause it's removing
			makeRemovedChoosedCategy(subCategory.getAttribute("id").substring(
					13));
		}
		choosedCategory.parentNode.removeChild(choosedCategory);
	}
</script>
<style type="text/css">
.not-active {
	pointer-events: none;
	cursor: default;
}
</style>
<title>Category Chooser</title>
</head>
<body>
	<h1>აირჩიეთ კატეგორია:</h1>
	<%-- shows all root categories --%>
	<%@ page import="core.category.CategoryTreeInterface"%>
	<%@ page import="core.category.CategoryInterface"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.io.PrintWriter"%>
	<%
		CategoryTreeInterface categories = (CategoryTreeInterface) request
				.getServletContext().getAttribute("categories");
		List<CategoryInterface> roots = categories.getRoots();
		PrintWriter writer = response.getWriter();
	%>
	<%="<ul id=\"categories\" >"%>
	<%
		for (int i = 0; i < roots.size(); i++) {
	%>
	<%="<li>"%>
	<%
		int id = roots.get(i).getId();
			String categoryName = roots.get(i).getName();
			//reaaly every root has chils , but work for every case
	%>
	<%="<div style=\"cursor:pointer;\" id =\""
						+ id
						+ "\"  categoryName=\""
						+ categoryName
						+ "\"  isExpanded = \"false\"  onclick=\"expandCategory(event,"
						+ id
						+ ",true);\"> "
						+ categoryName
						+ " <input type=\"checkbox\" parentId=\"0\" onclick=\"changeCheckedSet(event,"
						+ id + ",0);\"  id=\"check" + id + "\">"%>
	<%="</li>"%>
	<%
		}
	%>
	<%="</ul>"%>
	<h1>არჩეული კატეგორიები:</h1>
	<ul id="addedCategories"></ul>
</body>
</html>