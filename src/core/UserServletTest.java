package core;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import core.database.Connection;
import core.database.DBConnection;
import core.user.User;

public class UserServletTest {

	@Test
	
	public void simpleDoGetTest() throws ServletException, IOException {
		UsersServlet userServlet = new UsersServlet();
		Connection con = new StubDBConnection(){
			private Set<String> tags = new HashSet<String>();
			@Override
			public void addTag(int userId, String name) {
				// TODO Auto-generated method stub
				tags.add(name);
			}
			//checks if tag was added
			@Override
			public boolean existsTag(String tag){
				return tags.contains(tag);
			}
		};
		HttpServletRequest request = new StubServletRequest() {
			
			public String getParameter(String str) {
				// TODO Auto-generated method stub
				if (str.equals("addTag")) {
					return "zaza";
				}
				return null;
			}

			public HttpSession getSession() {
				// TODO Auto-generated method stub
				HttpSession session = new StubHttpSession() {
					public Object getAttribute(String arg0) {
						// TODO Auto-generated method stub
						return new User("zaza", "zaza@mail.ru", "1234", "zaza",
								SiteConstants.Type.email, 1);
					}
				};
				return session;
			}

			public ServletContext getServletContext() {
				// TODO Auto-generated method stub
				ServletContext context = new StubSetvletContext(){
					@Override
					public Object getAttribute(String arg0) {
						// TODO Auto-generated method stub
						
						
						return con;
					}

				};
				
				
				return context;
			}
		};
		HttpServletResponse response = new StubServletResponse();
		
		userServlet.doGet(request, response);
		assertEquals(con.existsTag("zaza"), true);
		assertEquals(con.existsTag("zaz"), false);
		assertEquals(con.existsTag("za543#"), false);
		assertEquals(con.existsTag("zaza"), true);//re use
		
	}

}
