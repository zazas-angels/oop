package login_registration;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GenAndSendConf
 */
@WebServlet("/GenAndSendConf")
public class GenAndSendConf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		String conf = "http://localhost:8080/OOP/GenAndSendConf?conf=" + genConf("28");
		
		/*try {
			SendMail obj = new SendMail(); 
			obj.sendEmail("akasr13@freeuni.edu.ge", conf);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println(conf);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	
	private  String genConf(String id) {
        String conf = id;

        try {
            MessageDigest ms = MessageDigest.getInstance("MD5");
            ms.update(conf.getBytes());
            byte[] digest = ms.digest();
            return (conf + "$" + hexToString(digest));
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
