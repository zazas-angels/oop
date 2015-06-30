package core.administrator;

import core.category.CategoryInterface;
import core.category.CategoryTree;
import core.category.CategoryTreeInterface;
import core.database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface AdminInterface {

    public ResultSet findUser(String name, String bann, String active, String categoryName);

    public void addCategory(String name, CategoryInterface parentCategory) throws SQLException;

    void addCategory(String name, int ID) throws SQLException;

    public void setCategoryTree(CategoryTreeInterface tree1);

    public ResultSet getReports();

    public ResultSet getReports(int days);

    public ResultSet getWantedCategories();

    public ResultSet getWantedCategories(int days);

    public ResultSet getNotifications();

    public void bannUser(int userID);

    public void bannUser(int userID, int days);

    public void releaseBann(int userID);

    public void deleteWantedCategory(int wcID);

    public void deleteReport(int reportID);

    public void deleteUser(int userID);

	String getEmail();

	String getPassword();

	int getId();

	Connection getDbConnection();

	CategoryTreeInterface getCategoryTree();
}
