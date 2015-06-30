package core.administrator;

import core.category.Category;
import core.category.CategoryInterface;
import core.category.CategoryTree;
import core.category.CategoryTreeInterface;
import core.database.Connection;
import core.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

import static core.user.User.generatePassword;

/**
 * Created by nika on 6/4/15.
 */
public class Administrator implements AdminInterface {

    private int id;
    private String email;
    private String password;
    private Connection dbConnection;
    private CategoryTreeInterface categoryTree;

    public Administrator(int id, String email, String password, Connection con, CategoryTreeInterface tree) {
        this.id = id;
        this.email = email;
        this.password = generatePassword(password);
        this.dbConnection = con;
        this.categoryTree = tree;
    }

    public Administrator(int id, String email, String password, boolean alreadyHashedPassword, DBConnection dbConnection, CategoryTree categoryTree) {
        this.id = id;
        this.email = email;
        if (alreadyHashedPassword)
            this.password = password;
        else
            this.password = generatePassword(password);
        this.dbConnection = dbConnection;
        this.categoryTree = categoryTree;
    }

    @Override
    public synchronized ResultSet findUser(String name, String bann, String active, String categoryName) {
        ResultSet set = null;
        if (name != null && bann != null && categoryName != null && active != null) {
            if (dbConnection != null) {
                set = dbConnection.getUsersByCriterias(name, bann, active, categoryName);
            }
        }
        return set;
    }

    @Override
    public synchronized void addCategory(String name, CategoryInterface parentCategory) throws SQLException {
        int id = dbConnection.addCategory(name, parentCategory.getId());
        Category category = new Category(id, name);
        categoryTree.add(category, parentCategory);
    }

    @Override
    public synchronized void addCategory(String name, int ID) throws SQLException {
        addCategory(name, new Category(ID, name));
    }

    @Override
    public synchronized void setCategoryTree(CategoryTreeInterface categoryTree) {
        this.categoryTree = categoryTree;
    }

    @Override
    public synchronized boolean equals(Object object) {
        Administrator admin = (Administrator) object;
        return email.equals(admin.getEmail()) && password.equals(admin.getPassword());
    }
    @Override
    public synchronized String getEmail() {
        return email;
    }
    @Override
    public synchronized String getPassword() {
        return password;
    }
    @Override
    public synchronized int getId() {
        return id;
    }
    @Override
    public synchronized Connection getDbConnection() {
        return dbConnection;
    }
    @Override
    public synchronized CategoryTreeInterface getCategoryTree() {
        return categoryTree;
    }

    @Override
    public synchronized ResultSet getReports() {
        ResultSet set = null;
        if (dbConnection != null) {
            set = dbConnection.getReports();
        }
        return set;
    }

    @Override
    public synchronized ResultSet getReports(int days) {
        ResultSet set = null;
        if (dbConnection != null) {
            set = dbConnection.getReports(days);
        }
        return set;
    }

    @Override
    public synchronized ResultSet getWantedCategories() {
        ResultSet set = null;
        if (dbConnection != null) {
            set = dbConnection.getWantedCategories();
        }
        return set;
    }

    @Override
    public synchronized ResultSet getWantedCategories(int days) {
        ResultSet set = null;
        if (dbConnection != null) {
            set = dbConnection.getWantedCategories(days);
        }
        return set;
    }

    @Override
    public synchronized ResultSet getNotifications() {
        ResultSet set = null;
        if (dbConnection != null) {
            set = dbConnection.getNotifications();
        }
        return set;
    }

    @Override
    public synchronized void bannUser(int userID) {
        dbConnection.setBannedStatus(userID, true);
    }

    @Override
    public synchronized void bannUser(int userID, int days) {
        dbConnection.bannUserByDays(userID, days);
    }

    @Override
    public synchronized void releaseBann(int userID) {
        dbConnection.setBannedStatus(userID, false);
    }

    @Override
    public synchronized void deleteWantedCategory(int wcID) {
        dbConnection.deleteWantedCategory(wcID);
    }

    @Override
    public synchronized void deleteReport(int reportID) {
        dbConnection.deleteReport(reportID);
    }

    @Override
    public void deleteUser(int userID) {
        dbConnection.deleteUser(userID);
    }
}
