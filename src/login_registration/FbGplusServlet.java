package login_registration;

import core.SiteConstants;
import core.database.DBConnection;
import core.user.User;

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
            User user = new User(name, id, "", url, tp);
            dbConnection.addUser(user);
            request.getSession().setAttribute("logged in", true);
            request.getSession().setAttribute("user", user);
            LoginServlet.addCookie(request, response, context, user);
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
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
        DBConnection dbConnection = (DBConnection) context.getAttribute("database");

        // in our base id must be unique, but we have not guarantee that fb user id != g+ user id, so i do this..
        if (type.equals("fb")) {
            id = "fb" + id;
        } else {
            id = "gp" + id;
        }

        if (dbConnection.existsUser(id)) {
            request.getSession().setAttribute("logged in", true);
            User user = (User) dbConnection.getUser(id, User.generatePassword(""));
            request.getSession().setAttribute("user", user);
            LoginServlet.addCookie(request, response, context, user);
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("id", id);
            if (type.equals("fb")) {
                request.getSession().setAttribute("type", type);
            } else {
                request.getSession().setAttribute("type", type);
            }
            request.getRequestDispatcher("fbG+Registration.jsp").forward(request, response);
        }
    }
}
