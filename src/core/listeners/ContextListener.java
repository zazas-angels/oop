package core.listeners; /**
 * Created by nika on 5/26/15.
 */

import core.category.CategoryTree;
import core.category.CategoryTreeInterface;
import core.database.Connection;
import core.database.DBConnection;
import core.user.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.ResultSet;
import java.util.HashMap;

@WebListener()
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // TODO Auto-generated method stub
        Connection database = new DBConnection();
        ServletContext servletCont = servletContextEvent.getServletContext();
        ResultSet set = database.getCategories();
        CategoryTreeInterface categories = new CategoryTree(set);


        // need for cookies
        HashMap<String, User> mapSession = new HashMap<>();
        HashMap<User, String> mapUser = new HashMap();

        servletCont.setAttribute("categories", categories);
        servletCont.setAttribute("database", database);
        servletCont.setAttribute("sessions_map", mapSession);
        servletCont.setAttribute("users_map", mapUser);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}