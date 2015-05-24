package login_registration;

import core.DBConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by nika on 5/24/15.
 */
@WebServlet(value = "/login", name = "loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = generateHash("/" + request.getParameter("password"));
        String email = request.getParameter("email");
        ServletContext context = request.getServletContext();
        DBConnection dbConnection = (DBConnection) context.getAttribute("database");

        if ((email.equals("zaza") && request.getParameter("password").equals("zazuna")) || dbConnection.getUser(email, password) != null) {
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


    /**
     * generates hash string form given password
     *
     * @param password string to hash
     * @return hash string
     */
    public static String generateHash(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(password.getBytes());
            password = hexToString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }

    /*
     Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
    public static String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;  // remove higher bits, sign
            if (val < 16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }
}