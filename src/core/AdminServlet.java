package core;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import core.administrator.Administrator;

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
 * Created by nika on 6/6/15.
 */
@WebServlet(value = "/admin", name = "admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String bann = request.getParameter("bann");
        String category = request.getParameter("category");
        String active = request.getParameter("active");
        Administrator admin = (Administrator) request.getSession().getAttribute("admin");
        if (admin != null && name != null && bann != null && category != null && active != null) {

            ResultSet set = admin.findUser(name, bann, active, category);
            if (set != null) {
                try {
                    JsonArray list = new JsonArray();
                    JsonObject userObj;
                    while (set.next()) {
                        userObj = new JsonObject();
                        userObj.addProperty("name", set.getString("name"));
                        userObj.addProperty("url", set.getString("url"));
                        userObj.addProperty("type", set.getString("type"));
                        userObj.addProperty("avatarFile", set.getString("avatarFile"));
                        list.add(userObj);
                    }
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    out.println(list);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
