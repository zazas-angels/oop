/**
 * Created by nika on 6/4/15.
 */


//sending request is from W3School tutorial
//make next categories and make button (set it disabled or not)
function findUsers() {
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

    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            list.innerHTML = xmlHttp.responseText;
        }
    };

    xmlHttp.open("POST", "CategoriesServlet?id=" + id, true);
    xmlHttp.send();
    makeUsersForCategory(id);
    if (id == 0)
        document.getElementById("upButton").disabled = true;
    alert(items.length);
}