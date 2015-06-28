package login_registration;

import core.database.DBConnection;
import core.user.User;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Servlet implementation class GenAndSendConf
 */
@WebServlet("/GenAndSendConf")
public class GenAndSendConf extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DELIMITER = "$";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenAndSendConf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		User user = (User)request.getSession().getAttribute("user");
		
		String conf = genConf("" + user.getID());
		String link = "http://localhost:8080/" +
				"ConfirmUser?conf=" + user.getID() + DELIMITER + conf;
		ServletContext context = request.getServletContext();
		DBConnection dbConnection = (DBConnection) context.getAttribute("database");
		dbConnection.insertUserConfCode(user.getID(), conf);
		
		try {
			SendMail obj = new SendMail(); 
			obj.sendEmail(user.getEmail(), link);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(conf);
		request.getRequestDispatcher("ConfirmSent.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
	private  String genConf(String id) {
		try {
            MessageDigest ms = MessageDigest.getInstance("MD5");
            ms.update(id.getBytes());
            byte[] digest = ms.digest();
            return (hexToString(digest));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     Given a byte[] array, produces a hex String,
     such as "234a6f". with 2 chars for each byte in the array.
    */
    private String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;  // remove higher bits, sign
            if (val < 16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }

}
