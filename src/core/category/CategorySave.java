package core.category;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.SiteConstants;
import core.database.DBConnection;
import core.user.User;

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
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ignored
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
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
		DBConnection db = (DBConnection) request.getServletContext()
				.getAttribute(SiteConstants.DATABASE);
		User user = (User) request.getSession().getAttribute("user");
		if (user != null){
			db.addUserCategories(user.getID(), categories);
		}
		request.getRequestDispatcher("/GenAndSendConf").forward(request, response);
		
	}

}
