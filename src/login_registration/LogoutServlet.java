package login_registration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nika on 5/25/15.
 */
@WebServlet(value = "/logout", name = "logoutServlet")
public class LogoutServlet extends HttpServlet {
    /**
     * sets logged in parameter false, email - null and forwards to homepage
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("logged in", false);
        request.getSession().setAttribute("email", null);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    //ignored
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
