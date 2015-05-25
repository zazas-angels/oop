package login_registration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nika on 5/24/15.
 */
@WebServlet(value = "/registration", name = "registrationServlet")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  /*      String password = LoginServlet.generateHash("/" + request.getParameter("password"));
        String email = request.getParameter("email");
        ServletContext context = request.getServletContext();
        DBConnection dbConnection = (DBConnection) context.getAttribute("database");

        if (dbConnection.existsUser(email)) {
            request.setAttribute("email busy", true);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        } else {
            //R regExp = new RegExp("(?=.{6,}).*", "g");
        }
        /*
        request.getSession().setAttribute("logged in", true);
        request.getSession().setAttribute("email", email);
        request.getRequestDispatcher("userPage.jsp").forward(request, response);*/
        System.out.println("RegistrationServlet.doPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RegistrationServlet.doGet");
    }
}
