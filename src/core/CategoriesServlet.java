package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Categories
 * Author: glaba13
 */
@WebServlet("/CategoriesServlet")
public class CategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriesServlet() {
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
	/*
	 * writes sub categories and make up button
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer =response.getWriter();
		String idSting = request.getParameter("id");
		int id;
		try {
			id = Integer.parseInt(idSting);
		} catch (NumberFormatException e) {
			id = -1;
		}
		if(id<0)return;
		
		CategoryTree categories = (CategoryTree) getServletContext().getAttribute("categories");
		CategoryInterface parent =categories.getParent(new Category(id, ""));
		int parentID=0;
		if(parent!=null)
			parentID=parent.getId();
		System.out.println("id = "+id);;
		writer.print("<button id=\"upButton\" onclick=\"makeNextCategories("+parentID+")\">Up</button>");
		List<CategoryInterface> childCategories;
		if(id==0){
			childCategories=categories.getRoots();
		}else{
			//can be converted to 0 if roots but for every case
			
			childCategories=categories.getChilds(id);
			System.out.println("childs :"+id+" "+childCategories.size());
		}
		
		for (int i = 0; i < childCategories.size(); i++) {
			writer.print("<li>");
			String href="";
			int currId = childCategories.get(i).getId();
			if(categories.hasChilds(currId))
			href= " href='#' onclick=\"makeNextCategories("+currId+");\"";
			writer.print("<a"+href+"> "+ childCategories.get(i).getName()+" </a>");
			writer.print("</li>");
		}
		
	}

}
