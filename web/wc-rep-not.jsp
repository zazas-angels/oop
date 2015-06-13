<%@ page import="com.google.gson.JsonArray" %>
<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="core.administrator.Administrator" %>
<%@ page import="java.io.PrintWriter" %>
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
            set = admin.getReports(31);
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
                        userObj.addProperty("author", set.getString("authorName"));
                        userObj.addProperty("url", set.getString("authorUrl"));
                        userObj.addProperty("text", set.getString("text"));
                        userObj.addProperty("date", set.getString("postDate"));
                    } else {
                        //wanted categories
                        if (toUpdate.equals("wc")) {
                            userObj.addProperty("author", set.getString("authorName"));
                            userObj.addProperty("url", set.getString("authorUrl"));
                            userObj.addProperty("categoryName", set.getString("categoryName"));
                            userObj.addProperty("date", set.getString("postDate"));
                            userObj.addProperty("parentCategory", set.getString("name"));
                            userObj.addProperty("parentCategoryID", set.getString("parentCategoryID"));
                        } else {
                            userObj.addProperty("author", set.getString("userName"));
                            userObj.addProperty("url", set.getString("userUrl"));
                            userObj.addProperty("notification", set.getString("notification"));
                            userObj.addProperty("date", set.getString("postDate"));
                        }
                    }
                    list.add(userObj);
                }
                PrintWriter out1 = response.getWriter();
                response.setContentType("application/json");
                out1.println(list);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
%>