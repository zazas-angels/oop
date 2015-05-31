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

/**
 * Created by nika on 5/31/15.
 */
@WebServlet(value = "/FacebookServlet", name = "FacebookServlet")
public class FacebookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FacebookServlet.doPost");
    }

    /**
     * as parameter only passed email, so that isn't problem when called doGet and not doPost
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        ServletContext context = request.getServletContext();
        DBConnection dbConnection = (DBConnection) context.getAttribute("database");

        if (dbConnection.existsUser(email)) {
            request.getSession().setAttribute("logged in", true);
            request.getSession().setAttribute("email", email);
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        } else {
            User user = new User(null, email, null, null, SiteConstants.Type.facebook);
            dbConnection.addUser(user);

            request.getSession().setAttribute("logged in", true);
            request.getSession().setAttribute("email", email);
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        }
    }
}
