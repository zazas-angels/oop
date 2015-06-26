function changeRating(num) {
	var element = document.getElementById("rate");
	var numVote = +element.getAttribute("numVote") + 1;
	var sumVote = +element.getAttribute("sumVote") + +num;
	element.setAttribute("numVote", numVote);
	element.setAttribute("sumVote", sumVote);
	for (var i = 1; i <= 5; i++) {
		if (sumVote / numVote >= i) {
			document.getElementById("star" + i).className = "fa fa-star filled";
		} else {
			document.getElementById("star" + i).className = "fa fa-star";
		}

	}
	//var sumVote=parseInt(element.sumVote);
	save();
	alert(numVote);
	alert(sumVote);
}

function comment(id) {
	alert(1);
	numElements += 1;
	var text = document.getElementById("textcom" + id).value;
	var name = document.getElementById("input" + id).value;
	if (text == "" || name == "")
		return;
	var elem = '<p id="comment' + numElements
			+ '" class="bubbleLeft2"><a class="name">' + name + '</a> ' + text
			+ ' <opac><a onclick="$(' + "comment" + numElements
			+ ').remove();" class="close" style="float: right;">×</a><opac>'
			+ '</p>'
	document.getElementById("comments" + id).innerHTML += elem;
	if (viewMode) {
		var elements = document.getElementsByTagName("opac");
		for (var i = 0; i < elements.length; i++) {
			var element = elements[i];
			element.style.visibility = "hidden";
		}
	}

	save();

}
function save() {
	alert(0);

	var elements = document.getElementsByTagName("input");
	for (var i = 0; i < elements.length; i++) {
		var element = elements[i];
		if (element.className != "comment") {
			element.setAttribute("val", element.value);
		}
	}
	elements = document.getElementsByTagName("textarea");
	for (var i = 0; i < elements.length; i++) {
		var element = elements[i];
		if (element.className != "comment") {
			element.setAttribute("val", element.value);
		}
	}

	elements = document.getElementsByTagName("select");
	for (var i = 0; i < elements.length; i++) {
		var element = elements[i];
		element.setAttribute("val", element.value);
	}

	var txt = document.body.innerHTML;
	alert(txt);
	$.post("UserPageData", {
		data : txt,
		view : 0
	}, function(result) {
		alert(1);
	});
}
function view() {
	viewMode = true;
	var elements = document.getElementsByTagName("div");
	alert("len: " + elements.length)
	for (var i = 0; i < elements.length; i++) {
		var element = elements[i];
		//var innerEl = element.getElementsByTagName("innerElement");
		alert(element.className)
		if (element.className == "drsMoveHandle") {
			element.className = "dummyHand";
		}
		if (element.className == "drsElement") {
			alert("opa");
			element.style.visibility = "hidden";
			//innerEl.style.visible="visible";
			element.getElementsByTagName("innerElement")[0].style.visibility = "visible";
			element.className = "dummyElem";
		}
	}
	elements = document.getElementsByTagName("opac");
	for (var i = 0; i < elements.length; i++) {
		var element = elements[i];
		element.style.visibility = "hidden";
	}
	elements = document.getElementsByTagName("textArea");
	for (var i = 0; i < elements.length; i++) {
		var element = elements[i];
		if (element.className == "comment") {
			element.value = "";
		}
		if (element.className == "textF") {
			element.readOnly = true;
		}
	}
	elements = document.getElementsByTagName("input");
	for (var i = 0; i < elements.length; i++) {
		var element = elements[i];
		if (element.className == "comment") {
			element.value = "";
		}
	}

	document.getElementById("control").style.visibility = "hidden";

	document.getElementById("themeselect").style.visibility = "hidden";
	document.getElementById("edit").style.visibility = "visible";
	document.getElementById("addSub").style.visibility = "hidden";

}