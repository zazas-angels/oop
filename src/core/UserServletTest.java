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

import org.junit.Before;
import org.junit.Test;

import core.database.Connection;
import core.database.DBConnection;
import core.user.User;

public class UserServletTest {
	FactoryUserServlet factory;
	@Before
	public void initial(){
		factory=new FactoryUserServlet();
	}
	//simple tests for DoGet in UsersServlet
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
		HttpServletRequest request= factory.buildServletRequest(con, "zaza", null);
		HttpServletResponse response = new StubServletResponse();
		
		userServlet.doGet(request, response);
		assertEquals(con.existsTag("zaza"), true);
		assertEquals(con.existsTag("zaz"), false);
		assertEquals(con.existsTag("za543#"), false);
		assertEquals(con.existsTag("zaza"), true);//re use
		
	}
	// complex testing (realy works)
	@Test
	public void complexDoGetTest() throws ServletException, IOException {
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
HttpServletRequest request= factory.buildServletRequest(con, "aveji", "nukri");
		HttpServletResponse response = new StubServletResponse();
		userServlet.doGet(request, response);
		assertEquals(con.existsTag("aveji"), true);
		assertEquals(con.existsTag("nukri"), false);
		assertEquals(con.existsTag("wewrw"), false);
		assertEquals(con.existsTag("aveji"), true);
		
		
		request= factory.buildServletRequest(con, "gadazidva", "");
		response = new StubServletResponse();
		userServlet.doGet(request, response);
		assertEquals(con.existsTag("gadazidva"), true);
		assertEquals(con.existsTag("asdf"), false);
		assertEquals(con.existsTag("wewrw"), false);
		assertEquals(con.existsTag("gadazidva"), true);//re use
		assertEquals(con.existsTag("aveji"), true);//re use old one
		assertEquals(con.existsTag("zaza"), false);
		
	}
	//some wierd tests, checking nulls and empty strings
	@Test
	public void wierdDoGetTest() throws ServletException, IOException {
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
		HttpServletRequest request= factory.buildServletRequest(con, null, null);
		HttpServletResponse response = new StubServletResponse();
		
		userServlet.doGet(request, response);
		assertEquals(con.existsTag("zaza"), false);
		assertEquals(con.existsTag("za"), false);
		assertEquals(con.existsTag("zamiri"), false);
		assertEquals(con.existsTag("zazasda"), false);
		
		request= factory.buildServletRequest(con, null, null);//re use
		response = new StubServletResponse();
		
		userServlet.doGet(request, response);
		assertEquals(con.existsTag("zaza"), false);
		assertEquals(con.existsTag("za"), false);
		assertEquals(con.existsTag("zamiri"), false);
		assertEquals(con.existsTag("zazasda"), false);
		 request= factory.buildServletRequest(con, "", null);
		response = new StubServletResponse();
		
		userServlet.doGet(request, response);
		assertEquals(con.existsTag("vip"), false);
		assertEquals(con.existsTag("za"), false);
		assertEquals(con.existsTag("zamiri"), false);
		assertEquals(con.existsTag(""), true);//wierd
		assertEquals(con.existsTag(null), false);//wierd
		
		
	}
	
		
	}
	

//factory class for testing
class FactoryUserServlet{
	HttpServletRequest buildServletRequest(Connection con, String parameter,String  returnValue ){
		HttpServletRequest request;
		request= new StubServletRequest() {
			
			public String getParameter(String str) {
				// TODO Auto-generated method stub
				if (str.equals("addTag")) {
					return parameter;
				}
				return returnValue;
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
		
		return request;
	}
}
