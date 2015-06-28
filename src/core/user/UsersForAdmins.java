package core.user;

import core.SiteConstants;
import core.database.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class UsersForAdmins
 */
@WebServlet("/UsersForAdmins")
public class UsersForAdmins extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersForAdmins() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ID"));
        DBConnection db = (DBConnection) request.getServletContext().getAttribute(SiteConstants.DATABASE);
        if (db.existsUserWithID(id)) {
            request.getSession().setAttribute("userId", id);
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("userNotFound.html").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
