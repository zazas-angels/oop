package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer =response.getWriter();
		String idSting = request.getParameter("categoryId");
		System.out.println(idSting);
		int id;
		try {
			id = Integer.parseInt(idSting);
		} catch (NumberFormatException e) {
			id = -1;
		}
		if(id<0)return;
		Connection database = (Connection)request.getServletContext().getAttribute("database");
		ResultSet results=null;
		if(id!=0){
		CategoryTree categories = (CategoryTree) getServletContext().getAttribute("categories");
		List<CategoryInterface> connectedCategories = new ArrayList<CategoryInterface>();
		List<CategoryInterface> childBush= categories.getChildBush(id);
		if(childBush!=null)
		connectedCategories.addAll(childBush);
		List<CategoryInterface> parentsBranch = categories.getParentBranch(id);
		if(parentsBranch!=null)
		connectedCategories.addAll(parentsBranch);
	
		results = database.getUsersByCategories(connectedCategories);
		}else{
			results = database.getUsers();
		}
		if (results != null) {
			try {
				while (results.next()) {
					writer.print("<li>");

					writer.print("<a href='#' onclick=\"alert('notImlemented yet')\"> "
							+ results.getString(2) + " </a>");
					writer.print("</li>");

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
