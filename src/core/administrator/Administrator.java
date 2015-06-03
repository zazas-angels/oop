package core.administrator;

import core.category.CategoryInterface;
import core.category.CategoryTree;
import core.database.DBConnection;
import core.user.UserInterface;

import java.util.List;

/**
 * Created by nika on 6/4/15.
 */
public class Administrator implements AdminInterface {

    private DBConnection dbConnection;
    private CategoryTree categoryTree;

    public Administrator(DBConnection dbConnection, CategoryTree categoryTree) {
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
}
