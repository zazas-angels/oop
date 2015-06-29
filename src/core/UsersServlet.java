package core;
/*
 * Author guri
 */

import core.category.CategoryInterface;
import core.category.CategoryTree;
import core.database.Connection;
import core.database.DBConnection;
import core.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		if(request.getParameter("addTag") != null){
			DBConnection dbConnection = (DBConnection) request.getServletContext().getAttribute(SiteConstants.DATABASE);
			dbConnection.addTag(((User) request.getSession().getAttribute("user")).getID(), request.getParameter("addTag"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 * writes all users which is connected which defined categories
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/plain;; charset=UTF-8");
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
		for (int i = 0; i < connectedCategories.size(); i++) {
			System.out.println(connectedCategories.get(i).getId());
		}
		
		
		results = database.getUsersByCategories(connectedCategories);
		}else{
			results = database.getUsers();
		}
		if (results != null) {
			try {
				while (results.next()) {
					System.out.println("kio");
					writer.print("<li>");
					writer.print("<a href='Visitor.jsp?id="
							+ results.getString("ID") + "' > " + "<img src=\""
							+ results.getString("avatarFile") + "\""  + "> <span>"
							+ results.getString("name") + "</span> </a>");
					writer.print("</li>");

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("ola");
		
	}

}
