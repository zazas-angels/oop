package core;

import com.google.gson.JsonObject;
import core.administrator.Administrator;
import core.administrator.SuperAdministrator;
import core.category.CategoryTree;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by nika on 6/28/15.
 */
@WebServlet(value = "/superAdmin", name = "superAdmin")
public class SuperAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SuperAdminServlet.doPost");
        String type = request.getParameter("requestType");
        if (type != null) {
            SuperAdministrator admin = (SuperAdministrator) request.getSession().getAttribute("admin");
            switch (type){
                case "addAdmin":
                    Administrator administrator = admin.setAdmin(Integer.parseInt(request.getParameter("userID")), (CategoryTree) request.getServletContext().getAttribute(SiteConstants.CATEGORY_TREE));
                    PrintWriter out = response.getWriter();
                    JsonObject obj = new JsonObject();
                    obj.addProperty("ID", administrator.getId());
                    obj.addProperty("mail", administrator.getEmail());
                    response.setContentType("application/json; charset=UTF-8");
                    out.println(obj);
                    break;
                case "deleteAdmin":
                    admin.deleteAdmin(Integer.parseInt(request.getParameter("adminID")));
                    break;
                default:
                    System.out.println("zazaaaaa");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
