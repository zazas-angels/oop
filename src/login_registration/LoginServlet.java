package login_registration;

import core.SiteConstants;
import core.administrator.AdminInterface;
import core.administrator.Administrator;
import core.database.DBConnection;
import core.user.User;
import core.user.UserInterface;

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

        if (password != null && email != null) {
            User user = null;
            if (dbConnection.getUser(email, password) != null) {
                user = (User) dbConnection.getUser(email, password);
            }
            if (user != null) {
                loginUser(request, response, user, context);
            }
            Administrator admin = (Administrator) dbConnection.getAdmin(email, password);
            if (admin != null) {
                loginAdmin(request, response, admin, context);
            }
        }
        request.getSession().setAttribute("wrong try to log in", true);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * is called while login with cookies
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            ServletContext context = request.getServletContext();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(SiteConstants.LOGIN_COOKIE_NAME) && cookie.getValue() != null) {
                    HashMap<String, UserInterface> mapSession = (HashMap<String, UserInterface>) context.getAttribute(SiteConstants.SESSIONS_MAP_USERS);
                    UserInterface user = mapSession.get(cookie.getValue());
                    if (user != null) {
                        loginUser(request, response, user, context);
                    } else {
                        HashMap<String, AdminInterface> mapSessionAd = (HashMap<String, AdminInterface>) context.getAttribute(SiteConstants.SESSIONS_MAP_ADMINS);
                        AdminInterface admin = mapSessionAd.get(cookie.getValue());
                        if (admin != null) {
                            loginAdmin(request, response, admin, context);
                        }
                    }
                }
            }
        }
    }


    /**
     * logs in user, saves information in session and saves session id in cookie
     */
    private void loginAdmin(HttpServletRequest request, HttpServletResponse response, AdminInterface admin, ServletContext context) throws ServletException, IOException {
        request.getSession().setAttribute("logged in", true);
        request.getSession().setAttribute("type", "admin");
        request.getSession().setAttribute("admin", admin);

        request.getRequestDispatcher("adminPage.jsp").forward(request, response);
    }

    /**
     * logs in user, saves information in session and saves session id in cookie
     */
    private void loginUser(HttpServletRequest request, HttpServletResponse response, UserInterface user, ServletContext context) throws ServletException, IOException {
        request.getSession().setAttribute("logged in", true);
        request.getSession().setAttribute("type", "email");
        request.getSession().setAttribute("user", user);

        addCookie(request, response, context, user);
        request.getRequestDispatcher("userPage.jsp").forward(request, response);
    }


    /**
     * saves cookie and adds in maps sessions and which user was logged in on that session
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, ServletContext context, UserInterface user) {
        String sessionId = request.getSession().getId();
        addIntoMaps(sessionId, user, context);
        // save login state in cookies..
        saveCookie(sessionId, response);
    }

    /**
     * saves cookie and adds in maps sessions and which admin was logged in on that session
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, ServletContext context, AdminInterface admin) {
        String sessionId = request.getSession().getId();
        // save login state in cookies..
        addIntoMaps(sessionId, admin, context);
        saveCookie(sessionId, response);
    }

    /**
     * saves cookie for login, cookie is valid during one hour
     */
    private static void saveCookie(String sessionId, HttpServletResponse response) {
        Cookie newCookie = new Cookie(SiteConstants.LOGIN_COOKIE_NAME, sessionId);
        newCookie.setMaxAge(60 * 60); // one hour
        response.addCookie(newCookie);
    }

    /**
     * saves in maps id of session and user
     *
     * @param sessionId id of session which we want to associate to user
     * @param user      user, which was logged in on that session
     */
    public static void addIntoMaps(String sessionId, UserInterface user, ServletContext context) {
        HashMap<UserInterface, String> mapUser = (HashMap<UserInterface, String>) context.getAttribute(SiteConstants.USERS_MAP_NAME);
        HashMap<String, UserInterface> mapSession = (HashMap<String, UserInterface>) context.getAttribute(SiteConstants.SESSIONS_MAP_USERS);
        if (mapUser.get(user) != null) {
            String oldSession = mapUser.get(user);
            mapSession.remove(oldSession);
        }
        mapSession.put(sessionId, user);
        mapUser.put(user, sessionId);
    }

    /**
     * saves in maps id of session and admin
     *
     * @param sessionId id of session which we want to associate to admin
     * @param admin     admin, which was logged in on that session
     */
    public static void addIntoMaps(String sessionId, AdminInterface admin, ServletContext context) {
        HashMap<AdminInterface, String> mapAdmins = (HashMap<AdminInterface, String>) context.getAttribute(SiteConstants.ADMINS_MAP_NAME);
        HashMap<String, AdminInterface> mapSession = (HashMap<String, AdminInterface>) context.getAttribute(SiteConstants.SESSIONS_MAP_ADMINS);
        if (mapAdmins.get(admin) != null) {
            String oldSession = mapAdmins.get(admin);
            mapSession.remove(oldSession);
        }
        mapSession.put(sessionId, admin);
        mapAdmins.put(admin, sessionId);
    }
}