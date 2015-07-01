package core.category;

import core.SiteConstants;
import core.database.DBConnection;
import core.user.User;
import core.user.UserInterface;
import login_registration.LoginServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

/**
 * Servlet implementation class CategorySave
 */
@WebServlet("/CategorySave")
public class CategorySave extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategorySave() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("requestType");
        if (type != null && type.equals("addCategory")) {
            int newCategory = Integer.parseInt(request.getParameter("categoryID"));
            int userID = Integer.parseInt(""+request.getSession().getAttribute("userId"));
            DBConnection dbConnection = (DBConnection) request.getServletContext().getAttribute(SiteConstants.DATABASE);
            Vector<Integer> v = new Vector<Integer>();
            v.add(newCategory);
            dbConnection.addUserCategories(userID, v    );
        }
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    /*
     * Saves categories for logged user (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("choosed");
        DBConnection db = (DBConnection) request.getServletContext()
                .getAttribute(SiteConstants.DATABASE);
        User user = (User) request.getSession().getAttribute("user");
        Vector<Integer> categories = new Vector<Integer>();
        String num = "";
        System.out.println("dt  " + data);
        // we now that last won't be digit
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            if (Character.isDigit(ch)) {
                num += ch;
            } else {
                if (num.length() > 0) {
                    categories.add(Integer.parseInt(num));
                    num = "";
                }
            }
        }
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(categories.get(i));
        }
        if (user != null) {
            db.addUserCategories(user.getID(), categories);
        }
        if (db.isActiveUser(user.getID())) {
            loginUser(user, request, response);
        } else {
            request.getRequestDispatcher("/GenAndSendConf").forward(request, response);
        }
    }

    /**
     * logs user in, saves in cookies.
     */
    private void loginUser(UserInterface user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("logged in", true);
        request.getSession().setAttribute("type", "user");
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("userId", user.getID());
        LoginServlet.addCookie(request, response, request.getServletContext(), user);
        request.getRequestDispatcher("userPage.jsp").forward(request, response);
    }
}
