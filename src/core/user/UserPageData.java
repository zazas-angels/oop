package core.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.database.Connection;

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
		
		WebData webData = (WebData) request.getSession().getAttribute("webData");
		//webData.changeData(data);
		
		System.out.println("áƒ�");
		String res="";
		Connection database = (Connection) request.getServletContext().getAttribute("database");
		int userId;
		if(request.getParameter("id")!=null){
			userId=Integer.parseInt(request.getParameter("id"));
		}else{
		userId=((int) request.getSession().getAttribute("userId"));
		}
		if(needViewMassage.equals("0")){
			//res=webData.getData(); 
		//	if(res!=null)
			database.changeData( userId,data);
			System.out.println("save");
			
			
			//System.out.println(webData.getDataView());
			//System.out.println("needView");
		}else{
			//nothing to write
			response.setContentType("text/plain;; charset=UTF-8");
			response.getWriter().write(database.getData(userId));
		
		
		}
		System.out.println(database.getData(userId));
		System.out.println(needViewMassage);
		System.out.println("result" +res);
	}

}
