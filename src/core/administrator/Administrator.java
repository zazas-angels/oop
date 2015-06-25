package core.administrator;

import core.category.Category;
import core.category.CategoryInterface;
import core.category.CategoryTree;
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
    private DBConnection dbConnection;
    private CategoryTree categoryTree;

    public Administrator(int id, String email, String password, DBConnection dbConnection, CategoryTree categoryTree) {
        this.id = id;
        this.email = email;
        this.password = generatePassword(password);
        this.dbConnection = dbConnection;
        this.categoryTree = categoryTree;
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
    public ResultSet findUser(String name, String bann, String active, String categoryName) {
        ResultSet set = null;
        if (name != null && bann != null && categoryName != null && active != null) {
            if (dbConnection != null) {
                set = dbConnection.getUsersByCriterias(name, bann, active, categoryName);
            }
        }
        return set;
    }

    @Override
    public void addCategory(String name, CategoryInterface parentCategory) throws SQLException {
        int id = dbConnection.addCategory(name, parentCategory.getId());
        Category category = new Category(id, name);
        categoryTree.add(category, parentCategory);
    }

    @Override
    public void setCategoryTree(CategoryTree categoryTree) {
        this.categoryTree = categoryTree;
    }

    @Override
    public boolean equals(Object object) {
        Administrator admin = (Administrator) object;
        return email.equals(admin.getEmail()) && password.equals(admin.getPassword());
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public DBConnection getDbConnection() {
        return dbConnection;
    }

    public CategoryTree getCategoryTree() {
        return categoryTree;
    }

    @Override
    public ResultSet getReports() {
        ResultSet set = null;
        if (dbConnection != null) {
            set = dbConnection.getReports();
        }
        return set;
    }

    @Override
    public ResultSet getReports(int days) {
        ResultSet set = null;
        if (dbConnection != null) {
            set = dbConnection.getReports(days);
        }
        return set;
    }

    @Override
    public ResultSet getWantedCategories() {
        ResultSet set = null;
        if (dbConnection != null) {
            set = dbConnection.getWantedCategories();
        }
        return set;
    }

    @Override
    public ResultSet getWantedCategories(int days) {
        ResultSet set = null;
        if (dbConnection != null) {
            set = dbConnection.getWantedCategories(days);
        }
        return set;
    }

    @Override
    public ResultSet getNotifications() {
        ResultSet set = null;
        if (dbConnection != null) {
            set = dbConnection.getNotifications();
        }
        return set;
    }

    @Override
    public void bannUser(int userID) {
        dbConnection.setBannedStatus(userID, true);
    }

    @Override
    public void bannUser(int userID, int days) {

    }

    @Override
    public void releaseBann(int userID) {
        dbConnection.setBannedStatus(userID, false);
    }

}
