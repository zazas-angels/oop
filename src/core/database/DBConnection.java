package core.database;

/*
 * Author: guri
 */

import core.SiteConstants;
import core.administrator.AdminInterface;
import core.administrator.Administrator;
import core.category.CategoryInterface;
import core.user.User;
import core.user.UserInterface;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static core.user.User.generatePassword;

/* This class is for data base connection, it connects and can make suitable prepared statement.
 * like (similar code) as in assignment 3.
 */
public class DBConnection implements core.database.Connection {
    Connection dataBaseConnection;
    private ArrayList<String> allCategoriesNames;

    /*
     * Constructor: it connects (tries) database using MyDBINfo informations.
     */
    public DBConnection() {
        dataBaseConnection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            dataBaseConnection = DriverManager.getConnection("jdbc:mysql://"
                            + DBInfo.MYSQL_DATABASE_SERVER, DBInfo.MYSQL_USERNAME,
                    DBInfo.MYSQL_PASSWORD);
            executeUseQueary(dataBaseConnection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
     * This method executes use quary for using this database:same as in
     * assignment 3.
     */
    private void executeUseQueary(Connection dataBaseConnection) {
        Statement useStatment;
        try {
            useStatment = dataBaseConnection.createStatement();
            try {
                useStatment.executeQuery("USE " + DBInfo.MYSQL_DATABASE_NAME);
            } catch (SQLException e) {
                // ignored
            }
        } catch (SQLException e1) {
            // ignored
        }
    }

    /*
     * This method close connections
     */
    public void closeConection() {
        // now is dummy but let it be
        // can be commented
        if (dataBaseConnection != null)
            try {
                dataBaseConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    /**
     * This method returns all results form table
     */
    private ResultSet getResults(String tableName) {
        ResultSet results = null;
        try {
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("select * from " + tableName + " ;");

            results = statement.executeQuery();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
        return results;
    }

    /**
     * This method returns all results form table with specified id
     */
    private ResultSet getResults(String tableName, int id) {
        ResultSet results = null;
        try {
            int temp = 1; // 1s ro gadavcem metods mixurebs ratomgac
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("select * from +tableName"
                            + " Where id=?;");
            statement.setInt(temp, id);
            results = statement.executeQuery();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
        return results;
    }

    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        db.addAdmin(new Administrator("nika", "paroli", new DBConnection(), null));
    }

    @Override
    public ResultSet getUsers() {
        return getResults("users");
    }

    @Override
    public ResultSet getCategories() {
        return getResults("categories");
    }

    @Override
    public ResultSet getUsersCategories() {
        return getResults("users_categories");
    }

    @Override
    public ResultSet getPictures() {
        return getResults("pictures");
    }

    @Override
    public ResultSet getColors() {
        return getResults("colors");
    }

    @Override
    public ResultSet getFonts() {
        return getResults("fonts");
    }

    @Override
    public ResultSet getThemes() {
        return getResults("themes");
    }

    @Override
    public ResultSet getElements() {
        return getResults("elements");
    }

    @Override
    public ResultSet getTexts() {
        return getResults("texts");
    }

    @Override
    public ResultSet getElementsInfo() {
        return getResults("elements_info");
    }

    @Override
    public ResultSet getAlboms() {
        return getResults("alboms");
    }

    @Override
    public ResultSet getAlbomsElements() {
        return getResults("alboms_elements");
    }

    @Override
    public ResultSet getAlbomsPictures() {
        return getResults("alboms_pictures");
    }

    @Override
    public ResultSet getElementsPictures() {
        return getResults("elements_pictures");
    }

    @Override
    public ResultSet getAdmins() {
        return getResults("admins");

    }

    @Override
    public ResultSet getUsersByCategories(List<CategoryInterface> categories) {
        return null;
    }

    @Override
    public ResultSet getUsers(int id) {
        return getResults("users", id);
    }

    @Override
    public ResultSet getCategories(int id) {
        return getResults("categories", id);
    }

    @Override
    public ResultSet getUsersCategories(int id) {
        return getResults("users_categories", id);
    }

    @Override
    public ResultSet getPictures(int id) {
        return getResults("pictures", id);
    }

    @Override
    public ResultSet getColors(int id) {
        return getResults("colors", id);
    }

    @Override
    public ResultSet getFonts(int id) {
        return getResults("fonts", id);
    }

    @Override
    public ResultSet getThemes(int id) {
        return getResults("themes", id);
    }

    @Override
    public ResultSet getElements(int id) {
        return getResults("elements", id);
    }

    @Override
    public ResultSet getTexts(int id) {
        return getResults("texts", id);
    }

    @Override
    public ResultSet getElementsInfo(int id) {
        return getResults("elements_info", id);
    }

    @Override
    public ResultSet getAlbums(int id) {
        return getResults("alboms", id);
    }

    @Override
    public ResultSet getAlbumsElements(int id) {
        return getResults("alboms_elements", id);
    }

    @Override
    public ResultSet getAlbumsPictures(int id) {
        return getResults("alboms_pictures", id);
    }

    @Override
    public ResultSet getElementsPictures(int id) {
        return getResults("elements_pictures", id);
    }

    @Override
    public ResultSet getAdmins(int id) {
        return getResults("admins", id);

    }

    /*
     * Add user in database (non-Javadoc)
     *
     * @see core.database.Connection#addUser(core.user.UserInterface)
     */
    @Override
    public int addUser(UserInterface user) {

        try {
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("insert into users (name, url, mail, password, type) values (?,?,?,?,?);");
            statement.setString(1, user.getName());
            statement.setString(2, user.getURL());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, SiteConstants.typeToString(user.getType()));
            statement.executeUpdate();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    /*
     * getUsers if not exist return null (non-Javadoc)
     *
     * @see core.database.Connection#getUser(java.lang.String, java.lang.String)
     */
    @Override
    public UserInterface getUser(String mail, String password) {
        ResultSet results = getFromTable("users", mail, password);
        if (results == null)
            return null;
        UserInterface user = null;
        try {
            if (results.next()) {
                user = new User(results.getString("name"), mail, password,
                        results.getString("url"), SiteConstants.getType(results.getString("type")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }

    /*
     * Checks if user exist in database by email (non-Javadoc)
     *
     * @see core.database.Connection#existsUser(java.lang.String)
     */
    @Override
    public boolean existsUser(String email) {
        ResultSet results = null;
        boolean existResult = false;
        try {
            results = getUsersBy("mail", email);
            if (results != null)
                existResult = results.next();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
        return existResult;
    }

    @Override
    public AdminInterface getAdmin(String email, String password) {
        ResultSet results = getFromTable("admins", email, password);
        if (results == null)
            return null;
        AdminInterface admin = null;
        try {
            if (results.next()) {
                admin = new Administrator(email, password, this, null);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return admin;
    }

    private ResultSet getFromTable(String table, String email, String password) {
        password = generatePassword(password);
        ResultSet results = null;
        try {
            // ricxvebze mixurebs setString-s rom gadavcem da nu cvlit ra..
            int temp1 = 1;
            int temp2 = 2;
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("select * from " + table
                            + " Where mail=? and password =?;");
            statement.setString(temp1, email);
            statement.setString(temp2, password);
            results = statement.executeQuery();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public int insertUserConfCode(int UserId, String confCode) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isActiveUser(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int activateUser(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteUserConfCode(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setBannedStatus(int id, boolean bannedStatus) {

    }

    @Override
    public void setBannedStatus(UserInterface user, boolean bannedStatus) {
        // TODO
    }

    /**
     * @param column column if users table in database
     * @param value  wanted value
     * @return
     */
    private ResultSet getUsersBy(String column, String value) {
        ResultSet results = null;
        try {
            int temp = 1; // 1s ro gadavcem metods mixurebs ratomgac
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("select * from users"
                            + " Where " + column + "=?;");
            statement.setString(temp, value);
            results = statement.executeQuery();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
        return results;
    }

    /**
     * returns all users with given name
     * @param name user's wanted name
     * @return ResultSet of rows from users table
     */
    public ResultSet getUsersByName(String name) {
        return getUsersBy("name", name);
    }


    /**
     * adds administrator in database
     *
     * @return true, if operation was successful else false
     */
    @Override
    public boolean addAdmin(Administrator administrator) {

        try {
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("insert into admins (mail, password) values (?,?);");
            statement.setString(1, administrator.getEmail());
            statement.setString(2, administrator.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * checks if administrator is already regisrired with given mail
     *
     * @return true if is already registrired, otherwise false;
     */
    @Override
    public boolean existsAdministrator(String mail) {
        ResultSet results = null;
        boolean existResult = false;
        try {
            int temp = 1; // 1s ro gadavcem metods mixurebs ratomgac
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("select * from admins"
                            + " Where mail=?;");
            statement.setString(temp, mail);
            results = statement.executeQuery();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
        try {
            if (results != null)
                existResult = results.next();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
        return existResult;
    }

    /**
     * @param on if(on) we search banned users, else "bannless" users
     * @return all banned or "bannless" users
     */
    public ResultSet getUsersByBann(boolean on) {
        String bann = "off";
        if (on) bann = "on";
        return getUsersBy("", bann, "all");

    }

    /**
     * searchs in database users by given criterias
     *
     * @param name   name of user, if name.equals(''), we assume that we don't need to specify search by name.
     * @param bann   if bann.equals("on"), we assume that user's isActived field is true, if bann.equals("off") - false, otherwise we search trues and falses too
     * @param active if active.equals("on") we assume that user's isActived field is true, if active.equals("off") - false, otherwise we search trues and falses too
     * @return ResultSet of apropriate users
     */
    private ResultSet getUsersBy(String name, String bann, String active) {
        ResultSet results = null;
        if (name != null && bann != null && active != null) {
            try {
                PreparedStatement statement = dataBaseConnection
                        .prepareStatement("select * from users"
                                + " Where name like ? and isBanned like ? and isActive like ?;");

                statement.setString(1, name + "%");

                if (bann.equals("on")) {
                    statement.setBoolean(2, true);
                } else {
                    if (bann.equals("off"))
                        statement.setBoolean(2, false);
                    else statement.setString(2, "%");
                }
                if (active.equals("on")) {
                    statement.setBoolean(3, true);
                } else {
                    if (active.equals("off"))
                        statement.setBoolean(3, false);
                    else statement.setString(3, "%");
                }
                results = statement.executeQuery();
                System.out.println(results);
            } catch (SQLException e) {
                // ignore
                e.printStackTrace();
            }
        }
        return results;
    }


    /**
     * searchs in database users by given criterias
     *
     * @param name       name of user, if name.equals(''), we assume that we don't need to specify search by name.
     * @param bann       if bann.equals("on"), we assume that user's isActived field is true, if bann.equals("off") - false, otherwise we search trues and falses too
     * @param active     if active.equals("on") we assume that user's isActived field is true, if active.equals("off") - false, otherwise we search trues and falses too
     * @param categoryID category
     * @return ResultSet of apropriate users
     */
    public ResultSet getUsersByCriterias(String name, String bann, String active, String categoryID) {
        ResultSet set = null;
        if (name != null && bann != null && categoryID != null && active != null) {
            if (categoryID.equals("default") || categoryID.equals("")) {
                set = getUsersBy(name, bann, active);
            } else {
                set = getUsersByCategoryAndCriterias(categoryID, name, bann, active);
                //need to join
            }

        }
        return set;
    }

    /**
     * searchs in database users by given criterias
     *
     * @param name       name of user, if name.equals(''), we assume that we don't need to specify search by name.
     * @param bann       if bann.equals("on"), we assume that user's isActived field is true, if bann.equals("off") - false, otherwise we search trues and falses too
     * @param active     if active.equals("on") we assume that user's isActived field is true, if active.equals("off") - false, otherwise we search trues and falses too
     * @param categoryID category
     * @return ResultSet of apropriate users
     */
    private ResultSet getUsersByCategoryAndCriterias(String categoryID, String name, String bann, String active) {
        ResultSet results = null;
        if (categoryID != null && name != null && bann != null && active != null) {
            try {
                PreparedStatement statement = dataBaseConnection
                        .prepareStatement("select * from users u, users_categories, categories c " +
                                "where u.ID = UserID and CategoryID = c.ID and c.ID = ? and u.name like ? " +
                                "and u.isBanned like ? and u.isActive like ? " +
                                "group by u.ID");


                statement.setString(1, categoryID);
                statement.setString(2, name + "%");

                if (bann.equals("on")) {
                    statement.setBoolean(3, true);
                } else {
                    if (bann.equals("off"))
                        statement.setBoolean(3, false);
                    else statement.setString(3, "%");
                }
                if (active.equals("on")) {
                    statement.setBoolean(4, true);
                } else {
                    if (active.equals("off"))
                        statement.setBoolean(4, false);
                    else statement.setString(4, "%");
                }
                results = statement.executeQuery();
                System.out.println(results);
            } catch (SQLException e) {
                // ignore
                e.printStackTrace();
            }
        }
        return results;
    }
}
