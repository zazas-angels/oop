package core.database;

/*
 * Author: guri
 */
import core.SiteConstants;
import core.SiteConstants.Type;
import core.category.CategoryInterface;
import core.user.User;
import core.user.UserInterface;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* This class is for data base connection, it connects and can make suitable prepared statement.
 * like (similar code) as in assignment 3.
 */
public class DBConnection implements core.database.Connection {
	Connection dataBaseConnection;

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
		db.addUser(new User("nika", "nika@yahoo.com", "paroli", "ragaca.com", SiteConstants.Type.email));

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
		ResultSet results = null;
		try {
			// ricxvebze mixurebs setString-s rom gadavcem da nu cvlit ra..
			int temp1 = 1;
			int temp2 = 2;
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from users"
							+ " Where mail=? and password =?;");
			statement.setString(temp1, mail);
			statement.setString(temp2, password);
			results = statement.executeQuery();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		if (results == null)
			return null;
		UserInterface user = null;
		try {
			if (results.next()) {
				user = new User(results.getString("name"),mail, password,
						results.getString("url"), SiteConstants.getType(results.getString("type")));
			}
		} catch (SQLException e) {
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
		
		return checkByMail(email,"users");
	}
	/*
	 * Checks if mail exists in table and returns result
	 */
	private boolean checkByMail(String email, String table){
		ResultSet results = null;
		boolean existResult = false;
		try {
			int temp = 1; // 1s ro gadavcem metods mixurebs ratomgac
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from "+table
							+ " Where mail=?;");
			statement.setString(temp, email);
			results = statement.executeQuery();
			if (results != null)
				existResult = results.next();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return existResult;
	}
	/*
	 * checks if administrator exists and returnes result
	 * (non-Javadoc)
	 * @see core.database.Connection#existsAdministrator(java.lang.String)
	 */
	@Override
	public boolean existsAdministrator(String email) {
		return checkByMail(email,"admins");
	}

	@Override
	public UserInterface getAdmin(String email, String password) {
		UserInterface admin=null;
		ResultSet results = null;
		try {
			int one = 1; // 1s ro gadavcem metods mixurebs ratomgac
			int two=2;
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from admins"
							+ " Where mail=?&&password=?;");
			statement.setString(one, email);
			statement.setString(two, password);
			results = statement.executeQuery();
			if (results != null&&results.next()){
				admin = new User("",results.getString("mail"),results.getString("password"),"",null);;
			}
			
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return admin;
	}



	@Override
	public boolean isActiveUser(int id) {
		boolean result = false;
		ResultSet userRes = getUsers(id);
		try {
			if(userRes!=null&&userRes.next()){
				result=userRes.getString("isActive")=="true";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/*
	 * Activates user. returns 0 if everything is allright, 1 otherwise
	 * (non-Javadoc)
	 * @see core.database.Connection#activateUser(int)
	 */
	@Override
	public int activateUser(int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("Update users	set isActive = true    where id=?;");
			int one=1;
			
					statement.setInt(one, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	@Override
	public int insertUserConfCode(int UserId, String confCode) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteUserConfCode(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	/*
	 * set Banned of user, returns 0 if everythin is allright else 1
	 * (non-Javadoc)
	 * @see core.database.Connection#setBannedStatus(core.user.UserInterface, boolean)
	 */
	public int setBannedStatus(int id, boolean bannedStatus) {
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("Update users	set isBanned =?   where id=?;");
			int one=1;
			int two=2;
					statement.setBoolean(one, bannedStatus);
					statement.setInt(two, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	/*
	 * Returnes users by name
	 * (non-Javadoc)
	 * @see core.database.Connection#getUsersByName(java.lang.String)
	 */
	@Override
	public List<UserInterface> getUsersByName(String name) {
		// TODO Auto-generated method stub
		List<UserInterface>  usersList = null;
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from users where name=?;");
			int one=1;
			statement.setString(one, name);
			ResultSet results = statement.executeQuery();
			usersList=new ArrayList<UserInterface>();
			fillUsers(results,usersList);
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		
		return usersList;
	}
	//Fills user with result set and returns result
	private void fillUsers(ResultSet results, List<UserInterface> usersList) throws SQLException {
		while(results.next()){
			Type type=null;
			String stringType =results.getString("type");
			if(stringType=="email"){
				type=Type.email;
			}else
			if(stringType=="facebook"){
				type=Type.facebook;
			}else
			if(stringType=="googlePlus"){
				type=Type.googlePlus;
			}
			UserInterface user = new User(results.getString("name"),results.getString("mail"),results.getString("password"),
					results.getString("password"),type);
			usersList.add(user);
		}
		
	}
}
