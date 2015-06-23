package login_registration;

import core.SiteConstants;
import core.administrator.Administrator;
import core.category.CategoryTree;
import core.database.DBConnection;
import core.user.User;
import core.user.UserInterface;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static login_registration.RegistrationServlet.checkName;

/**
 * Created by nika on 5/31/15.
 */
@WebServlet(value = "/fbG+Servlet", name = "fbG+Servlet")
public class FbGplusServlet extends HttpServlet {
    /**
     * called while registration
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        DBConnection dbConnection = (DBConnection) context.getAttribute("database");

        String url = request.getParameter("url");
        String name = request.getParameter("name");
        String id = (String) request.getSession().getAttribute("id");
        String type = (String) request.getSession().getAttribute("type");
        if (name != null && url != null && checkName(name) && !url.equals("")) {
            SiteConstants.Type tp = SiteConstants.getType(type);
            User user = dbConnection.addUser(name, id, "", url, SiteConstants.getType(type));
            loginUser(user, request, response, context);
        } else {
            request.getRequestDispatcher("fbG+Registration.jsp").forward(request, response);
        }
    }

    /**
     * as parameter only passed email, so that isn't problem when called doGet and not doPost
     * this method is called while login or start registration
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        ServletContext context = request.getServletContext();
        DBConnection dbConnection = (DBConnection) context.getAttribute(SiteConstants.DATABASE);
        CategoryTree categoryTree = (CategoryTree) context.getAttribute(SiteConstants.CATEGORY_TREE);

        // in our base id must be unique, but we have not guarantee that fb user id != g+ user id, so i do this..
        if (type.equals("fb")) {
            id = "fb" + id;
        } else {
            id = "gp" + id;
        }

        if (dbConnection.existsUser(id)) {
            User user = (User) dbConnection.getUser(id, "");
            loginUser(user, request, response, context);
        } else {
            if (dbConnection.existsAdministrator(id)) {
                Administrator admin = (Administrator) dbConnection.getAdmin(id, "", categoryTree);
                loginAdmin(admin, request, response, context);

            } else {
                request.getSession().setAttribute("id", id);
                request.getSession().setAttribute("type", type);
                request.getRequestDispatcher("fbG+Registration.jsp").forward(request, response);
            }
        }
    }

    /**
     * logs admin in..
     */
    private void loginAdmin(Administrator admin, HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        request.getSession().setAttribute("logged in", true);
        request.getSession().setAttribute("admin", admin);
        LoginServlet.addCookie(request, response, context, admin);
        //LoginServlet.addCookie(request, response, context, admin);
        request.getRequestDispatcher("adminPage.jsp").forward(request, response);
    }

    /**
     * logs user in, saves in cookies.
     */
    private void loginUser(UserInterface user, HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        request.getSession().setAttribute("logged in", true);
        request.getSession().setAttribute("user", user);
        LoginServlet.addCookie(request, response, context, user);
        request.getRequestDispatcher("userPage.jsp").forward(request, response);
    }
}