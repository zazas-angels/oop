package core.administrator;

import core.category.CategoryInterface;
import core.category.CategoryTree;
import core.database.DBConnection;
import core.user.UserInterface;

import java.sql.ResultSet;

import static core.user.User.generatePassword;

/**
 * Created by nika on 6/4/15.
 */
public class Administrator implements AdminInterface {

    private String email;
    private String password;
    private DBConnection dbConnection;
    private CategoryTree categoryTree;

    public Administrator(String email, String password, DBConnection dbConnection, CategoryTree categoryTree) {
        this.email = email;
        this.password = generatePassword(password);
        this.dbConnection = dbConnection;
        this.categoryTree = categoryTree;
    }

    @Override
    public void bannUser(UserInterface user) {
        dbConnection.setBannedStatus(user, true);
    }

    @Override
    public void bannUser(UserInterface user, int hours) {
        dbConnection.setBannedStatus(user, true);
    }

    @Override
    public void releaseBann(UserInterface user) {
        dbConnection.setBannedStatus(user, false);
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
    public void addCategory(CategoryInterface parentCategory, CategoryInterface category) {
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
}
