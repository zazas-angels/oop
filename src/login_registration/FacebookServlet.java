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
@WebServlet(value = "/fbServlet", name = "fbServlet")
public class FacebookServlet extends HttpServlet {
    /**
     * called while registration
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        DBConnection dbConnection = (DBConnection) context.getAttribute("database");

        String url = request.getParameter("url");
        String name = request.getParameter("name");
        String id = (String) request.getSession().getAttribute("id");
        if (name != null && url != null && checkName(name) && !url.equals("")) {
            User user = new User(name, id, "", url, SiteConstants.Type.facebook);
            dbConnection.addUser(user);

            request.getSession().setAttribute("logged in", true);
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("fbRegistration.jsp").forward(request, response);
        }
    }

    /**
     * as parameter only passed email, so that isn't problem when called doGet and not doPost
     * this method is called while login or start registration
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ServletContext context = request.getServletContext();
        DBConnection dbConnection = (DBConnection) context.getAttribute("database");

        if (dbConnection.existsUser(id)) {
            request.getSession().setAttribute("logged in", true);
            request.getSession().setAttribute("user", dbConnection.getUser(id, ""));
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("id", id);
            request.getRequestDispatcher("fbRegistration.jsp").forward(request, response);
        }
    }
}
