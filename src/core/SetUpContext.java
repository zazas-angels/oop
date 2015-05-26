package core;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class SetUpContext
 *
 */
@WebListener
public class SetUpContext implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SetUpContext() {
        // TODO Auto-generated constructor stub
    	//ignore
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	//ignore
    }

	/**
	 * Add categories and database in contexts
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    	Connection database = new DBConnection();
    	ServletContext servletCont = event.getServletContext();
    	CategoryTreeInterface categories = new CategoryTree(database.getUsers());
    	servletCont.setAttribute("categories", categories);
    	servletCont.setAttribute("database", database);
    }
	
}
