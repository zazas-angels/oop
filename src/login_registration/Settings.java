package login_registration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.SiteConstants;
import core.administrator.Administrator;
import core.database.DBConnection;
import core.user.User;

/**
 * Servlet implementation class Settings
 */
@WebServlet(value = "/Settings", name = "Settings")
public class Settings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Settings() {
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
		 String type = request.getParameter("requestType");
		 ServletContext context = request.getServletContext();
		 DBConnection db = (DBConnection) context.getAttribute(SiteConstants.DATABASE);
		 User user = (User)request.getAttribute("user");
	        if (type != null) {
	            switch (type){
	                case "changeMail":
	                    String newMail = request.getParameter("mail");
	                    if(checkMail(newMail)){
	                    	
	                    }
	                    break;
	                case "changeName":
	                	String newName = request.getParameter("name");
	                	if(checkName(newName)){
	                		
	                	}
	                    break;
	                case "chengaPassword":
	                    String newPaswrd = request.getParameter("password");
	                    if(checkPassword(newPaswrd)){
	                    	
	                    }
	                    break;
	               
	                default:
	                    System.out.println("zazaaaaa");
	            }
	        }
	}
	
	/**
     * checks if given password has enough length
     */
	private boolean checkPassword(String pas){
		return pas != null && pas.length() >= 6;
	}
	
	
	
    protected static boolean checkName(String name) {
    	return name != null && name.length() >= 1;
    }

    

    /**
     * checks if given email has right structure
     */
    private boolean checkMail(String email) {
        return rightEmailStructure(email);
    }

    /**
     * checks if given string has email structure
     */
    private boolean rightEmailStructure(String email) {
        String patternString = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$";
        return checkRegEx(patternString, email);
    }

    

    /**
     * checks if given text matches to given regular expression
     *
     * @param patternString regular expression
     * @param text          to check
     * @return true if matches, else false
     */
    private static boolean checkRegEx(String patternString, String text) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

}
