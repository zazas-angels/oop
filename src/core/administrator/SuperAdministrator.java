package core.administrator;

import core.category.CategoryTree;
import core.database.DBConnection;

/**
 * Created by nika on 6/23/15.
 */
public class SuperAdministrator extends Administrator {
    public SuperAdministrator(int id, String email, String password, DBConnection dbConnection, CategoryTree categoryTree) {
        super(id, email, password, dbConnection, categoryTree);
    }

    public SuperAdministrator(int id, String email, String password, boolean alreadyHashed, DBConnection dbConnection, CategoryTree categoryTree) {
        super(id, email, password, alreadyHashed, dbConnection, categoryTree);
    }


    public Administrator setAdmin(int userID, CategoryTree categoryTree) {
        return this.getDbConnection().setAdmin(userID, categoryTree);
    }

    public void deleteAdmin(int adminID) {
        this.getDbConnection().deleteAdmin(adminID);
    }
}
