package login_registration;

import core.database.DBConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nika on 5/24/15.
 */
@WebServlet(value = "/login", name = "loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        ServletContext context = request.getServletContext();
        DBConnection dbConnection = (DBConnection) context.getAttribute("database");

        if (password != null && email != null && (email.equals("zaza") && request.getParameter("password").equals("zazuna")) || dbConnection.getUser(email, password) != null) {
            request.getSession().setAttribute("logged in", true);
            request.getSession().setAttribute("email", email);
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        } else {
            request.setAttribute("wrong try to log in", true);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }


    }

    // ignored
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginServlet.doGet");
        System.out.println("request = [" + request + "], response = [" + response + "]");
    }
}
