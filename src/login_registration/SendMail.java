package login_registration;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
 
public class SendMail {
	static final String SRC_MAIL_ADDRESS = "akasr13@freeuni.edu.ge"; //sends from
	static final String SRC_MAIL_PASSWORD = "12200139";	//user password
	
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 
	public static void main(String[] args) throws AddressException, MessagingException{
		sendEmail("akasr13@freeuni.edu.ge", "idi na xui suka" + "<br/> mase jobia");
		sendEmail("akasr13@freeuni.edu.ge", "ara zmao, kvrias mcalia \n \n \n" + "<br/> mase jobia");
	}
 
	/*
	 * sends message to mailAdress from SRC_MAIL_ADDRESS
	 * message should be in HTML String format 
	 */
	public static void sendEmail(String mailAddress, String message) throws AddressException, MessagingException {

		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailAddress));
		generateMailMessage.setSubject("zdarovebii");
		generateMailMessage.setContent(message, "text/html");
		
		Transport transport = getMailSession.getTransport("smtp");
		
		transport.connect("smtp.gmail.com", SRC_MAIL_ADDRESS, SRC_MAIL_PASSWORD);
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
		System.out.println("message sent successfully");
		
	}
}
