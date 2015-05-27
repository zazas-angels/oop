package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.category.Category;
import core.category.CategoryInterface;
import core.category.CategoryTree;

/**
 * Servlet implementation class ExpandCategoriesServlet
 */
@WebServlet("/ExpandCategoriesServlet")
public class ExpandCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpandCategoriesServlet() {
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
				List<CategoryInterface> childCategories;
				if(id==0){
					childCategories=categories.getRoots();
				}else{
					//can be converted to 0 if roots but for every case
					
					childCategories=categories.getChilds(id);
				}
				if(childCategories==null)return;
				writer.print("<ul>");
				for (int i = 0; i < childCategories.size(); i++) {
					writer.print("<li>");
					boolean hasChilds=false;
					int currId = childCategories.get(i).getId();
					if(categories.hasChilds(currId))
						hasChilds=true;
					
					writer.print("<div  id=\""+currId+"\" "+ "  onclick=\"expandCategory(event"+","+currId+","+hasChilds+");\""+"> "+ childCategories.get(i).getName()+" </div>");
					writer.print("</li>");
				}
				writer.print("</ul>");
	}

}
