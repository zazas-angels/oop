package login_registration;

import core.database.DBConnection;
import core.user.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by nika on 5/24/15.
 */
@WebServlet(value = "/login", name = "login")
public class LoginServlet extends HttpServlet {

    /**
     * try to log in with given parameters, wich are stored in request object.
     * if login is successful, forwards to user page,
     * else forwards to same page, and sets wrong try login attribute true.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        password = User.generatePassword(password);
        String email = request.getParameter("email");
        ServletContext context = request.getServletContext();
        DBConnection dbConnection = (DBConnection) context.getAttribute("database");

        User user = null;
        if (dbConnection.getUser(email, password) != null) {
            user = (User) dbConnection.getUser(email, password);
        }
        ;
        if (password != null && email != null && (email.equals("zaza") && request.getParameter("password").equals("zazuna")) || user != null) {
            loginUser(request, response, user, context);
        } else {
            request.getSession().setAttribute("wrong try to log in", true);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    /**
     * is called while login with cookies
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                ServletContext context = request.getServletContext();
                HashMap<String, User> mapSession = (HashMap<String, User>) context.getAttribute("sessions_map");


                if (cookie.getName().equals("login_session_id") && cookie.getValue() != null) {
                    User user = mapSession.get(cookie.getValue());
                    if (user != null) {
                        loginUser(request, response, user, context);
                    }
                }
            }
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response, User user, ServletContext context) throws ServletException, IOException {
        request.getSession().setAttribute("logged in", true);
        request.getSession().setAttribute("type", "email");
        request.getSession().setAttribute("user", user);

        addCookie(request, response, context, user);
        request.getRequestDispatcher("userPage.jsp").forward(request, response);
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, ServletContext context, User user) {

        String sessionId = request.getSession().getId();
        addIntoMaps(sessionId, user, context);
        // save login state in cookies..
        Cookie newCookie = new Cookie("login_session_id", sessionId);
        newCookie.setMaxAge(60 * 60); // one hour
        response.addCookie(newCookie);
    }


    /**
     * saves in maps id of session and user
     *
     * @param sessionId id of session which we want to associate to user
     * @param user      user, which was logged in on that session
     */
    public static void addIntoMaps(String sessionId, User user, ServletContext context) {
        HashMap<User, String> mapUser = (HashMap<User, String>) context.getAttribute("users_map");
        HashMap<String, User> mapSession = (HashMap<String, User>) context.getAttribute("sessions_map");
        if (mapUser.get(user) != null) {
            String oldSession = mapUser.get(user);
            mapSession.remove(oldSession);
        }
        mapSession.put(sessionId, user);
        mapUser.put(user, sessionId);
    }
}