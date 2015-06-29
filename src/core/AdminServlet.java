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
        String type = request.getParameter("requestType");
        if (type != null) {
            Administrator admin = (Administrator) request.getSession().getAttribute("admin");
            switch (type){
                case "bann":
                    addBann(admin, request.getParameter("bannType"), request.getParameter("userID"));
                    break;
                case "release-bann":
                    admin.releaseBann(Integer.parseInt(request.getParameter("userID")));
                    break;
                case "delete-wc":
                    admin.deleteWantedCategory(Integer.parseInt(request.getParameter("wcID")));
                    break;
                case "delete-report":
                    admin.deleteReport(Integer.parseInt(request.getParameter("ID")));
                    break;
                case "deleteUser":
                    admin.deleteUser(Integer.parseInt(request.getParameter("userID")));
                    break;
                case "addCategory":
                    try {
                        admin.addCategory(request.getParameter("name"), Integer.parseInt(request.getParameter("parentID")));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                default:
                    System.out.println("zazaaaaa");
            }
        }
    }

    private void addBann(Administrator administrator, String bannType, String userID) {
        if (bannType.equals("undefined")) {
            administrator.bannUser(Integer.parseInt(userID));
        } else {
            administrator.bannUser(Integer.parseInt(userID), Integer.parseInt(bannType));
        }
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

                        // bazashi rating-is nacvlad raiting weria..!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        userObj.addProperty("rating", set.getDouble("raiting"));
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
}
