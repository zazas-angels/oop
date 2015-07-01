package core;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import core.database.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nika on 7/1/15.
 */
@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("name") != null && request.getParameter("categoryID") != null) {
            String name = request.getParameter("name");
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            DBConnection dbConnection = (DBConnection) request.getServletContext().getAttribute(SiteConstants.DATABASE);
            ResultSet set = null;
            if (categoryID > 0) {
                set = dbConnection.getUsersByCriterias(name, "", "on", "" + categoryID);
            } else {
                set = dbConnection.getUsersByCriterias(name, "", "on", "");
            }
            JsonArray list = new JsonArray();
            JsonObject userObj;
            try {
                while (set.next()) {
                    userObj = new JsonObject();
                    userObj.addProperty("name", set.getString("name"));
                    userObj.addProperty("ID", set.getInt("ID"));
                    userObj.addProperty("url", set.getString("url"));
                    userObj.addProperty("type", set.getString("type"));
                    userObj.addProperty("avatarFile", set.getString("avatarFile"));
                    if (set.getBoolean("isActive")) {
                        userObj.addProperty("isActive", "active");
                    } else {
                        userObj.addProperty("isActive", "not actived");
                    }
                    if (set.getBoolean("isBanned")) {
                        userObj.addProperty("isBanned", "banned");
                    } else {
                        userObj.addProperty("isBanned", "not banned");
                    }
                    list.add(userObj);
                }
                response.setContentType("application/json; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println(list);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
