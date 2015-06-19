<%@ page import="com.google.gson.JsonArray" %>
<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="core.database.DBConnection" %>
<%@ page import="core.user.User" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 6/19/15
  Time: 2:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    User user = (User) request.getSession().getAttribute("user");
    ServletContext context = request.getServletContext();
    DBConnection dbConnection = (DBConnection) context.getAttribute("database");
    String type = request.getParameter("type");
    try {
        if (type.equals("save")) {
            dbConnection.addMarker(request.getParameter("name"), request.getParameter("address"), Double.parseDouble(request.getParameter("lat")), Double.parseDouble(request.getParameter("lang")), user.getID());
            response.getWriter().println("added");
        } else {
            if (type.equals("remove")) {
                dbConnection.removeMarker(Double.parseDouble(request.getParameter("lat")), Double.parseDouble(request.getParameter("lang")), user.getID());
                response.getWriter().println("removed");
            } else {
                if (type.equals("get")) {
                    ResultSet set = dbConnection.getMarkers(user.getID());
                    JsonArray list = new JsonArray();
                    JsonObject userObj;
                    while (set.next()) {
                        userObj = new JsonObject();
                        userObj.addProperty("name", set.getString("name"));
                        userObj.addProperty("desc", set.getString("address"));
                        userObj.addProperty("lat", set.getDouble("lat"));
                        userObj.addProperty("lng", set.getDouble("lng"));
                        list.add(userObj);
                    }
                    PrintWriter out1 = response.getWriter();
                    response.setContentType("application/json");
                    out1.println(list);
                }
            }
        }
    } catch (SQLException e) {
        response.getWriter().println("error");
    }
%>
