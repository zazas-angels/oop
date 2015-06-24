package core.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserPageData
 * 
 * This servlet is for  saving user editable and viewable page
 */
@WebServlet("/UserPageData")
public class UserPageData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPageData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * ignored
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ignored
	}
	/*
	 * This method saves for body as WebData Info
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("movida");
		String data = request.getParameter("data");
		
		String needViewMassage = request.getParameter("view");
		
		if(needViewMassage==null)return;
		boolean needView = needViewMassage.equals("1");
		WebData webData = (WebData) request.getSession().getAttribute("webData");
		webData.changeData(data);
		System.out.println(data);
		String res="";
		if(needView){
			res=webData.getData();
		//	if(res!=null)
				response.setContentType("text/plain");
			response.getWriter().write(res);
			System.out.println(webData.getDataView());
			System.out.println("needView");
		}else{
			//nothing to write
			System.out.println("notneedView");
		}
		System.out.println(needViewMassage);
		System.out.println("result" +res);
	}

}
