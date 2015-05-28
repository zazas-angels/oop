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
 * Servlet implementation class ExpandCategoriesServlet Author: glaba13
 */
@WebServlet("/ExpandCategoriesServlet")
public class ExpandCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExpandCategoriesServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * makes inner categories suitable for having children
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String idSting = request.getParameter("id");
		int id;
		try {
			id = Integer.parseInt(idSting);
		} catch (NumberFormatException e) {
			id = -1;
		}
		if (id < 0)
			return;

		CategoryTree categories = (CategoryTree) getServletContext()
				.getAttribute("categories");
		List<CategoryInterface> childCategories;
		if (id == 0) {
			childCategories = categories.getRoots();
		} else {
			// can be converted to 0 if roots but for every case

			childCategories = categories.getChilds(id);
		}
		if (childCategories == null)
			return;
		writer.print("<ul>");
		for (int i = 0; i < childCategories.size(); i++) {
			writer.print("<li>");
			boolean hasChilds = false;
			int currId = childCategories.get(i).getId();
			if (categories.hasChilds(currId))
				hasChilds = true;
			String categoryName= childCategories.get(i).getName();
			writer.print("<div  id=\"" + currId + "\" "+" categoryName=\""+categoryName
					+ "\"  isExpanded = \"false\" onclick=\"expandCategory(event" + "," + currId + ","
					+ hasChilds + ");\"" + "> "
					+ categoryName + " <input type=\"checkbox\" onclick=\"changeCheckedSet(event,"
					+ currId + ");\"  id=\"check" + currId + "\">");
			writer.print("</li>");
		}
		writer.print("</ul>");
	}

}
