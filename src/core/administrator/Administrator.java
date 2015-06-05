package core.administrator;

import core.category.CategoryInterface;
import core.category.CategoryTree;
import core.database.DBConnection;
import core.user.UserInterface;

import java.util.List;

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
    public List<UserInterface> findUser(String name) {
        return dbConnection.getUsersByName(name);
    }

    @Override
    public List<UserInterface> findUsersExtended(String name, boolean banned, CategoryInterface category) {
        return null;
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
}
