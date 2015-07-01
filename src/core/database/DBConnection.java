package core.database;

/*
 * Author: guri
 */

import core.SiteConstants;
import core.administrator.AdminInterface;
import core.administrator.Administrator;
import core.administrator.SuperAdministrator;
import core.category.Category;
import core.category.CategoryInterface;
import core.category.CategoryTree;
import core.user.User;
import core.user.UserInterface;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static core.user.User.generatePassword;

/* This class is for data base connection, it connects and can make suitable prepared statement.
 * like (similar code) as in assignment 3.
 */
public class DBConnection implements core.database.Connection {
private static DBConnection dbConnection = new DBConnection();
    private Connection dataBaseConnection;
    private ArrayList<String> allCategoriesNames;
    
    public static DBConnection getInstance( ) {
        return dbConnection;
     }

	/*
	 * Constructor: it connects (tries) database using MyDBINfo informations.
	 */
	private DBConnection() {
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
	private synchronized void executeUseQueary(Connection dataBaseConnection) {
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
	public synchronized void closeConection() {
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
	private synchronized ResultSet getResults(String tableName) {
		ResultSet results = null;
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from " + tableName + ";");

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
	private synchronized ResultSet getResults(String tableName, int id) {
		ResultSet results = null;
		try {
			int temp = 1; // 1s ro gadavcem metods mixurebs ratomgac
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from " + tableName
                            + " Where id=?;");
			statement.setInt(temp, id);
			results = statement.executeQuery();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return results;
	}
	/**
	 * returns users set
	 */
	@Override
	public synchronized ResultSet getUsers() {
		ResultSet results = null;
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from users where isActive=1"  + ";");

			results = statement.executeQuery();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return results;
	}
	/**
	 * returns categories set
	 */
	@Override
	public synchronized ResultSet getCategories() {
		return getResults("categories");
		
	}
	/**
	 * returns Users_categories
	 */
	@Override
	public synchronized ResultSet getUsersCategories() {
		return getResults("users_categories");
	}
	/**
	 * returns pictures set
	 */
	@Override
	public synchronized ResultSet getPictures() {
		return getResults("pictures");
	}
	/**
	 * returns resultSet of colors
	 */
	@Override
	public synchronized ResultSet getColors() {
		return getResults("colors");
	}

	/*
	 * Add user in database (non-Javadoc)
	 *
	 * @see core.database.Connection#addUser(core.user.UserInterface)
	 */
	@Override
	public synchronized User addUser(String name, String email,
			String password, String url, SiteConstants.Type type) {

		User user = null;
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("insert into users (name, url, mail, password, type, avatarFile) values (?,?,?,?,?, \"default.png\");");
			statement.setString(1, name);
			statement.setString(2, url);
			statement.setString(3, email);
			statement.setString(4, generatePassword(password));
			statement.setString(5, SiteConstants.typeToString(type));
			statement.executeUpdate();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		try {
			ResultSet results = null;
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("SELECT * from users where name = ? and url = ? and mail = ? and password = ?;");
			int tmp = 1;
			statement.setString(tmp++, name);
			statement.setString(tmp++, url);
			statement.setString(tmp++, email);
			statement.setString(tmp++, generatePassword(password));
			results = statement.executeQuery();
			if (results != null) {
				if (results.next()) {
					user = new User(results.getString("name"),
							results.getString("mail"), password,
							results.getString("url"),
							SiteConstants.getType(results.getString("type")),
							results.getInt("ID"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	private synchronized ResultSet getFromTable(String table, String email,
			String password) {
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
	/**
	 * confirmation code
	 */
	@Override
	public synchronized void insertUserConfCode(int UserId, String confCode) {
		try {
			// at first remove(if exists) older confcode from database
			deleteUserConfCode(UserId);
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("insert into users_confcodes (userId, confirmCode) values (?,?);");
			statement.setInt(1, UserId);
			statement.setString(2, confCode);
			statement.executeUpdate();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}

	}

	/*
	 * getUsers if not exist return null (non-Javadoc)
	 *
	 * @see core.database.Connection#getUser(java.lang.String, java.lang.String)
	 */
	@Override
	public synchronized UserInterface getUser(String mail, String password) {
		ResultSet results = getFromTable("users", mail,
				generatePassword(password));
		if (results == null)
			return null;
		UserInterface user = null;
		try {
			if (results.next()) {
				user = new User(results.getString("name"), mail, password,
						results.getString("url"), SiteConstants.getType(results
								.getString("type")), results.getInt("ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	/**
	 * checks user active status
	 */
	@Override
	public synchronized boolean isActiveUser(int id) {
		boolean b = false;
		ResultSet results = null;
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from users" + " Where ID=?;");
			statement.setInt(1, id);
			results = statement.executeQuery();
			if (results != null && results.next()) {
				if (results.getBoolean("isActive") == true) {
					b = true;
				}
			}
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * return admin
	 */
	@Override
	public synchronized AdminInterface getAdmin(String email, String password,
			CategoryTree categoryTree) {
		return getAdmin(email, password, categoryTree, false);
	}
	/**
	 * activate user
	 */
	@Override
	public synchronized void activateUser(int id) {
		PreparedStatement stmt = null;
		try {
			stmt = dataBaseConnection
					.prepareStatement("update users set isActive = true where ID = ?;");
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * returns super administrator
	 */
	@Override
	public synchronized SuperAdministrator getSuperAdmin(String email,
			String password, CategoryTree categoryTree) {
		return (SuperAdministrator) getAdmin(email, password, categoryTree,
				true);
	}
	/**
	 * 
	 * @param email
	 * @param password
	 * @param categoryTree
	 * @param superAmin
	 * @return
	 */
	private synchronized AdminInterface getAdmin(String email, String password,
			CategoryTree categoryTree, boolean superAmin) {
		ResultSet results;
		if (superAmin)
			results = getFromTable("superAdmins", email,
					generatePassword(password));
		else
			results = getFromTable("admins", email, generatePassword(password));

		if (results == null)
			return null;
		AdminInterface admin = null;
		try {
			if (results.next()) {
				if (superAmin)
					admin = new SuperAdministrator(results.getInt("ID"), email,
							password, this, categoryTree);
				else
					admin = new Administrator(results.getInt("ID"), email,
							password, this, categoryTree);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;
	}
	/**
	 * deletes user confirmation code
	 */
	@Override
	public synchronized void deleteUserConfCode(int userId) {
		PreparedStatement stmt = null;
		try {
			stmt = dataBaseConnection
					.prepareStatement("delete from users_confcodes where userId = ?;");
			stmt.setInt(1, userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param column
	 *            column if users table in database
	 * @param value
	 *            wanted value
	 * @return
	 */
	private synchronized ResultSet getUsersBy(String column, String value) {
		ResultSet results = null;
		try {
			int temp = 1; // 1s ro gadavcem metods mixurebs ratomgac
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from users" + " Where "
                            + column + "=?;");
			statement.setString(temp, value);
			results = statement.executeQuery();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return results;
	}

    /**
     * sets isBanned column value given boolean(bannedStatus)
     *
     * @param userID       identifies user
     * @param bannedStatus new value for isBanned column
     */
    @Override
    public synchronized void setBannedStatus(int userID, boolean bannedStatus) {
        try {
            int temp = 1; // 1s ro gadavcem metods mixurebs ratomgac
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("update users set isBanned = ?, bannDuration = -1  WHERE ID = ?;");
            statement.setBoolean(1, bannedStatus);
            statement.setInt(2, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
    }
	/**
	 * returns all users with given name
	 *
	 * @param name
	 *            user's wanted name
	 * @return ResultSet of rows from users table
	 */
	public synchronized ResultSet getUsersByName(String name) {
		return getUsersBy("name", name);
	}

	/**
	 * adds administrator in database
	 *
	 * @return returns just added administrator as Administrator(class), if
	 *         there went something wrong, returns false.
	 */
	@Override
	public synchronized AdminInterface addAdmin(String mail, String password,
			CategoryTree categoryTree) {
		Administrator admin = null;
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("insert into admins (mail, password) values (?,?);");
			statement.setString(1, mail);
			statement.setString(2, password);
			statement.executeUpdate();

			// I do this, for get admin id
			ResultSet results = null;
			statement = dataBaseConnection
					.prepareStatement("SELECT * from admins where mail = ? and password = ?;");
			int tmp = 1;
			statement.setString(tmp++, mail);
			statement.setString(tmp++, password);
			results = statement.executeQuery();
			if (results != null) {
				if (results.next()) {
					admin = new Administrator(results.getInt("ID"),
							results.getString("mail"), password, true, this,
							categoryTree);
				}
			}
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
			admin = null;
		}
		return admin;
	}

	/**
	 * deletes wanted category from wantedCategories table
	 *
	 * @param wcID
	 *            id of wanted category to be deleted
	 */
	@Override
	public synchronized void deleteWantedCategory(int wcID) {
		deleteByID("wantedCategories", wcID);
	}

	/**
	 * deletes report from reports table
	 *
	 * @param reportID
	 *            id of report to be deleted
	 */
	@Override
	public synchronized void deleteReport(int reportID) {
		deleteByID("reports", reportID);
	}

	/**
	 * deletes from table with name tableName, row which has id given ID
	 *
	 * @param tableName
	 *            identifies table
	 * @param ID
	 *            identifies row
	 */
	private synchronized void deleteByID(String tableName, int ID) {
		PreparedStatement stmt = null;
		try {
			String query = "delete from " + tableName + " where ID = ?;";
			stmt = dataBaseConnection.prepareStatement(query);
			stmt.setInt(1, ID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * adds administrator in database
	 *
	 * @return returns just added administrator as Administrator(class), if
	 *         there went something wrong, returns false.
	 */
	public synchronized AdminInterface addSuperAdmin(String mail,
			String password, CategoryTree categoryTree) {
		SuperAdministrator superAdmin = null;
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("insert into superAdmins (mail, password) values (?,?);");
			statement.setString(1, mail);
			statement.setString(2, password);
			statement.executeUpdate();

			// I do this, for get admin id
			ResultSet results = null;
			statement = dataBaseConnection
					.prepareStatement("SELECT * from superAdmins where mail = ? and password = ?;");
			int tmp = 1;
			statement.setString(tmp++, mail);
			statement.setString(tmp++, password);
			results = statement.executeQuery();
			if (results != null) {
				if (results.next()) {
					superAdmin = new SuperAdministrator(results.getInt("ID"),
							results.getString("mail"), password, true, this,
							categoryTree);
				}
			}
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
			superAdmin = null;
		}
		return superAdmin;
	}

	/**
	 * checks if administrator is already regisrired with given mail
	 *
	 * @return true if is already registrired, otherwise false;
	 */
	@Override
	public synchronized boolean existsAdministrator(String mail) {
		ResultSet results = null;
		boolean existResult = false;
		try {
			int temp = 1; // 1s ro gadavcem metods mixurebs ratomgac
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from admins" + " Where mail=?;");
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
	 * searchs in database users by given criterias
	 *
	 * @param name
	 *            name of user, if name.equals(''), we assume that we don't need
	 *            to specify search by name.
	 * @param bann
	 *            if bann.equals("on"), we assume that user's isActived field is
	 *            true, if bann.equals("off") - false, otherwise we search trues
	 *            and falses too
	 * @param active
	 *            if active.equals("on") we assume that user's isActived field
	 *            is true, if active.equals("off") - false, otherwise we search
	 *            trues and falses too
	 * @return ResultSet of apropriate users
	 */
	private synchronized ResultSet getUsersBy(String name, String bann,
			String active) {
		ResultSet results = null;
		if (name != null && bann != null && active != null) {
			try {
				PreparedStatement statement = dataBaseConnection
						.prepareStatement("select * from users left join tags on users.ID = tags.userID" +
								" Where (users.name like ? or tags.name like ?) and isBanned like ? and isActive like ? group by users.ID;");

				statement.setString(1, name + "%");
				statement.setString(2, name + "%");
				if (bann.equals("on")) {
					statement.setBoolean(3, true);
				} else {
					if (bann.equals("off"))
						statement.setBoolean(3, false);
					else
						statement.setString(3, "%");
				}
				if (active.equals("on")) {
					statement.setBoolean(4, true);
				} else {
					if (active.equals("off"))
						statement.setBoolean(4, false);
					else
						statement.setString(4, "%");
				}
				results = statement.executeQuery();
			} catch (SQLException e) {
				// ignore
				e.printStackTrace();
			}
		}
		return results;
	}

	

	/**
	 * @param on
	 *            if(on) we search banned users, else "bannless" users
	 * @return all banned or "bannless" users
	 */
	public synchronized ResultSet getUsersByBann(boolean on) {
		String bann = "off";
		if (on)
			bann = "on";
		return getUsersBy("", bann, "all");
	}

	/**
	 * searchs in database users by given criterias
	 *
	 * @param name
	 *            name of user, if name.equals(''), we assume that we don't need
	 *            to specify search by name.
	 * @param bann
	 *            if bann.equals("on"), we assume that user's isActived field is
	 *            true, if bann.equals("off") - false, otherwise we search trues
	 *            and falses too
	 * @param active
	 *            if active.equals("on") we assume that user's isActived field
	 *            is true, if active.equals("off") - false, otherwise we search
	 *            trues and falses too
	 * @param categoryID
	 *            category
	 * @return ResultSet of apropriate users
	 */
	private synchronized ResultSet getUsersByCategoryAndCriterias(
			String categoryID, String name, String bann, String active) {
		ResultSet results = null;
		if (categoryID != null && name != null && bann != null
				&& active != null) {
			try {
				PreparedStatement statement = dataBaseConnection
						.prepareStatement("select * from users u left join tags as t on u.ID = t.userID " +
								"inner join users_categories as uc on u.ID = uc.UserID " +
								"inner join categories as c on uc.CategoryID = c.ID "
                                + "where u.ID = uc.UserID and CategoryID = c.ID and c.ID = ? and " +
								"(u.name like ? or t.name like ?) and u.isBanned like ? and u.isActive like ? group by u.ID;");

				statement.setString(1, categoryID);
				statement.setString(2, name + "%");
				statement.setString(3, name + "%");

				if (bann.equals("on")) {
					statement.setBoolean(3, true);
				} else {
					if (bann.equals("off"))
						statement.setBoolean(4, false);
					else
						statement.setString(4, "%");
				}
				if (active.equals("on")) {
					statement.setBoolean(5, true);
				} else {
					if (active.equals("off"))
						statement.setBoolean(5, false);
					else
						statement.setString(5, "%");
				}
				results = statement.executeQuery();
			} catch (SQLException e) {
				// ignore
				e.printStackTrace();
			}
		}
		return results;
	}

	@Override
	public synchronized ResultSet getReports() {
		return reports(-1);
	}

	@Override
	public synchronized ResultSet getReports(int days) {
		return reports(days);
	}

	@Override
	public synchronized ResultSet getWantedCategories(int days) {
		return wantedCategories(days);
	}

	@Override
	public synchronized ResultSet getWantedCategories() {
		return wantedCategories(-1);// -1 means, that date isn't limit
	}

	/**
	 * adds in categories table new category
	 *
	 * @param name
	 *            name of new category
	 * @param parentCategoryId
	 *            parent category's id, if(parentCategoryId is negative) it
	 *            means that this category doesn't have parent
	 * @return id in base, of added category
	 * @throws SQLException
	 *             if something went wrong we throw exception
	 */
	@Override
	public synchronized int addCategory(String name, int parentCategoryId)
			throws SQLException {
		PreparedStatement statement = dataBaseConnection
				.prepareStatement("insert into categories (name, ParentId) values (?,?);");
		if(parentCategoryId < 0){
			statement = dataBaseConnection
					.prepareStatement("insert into categories (name) values (?);");

		}
		statement.setString(1, name);
		if (parentCategoryId > 0) {
			statement.setObject(2, parentCategoryId);
		}
		statement.executeUpdate();

		PreparedStatement stmt;
		if (parentCategoryId < 0) {
			stmt = dataBaseConnection
					.prepareStatement("select ID from categories where name = ? and ParentId is null;");
			stmt.setString(1, name);
		} else {
			stmt = dataBaseConnection
					.prepareStatement("select ID from categories where name = ? and ParentId = ?;");
			stmt.setString(1, name);
			stmt.setInt(2, parentCategoryId);
		}

		ResultSet set = stmt.executeQuery();
		set.next();
		return set.getInt("ID");
	}

	/**
	 * adds new report in database
	 *
	 * @param authorName
	 *            author of report
	 * @param authorUrl
	 *            it will be null or "#", when report's author is visitor
	 * @param text
	 *            report's text
	 * @throws SQLException
	 */
	@Override
	public synchronized void addReport(String authorName, String authorUrl,
			String text) throws SQLException {
		PreparedStatement statement = dataBaseConnection
				.prepareStatement("insert into reports (authorName, text, postDate, authorUrl) values (?, ?, now(), ?);");
		statement.setString(1, authorName);
		statement.setString(2, text);
		statement.setString(3, authorUrl);
		statement.executeUpdate();
	}

	/**
	 * adds new wanted category in wantedCategory table
	 *
	 * @param authorName
	 * @param authorUrl
	 * @param categoryName
	 * @param parentCategoryID
	 * @throws SQLException
	 */
	@Override
	public synchronized void addWantedCategory(String authorName,
			String authorUrl, String categoryName, String parentCategoryID)
			throws SQLException {
		PreparedStatement statement = dataBaseConnection
				.prepareStatement("insert into wantedCategories (authorName, authorID, categoryName, postDate) values (?, ?, ?, now());");
		statement.setString(1, authorName);
		statement.setString(2, authorUrl);
		statement.setString(3, categoryName);
		statement.executeUpdate();
	}

	/**
	 * adds in database new notification
	 *
	 * @param userName
	 * @param userUrl
	 * @param notification
	 *            it must be from enum of notifications
	 * @throws SQLException
	 */
	@Override
	public synchronized void addNotification(String userName, String userUrl,
			SiteConstants.Notification notification) throws SQLException {
		PreparedStatement statement = dataBaseConnection
				.prepareStatement("insert into notifications (notification, userName, userUrl, postDate) values "
                        + "(?, ?, ?, now());");
		statement.setString(1, String.valueOf(notification));
		statement.setString(2, userName);
		statement.setString(3, userUrl);
		statement.executeUpdate();
	}

	/**
	 * searchs all notifications
	 *
	 * @return ResultSet of all notifications
	 */
	@Override
	public synchronized ResultSet getNotifications() {
		return notifications(-1);
	}

	/**
	 * searchs all notifications in last (days) days..
	 *
	 * @return ResultSet of all notifications
	 */
	@Override
	public synchronized ResultSet getNotifications(int days) {
		return notifications(days);
	}

	/**
	 * if days == -1 it means that we need all data, date isn't limit
	 */
	private synchronized ResultSet notifications(int days) {
		return getByTable("notifications", days);
	}

	/**
	 * searches information in given table
	 *
	 * @param days
	 *            the most old information age(in days) to select
	 * @return ResultSet from information from table
	 */
	private synchronized ResultSet getByTable(String table, int days) {
		ResultSet results = null;
		try {
			String dateLimit = "";
			if (days >= 0) {
				dateLimit = "where datediff(now(), postDate) < " + days;
			}
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from " + table + " "
                            + dateLimit + " order by postDate desc;");

			results = statement.executeQuery();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * if days == -1 it means that we need all data, date isn't limit
	 */
	private synchronized ResultSet wantedCategories(int days) {
		ResultSet results = null;
		try {
			String dateLimit = "";
			if (days >= 0) {
				dateLimit = "where datediff(now(), postDate) < " + days;
			}
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from wantedCategories "
                            + dateLimit + " order by postDate desc;");

			results = statement.executeQuery();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * removes marker from markers table
	 *
	 * @param lat
	 *            and lang are coordinates for marker
	 * @param userID
	 *            identifies to which user belongs this marker
	 */
	@Override
	public synchronized void removeMarker(double lat, double lang, int userID) {
		PreparedStatement stmt = null;
		try {
			stmt = dataBaseConnection
					.prepareStatement("delete from markers where lat = ? and lng = ? and userId = ?;");
			stmt.setDouble(1, lat);
			stmt.setDouble(2, lang);
			stmt.setInt(3, userID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * searches reports in database
	 *
	 * @param days
	 *            the most old report age(in days) to select
	 * @return ResultSet from reports table
	 */
	private synchronized ResultSet reports(int days) {
		return getByTable("reports", days);
	}

	/**
	 * adds google-map marker in markers table
	 *
	 * @param name
	 *            name of marker
	 * @param address
	 *            description of marker
	 * @param lat
	 *            latitude
	 * @param lng
	 *            longitude
	 * @param userID
	 *            identifies user, for which we are adding marker
	 * @throws SQLException
	 */
	@Override
	public synchronized void addMarker(String name, String address, double lat,
			double lng, int userID) throws SQLException {
		PreparedStatement statement = dataBaseConnection
				.prepareStatement("insert into markers (name, address, lat, lng, userID) values "
                        + "(?, ?, ?, ?, ?);");
		int tmp = 1;
		statement.setString(tmp++, name);
		statement.setString(tmp++, address);
		statement.setDouble(tmp++, lat);
		statement.setDouble(tmp++, lng);
		statement.setInt(tmp++, userID);
		statement.executeUpdate();
	}

	/**
	 * @param userID
	 *            identifies user, for which we are searching markers
	 * @return resultset of markers table
	 */
	@Override
	public synchronized ResultSet getMarkers(int userID) {
		ResultSet set = null;
		PreparedStatement stmt = null;
		try {
			stmt = dataBaseConnection
					.prepareStatement("select * from markers where userID = ?;");
			stmt.setInt(1, userID);
			set = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}


	@Override
	public synchronized String getConf(int userId) {
		String confCode = "";
		ResultSet results = null;
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from users_confcodes"
                            + " Where userId=?;");
			statement.setInt(1, userId);
			results = statement.executeQuery();
			if (results.next()) {
				confCode = results.getString("confirmCode");
			}

		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return confCode;
	}

	/**
	 * creates admin and deletes user form users table
	 *
	 * @param userID
	 *            id of user, which we want to set as admin
	 * @param categoryTree
	 *            need to create new Administrator
	 * @return new Administrator if everything went ok, else null
	 */
	@Override
	public synchronized Administrator setAdmin(int userID,
			CategoryTree categoryTree) {
		Administrator administrator = null;
		ResultSet set = getUsers(userID);
		try {
			if (set.next()) {
				administrator = (Administrator) addAdmin(set.getString("mail"),
						set.getString("password"), categoryTree);
				deleteUser(userID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return administrator;
	}

	/**
	 * deletes admin from database
	 *
	 * @param adminID
	 *            identifies administrator in admins table
	 */
	@Override
	public synchronized void deleteAdmin(int adminID) {
		PreparedStatement stmt;
		try {
			stmt = dataBaseConnection
					.prepareStatement("delete from admins where ID = ?;");
			stmt.setInt(1, adminID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * deletes user from users table where id == userID
	 *
	 * @param userID
	 *            identifies user in users table
	 */
	public synchronized void deleteUser(int userID) {
		PreparedStatement stmt;
		try {
			stmt = dataBaseConnection
					.prepareStatement("delete from users where ID = ?;");
			stmt.setInt(1, userID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized ResultSet getFonts() {
		return getResults("fonts");
	}

	@Override
	public synchronized ResultSet getThemes() {
		return getResults("themes");
	}

	@Override
	public synchronized ResultSet getElements() {
		return getResults("elements");
	}

	@Override
	public synchronized ResultSet getTexts() {
		return getResults("texts");
	}

    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        //	db.addUser(new User("nika", "nika@yahoo.com", "paroli", "ragaca.com", SiteConstants.Type.email));
        //	db.changeData(3, "ooo");
        //db.activateUser(8);
        //db.bannUserByDays(3, 30);

        //db.addSuperAdmin("nika", generatePassword("paroli12"), null);
        //User user = db.addUser("bondo", "ddd@ddd.aa", "123456", "mevdawiwini", SiteConstants.getType("email"));
        //db.activateUser(user.getID());
        CategoryTree tree = new CategoryTree(null);
        Category cur = new Category(1, "zgarbi");
        tree.add(cur, null);
        ArrayList<CategoryInterface> arr = (ArrayList<CategoryInterface>)tree.getRoots();
        for(int i = 0; i < arr.size(); i++){
        	System.out.println(arr.get(i));
        }

        db.addSuperAdmin("nika", generatePassword("paroli12"), null);
 //       User user = db.addUser("bondo", "ddd@ddd.aa", "123456", "mevdawiwini", SiteConstants.getType("email"));
   //     db.activateUser(user.getID());
    }
    
    
	@Override
	public synchronized ResultSet getElementsInfo() {
		return getResults("elements_info");
	}

	@Override
	public synchronized ResultSet getAlboms() {
		return getResults("alboms");
	}

	@Override
	public synchronized ResultSet getAlbomsElements() {
		return getResults("alboms_elements");
	}

	@Override
	public synchronized ResultSet getAlbomsPictures() {
		return getResults("alboms_pictures");
	}

	@Override
	public synchronized ResultSet getElementsPictures() {
		return getResults("elements_pictures");
	}

	@Override
	public synchronized ResultSet getAdmins() {
		return getResults("admins");

	}

	@Override
	public synchronized ResultSet getUsersByCategories(
			List<CategoryInterface> categories) {
		ResultSet results = null;
		String statementString = " select DISTINCT u.* from users as u "+
"inner join users_categories as uc "+
"on u.ID=uc.UserID "+
"where u.isActive=1 and uc.CategoryID  in (";
		for (int i = 0; i <categories.size(); i++) {
			statementString+=categories.get(i).getId();
			if(i!=categories.size()-1)
			statementString+=",";
		}
		statementString+=");";
		try {
			System.out.println(statementString);
			PreparedStatement statement = dataBaseConnection
					.prepareStatement(statementString);

			results = statement.executeQuery();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public synchronized ResultSet getUsers(int id) {
		return getResults("users", id);
	}

	@Override
	public synchronized ResultSet getCategories(int id) {
		return getResults("categories", id);
	}

	@Override
	public synchronized ResultSet getUsersCategories(int id) {
		return getResults("users_categories", id);
	}

	@Override
	public synchronized ResultSet getPictures(int id) {
		return getResults("pictures", id);
	}

	@Override
	public synchronized ResultSet getColors(int id) {
		return getResults("colors", id);
	}

	@Override
	public synchronized ResultSet getFonts(int id) {
		return getResults("fonts", id);
	}

	@Override
	public synchronized ResultSet getThemes(int id) {
		return getResults("themes", id);
	}

	@Override
	public synchronized ResultSet getElements(int id) {
		return getResults("elements", id);
	}

	@Override
	public synchronized ResultSet getTexts(int id) {
		return getResults("texts", id);
	}

	@Override
	public synchronized ResultSet getElementsInfo(int id) {
		return getResults("elements_info", id);
	}

	@Override
	public synchronized ResultSet getAlboms(int id) {
		return getResults("alboms", id);
	}

	@Override
	public synchronized ResultSet getAlbomsElements(int id) {
		return getResults("alboms_elements", id);
	}

	@Override
	public synchronized ResultSet getAlbomsPictures(int id) {
		return getResults("alboms_pictures", id);
	}

	@Override
	public synchronized ResultSet getElementsPictures(int id) {
		return getResults("elements_pictures", id);
	}

	@Override
	public synchronized ResultSet getAdmins(int id) {
		return getResults("admins", id);

	}

	/*
	 * getUsers if not exist return null (non-Javadoc)
	 * 
	 * @see core.database.Connection#getUser(java.lang.String, java.lang.String)
	 */

	/*
	 * Checks if user exist in database by email (non-Javadoc)
	 *
	 * @see core.database.Connection#existsUser(java.lang.String)
	 */
	@Override
	public synchronized boolean existsUserWithMail(String email) {
		return existsUser("mail", email);
	}

	@Override
	public synchronized boolean existsUserWithUrl(String url) {
		return existsUser("url", url);
	}

	@Override
	public boolean existsUserWithID(int id) {
		return existsUser("ID", "" + id);
	}


    /**
     * checks if user is banned
     * @param id identifies
     * @return true if user is banned else false
     */
    @Override
    public boolean isBannedUser(int id) {
        boolean b = false;
        ResultSet results = null;
        try {
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("select * from users" + " Where ID=?;");
            statement.setInt(1, id);
            results = statement.executeQuery();
            if (results != null && results.next()) {
                if (results.getBoolean("isBanned") == true) {
                    b = true;
                }
            }
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean checkBannDate(int userID) {
        boolean b = false;
        ResultSet results = null;
        try {
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("select DATEDIFF(now(), bannDate) as dateDifference, bannDuration from users where id = ?;");
            statement.setInt(1, userID);
            results = statement.executeQuery();
            if (results != null){
                if(results.next()){
                    int bannDuration = results.getInt("bannDuration");
                    if(bannDuration >= 0){
                        int dateDifference = results.getInt("dateDifference");
                        if(dateDifference >= bannDuration){
                            setBannedStatus(userID, false);
                            b = true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }

        return b;
    }

    /**
     * banns user by days, after given days user will release from bann
     * @param userID identifies user
     * @param days bann duration
     */
    @Override
    public void bannUserByDays(int userID, int days) {
        try {
            int temp = 1; // 1s ro gadavcem metods mixurebs ratomgac
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("update users set isBanned = ?,bannDuration = ?, bannDate = now()  WHERE ID = ?;");
            statement.setBoolean(1, true);
            statement.setInt(2, days);
            statement.setInt(3, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
    }

    /**
     * adds tag in database
     * @param userId identifies user
     * @param name name of tag
     */
    @Override
    public void addTag(int userId, String name) {
        try {
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("insert into tags (name, userID) values (?,?);");
            statement.setString(1, name);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
    }

    private synchronized boolean existsUser(String column, String value) {
		ResultSet results = null;
		boolean existResult = false;
		try {
			String stmt = "select * from users" + " Where " + column + "=?;";
			PreparedStatement statement = dataBaseConnection
					.prepareStatement(stmt);
			statement.setString(1, value);
			results = statement.executeQuery();
			if (results != null)
				existResult = results.next();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return existResult;
	}


   

    /**
     * searchs in database users by given criterias
     *
     * @param name       name of user, if name.equals(''), we assume that we don't need
     *                   to specify search by name.
     * @param bann       if bann.equals("on"), we assume that user's isActived field is
     *                   true, if bann.equals("off") - false, otherwise we search trues
     *                   and falses too
     * @param active     if active.equals("on") we assume that user's isActived field
     *                   is true, if active.equals("off") - false, otherwise we search
     *                   trues and falses too
     * @param categoryID category
     * @return ResultSet of apropriate users
     */
    public synchronized ResultSet getUsersByCriterias(String name, String bann,
                                         String active, String categoryID) {
        ResultSet set = null;
        if (name != null && bann != null && categoryID != null
                && active != null) {
            if (categoryID.equals("default") || categoryID.equals("")) {
                set = getUsersBy(name, bann, active);
            } else {
                set = getUsersByCategoryAndCriterias(categoryID, name, bann,
                        active);
            }

        }
        return set;
    }


    
	@Override
	/*
	 * Changing page data for this user id (non-Javadoc)
	 * 
	 * @see core.database.Connection#changeData(int, java.lang.String)
	 */
	public synchronized void changeData(int id, String data) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("Update user_page Set page=? where userId=?;");
			statement.setString(1, data);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
	}

	@Override
	/*
	 * returnes page data whith this user, if problem occured retuns empty
	 * (non-Javadoc)
	 * 
	 * @see core.database.Connection#getData(int)
	 */
	public synchronized String getData(int id) {
		// TODO Auto-generated method stub
		ResultSet results = null;

		String res = "";
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from user_page where UserId=?;");
			statement.setInt(1, id);
			results = statement.executeQuery();
			if (results != null && results.next())
				res = results.getString("page");
			System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;

	}

	/*
	 * Adds all categories id's for users (non-Javadoc)
	 *
	 * @see core.database.Connection#addUserCategories(int, java.util.Vector)
	 */
	@Override
	public synchronized void addUserCategories(int id,
			Vector<Integer> categories) {
		// TODO Auto-generated method stub

		try {

			for (int i = 0; i < categories.size(); i++) {
				PreparedStatement statement = dataBaseConnection
						.prepareStatement("insert into users_categories  (UserID, CategoryID) values (?,?);");
				statement.setInt(1, id);
				statement.setInt(2, categories.get(i));
				statement.executeUpdate();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	//just for teasting 
	public boolean existsTag(String tag) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
    public synchronized void updateName(int userID, String name) {
        try {
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("update users set name = ?  WHERE ID = ?;");
            statement.setString(1, name);
            statement.setInt(2, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
    }
	
	@Override
    public synchronized void updateMail(int userID, String mail) {
        try {
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("update users set mail = ?  WHERE ID = ?;");
            statement.setString(1, mail);
            statement.setInt(2, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
    }
	
	@Override
    public synchronized void updatePassword(int userID, String paswrd) {
		System.out.println(paswrd+userID);
        try {
            PreparedStatement statement = dataBaseConnection
                    .prepareStatement("update users set password = ?  WHERE ID = ?;");
            statement.setString(1, paswrd);
            statement.setInt(2, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            // ignore
            e.printStackTrace();
        }
    }

	public synchronized void updateURL(int id, String url) {
		 try {
	            PreparedStatement statement = dataBaseConnection
	                    .prepareStatement("update users set url = ?  WHERE ID = ?;");
	            statement.setString(1, url);
	            statement.setInt(2, id);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            // ignore
	            e.printStackTrace();
	        }
		
	}
/*
 * This emthod sets user Image
 * (non-Javadoc)
 * @see core.database.Connection#addImage(int, java.lang.String)
 */
	@Override
	public synchronized void addImage(int id, String image) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try {
			stmt = dataBaseConnection
					.prepareStatement("update users set avatarFile = ? where ID = ?;");
			stmt.setString(1, image);
			stmt.setInt(2, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}  

}
