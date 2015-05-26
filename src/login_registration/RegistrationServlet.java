package login_registration;

import core.database.DBConnection;
import core.user.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nika on 5/24/15.
 */
@WebServlet(value = "/registration", name = "registrationServlet")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        ServletContext context = request.getServletContext();
        DBConnection dbConnection = (DBConnection) context.getAttribute("database");


        String url = request.getParameter("url");
        if (password != null && email != null && url != null && checkMail(email) && checkPassword(password) && url != "") {
            if (dbConnection.existsUser(email)) {
                request.getSession().setAttribute("busy email", email);
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            } else {
                User user = new User(email, password, url);
                dbConnection.addUser(user);

                request.getSession().setAttribute("logged in", true);
                request.getSession().setAttribute("email", email);
                request.getRequestDispatcher("userPage.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ignored
        System.out.println("RegistrationServlet.doGet");
    }

    private boolean checkPassword(String password) {
        return enoughLength(password);
    }

    private boolean checkMail(String email) {
        return rightEmailStructure(email);
    }

    private boolean rightEmailStructure(String email) {
        String patternString = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$";
        return checkRegEx(patternString, email);
    }

    private boolean enoughLength(String text) {
        String patternString = "(?=.{1,}).*";
        return checkRegEx(patternString, text);
    }

    private boolean checkRegEx(String patternString, String text) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
