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
    ResultSet set = admin.getReports(31);

    if (set != null) {
        try {
            JsonArray list = new JsonArray();
            JsonObject userObj;
            while (set.next()) {
                userObj = new JsonObject();
                userObj.addProperty("author", set.getString("authorName"));
                userObj.addProperty("url", set.getString("authorUrl"));
                userObj.addProperty("text", set.getString("text"));
                userObj.addProperty("date", set.getString("postDate"));
                list.add(userObj);
            }
            PrintWriter out1 = response.getWriter();
            response.setContentType("application/json");
            out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>