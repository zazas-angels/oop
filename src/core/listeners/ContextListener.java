package core.listeners; /**
 * Created by nika on 5/26/15.
 */

import core.category.CategoryTree;
import core.category.CategoryTreeInterface;
import core.database.Connection;
import core.database.DBConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.ResultSet;

@WebListener()
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // TODO Auto-generated method stub
        Connection database = new DBConnection();
        ServletContext servletCont = servletContextEvent.getServletContext();
        ResultSet set = database.getCategories();
        CategoryTreeInterface categories = new CategoryTree(set);

        servletCont.setAttribute("categories", categories);
        servletCont.setAttribute("database", database);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}