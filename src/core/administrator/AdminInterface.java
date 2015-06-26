package core.administrator;

import core.category.CategoryInterface;
import core.category.CategoryTree;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface AdminInterface {

    public ResultSet findUser(String name, String bann, String active, String categoryName);

    public void addCategory(String name, CategoryInterface parentCategory) throws SQLException;

    public void setCategoryTree(CategoryTree categoryTree);

    public ResultSet getReports();

    public ResultSet getReports(int days);

    public ResultSet getWantedCategories();

    public ResultSet getWantedCategories(int days);

    public ResultSet getNotifications();

    public void bannUser(int userID);

    public void bannUser(int userID, int days);

    public void releaseBann(int userID);

    public void deleteWantedCategory(int wcID);
}
