<%@ page import="com.google.gson.JsonArray" %>
<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="core.administrator.Administrator" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 6/12/15
  Time: 1:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    Administrator admin = (Administrator) request.getSession().getAttribute("admin");
    ResultSet set = null;
    String toUpdate = request.getParameter("toUpdate");
    if (toUpdate.equals("rep") || toUpdate.equals("wc") || toUpdate.equals("not")) {
        if (toUpdate.equals("rep")) {
            set = admin.getReports(31);// select last
        } else {
            if (toUpdate.equals("wc")) {
                set = admin.getWantedCategories();
            } else {
                set = admin.getNotifications();
            }
        }

        if (set != null) {
            try {
                JsonArray list = new JsonArray();
                JsonObject userObj;
                while (set.next()) {
                    userObj = new JsonObject();
                    if (toUpdate.equals("rep")) {
                        userObj.addProperty("ID", set.getString("ID"));
                        userObj.addProperty("author", set.getString("authorName"));
                        userObj.addProperty("userID", set.getString("authorID"));
                        userObj.addProperty("text", set.getString("text"));
                        userObj.addProperty("date", set.getString("postDate"));
                    } else {
                        //wanted categories
                        if (toUpdate.equals("wc")) {
                            userObj.addProperty("ID", set.getString("ID"));
                            userObj.addProperty("author", set.getString("authorName"));
                            userObj.addProperty("userID", set.getString("authorID"));
                            userObj.addProperty("categoryName", set.getString("categoryName"));
                        } else {
                            userObj.addProperty("author", set.getString("userName"));
                            userObj.addProperty("userID", set.getString("userID"));
                            userObj.addProperty("notification", set.getString("notification"));
                            userObj.addProperty("date", set.getString("postDate"));
                        }
                    }
                    list.add(userObj);
                }
                response.setContentType("application/json; charset=UTF-8");
                out.println(list);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
%>