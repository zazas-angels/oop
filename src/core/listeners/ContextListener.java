package core.listeners; /**
 * Created by nika on 5/26/15.
 */

import core.SiteConstants;
import core.administrator.AdminInterface;
import core.category.CategoryTree;
import core.category.CategoryTreeInterface;
import core.database.Connection;
import core.database.DBConnection;
import core.user.UserInterface;

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
        Connection database = DBConnection.getInstance();
        ServletContext servletCont = servletContextEvent.getServletContext();
        ResultSet set = database.getCategories();
        CategoryTreeInterface categories = new CategoryTree(set);

        servletCont.setAttribute(SiteConstants.CATEGORY_TREE, categories);
        servletCont.setAttribute(SiteConstants.DATABASE, database);


        addMaps(servletCont);
    }

    /**
     * saves in servlet context hashmaps
     * this hashmaps are used, to find connections between sessions and who was logged in on that session(user or admin)
     */
    private void addMaps(ServletContext servletCont) {
        // need for cookies
        HashMap<String, UserInterface> mapSession = new HashMap<>();
        HashMap<UserInterface, String> mapUser = new HashMap<>();

        HashMap<String, AdminInterface> mapSessionAdmins = new HashMap<>();
        HashMap<AdminInterface, String> mapAdmins = new HashMap<>();

        servletCont.setAttribute(SiteConstants.SESSIONS_MAP_USERS, mapSession);
        servletCont.setAttribute(SiteConstants.SESSIONS_MAP_ADMINS, mapSessionAdmins);
        servletCont.setAttribute(SiteConstants.USERS_MAP_NAME, mapUser);
        servletCont.setAttribute(SiteConstants.ADMINS_MAP_NAME, mapAdmins);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}