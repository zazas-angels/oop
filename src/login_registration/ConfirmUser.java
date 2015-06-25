package login_registration;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.database.DBConnection;

/**
 * Servlet implementation class ConfirmUser
 */
@WebServlet("/ConfirmUser")
public class ConfirmUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String conf = request.getParameter("conf");
		int delIndx = conf.indexOf('$'); //get delimiter index
		int userId = Integer.parseInt(conf.substring(0, delIndx));
		String confCode = conf.substring(delIndx + 1);
		ServletContext context = request.getServletContext();
		DBConnection dbConnection = (DBConnection) context.getAttribute("database");
		String confFromDB = dbConnection.getConf(userId);
		
		if(confFromDB.equals(confCode)){
			dbConnection.deleteUserConfCode(userId);
			dbConnection.activateUser(userId);
			request.getRequestDispatcher("ConfirmationEnd.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
