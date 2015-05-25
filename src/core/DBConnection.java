package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/* This class is for data base connection, it connects and can make suitable prepared statement.
 * like (similar code) as in assignment 3.
 */
public class DBConnection implements core.Connection {
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
	 * This method makes prepared statement using it's id value (if it's "" id
	 * won't be specified) And returns select preperaed statement
	 */
	// it is synchronized for after, no need but mainc
	public synchronized PreparedStatement getStatement(String id) {
		PreparedStatement statement = null;
		String condition = "true";
		if (id.length() != 0) {
			condition = " productid = \"" + id + "\"";
		}
		try {
			statement = dataBaseConnection
					.prepareStatement("select * from products" + " Where "
							+ condition + " ;");
			/*
			 * another teqnique for another generation if(id.length()==0){
			 * statement.setString(1, "true"); }else{ statement.setString(1,
			 * id); }
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
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
					.prepareStatement("select * from "+tableName+" ;");

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
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from +tableName"+" Where id=?;");
			statement.setInt(1, id);
			results = statement.executeQuery();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return results;
	}

	public static void main(String[] args) throws SQLException {
		DBConnection db = new DBConnection();
		System.out.println("zaza");
		ResultSet users =db.getUsers();
if(users!=null){
	System.out.println("zaza");
	while(users.next()){
	
		System.out.println("zaza");
		System.out.println( users.getString(1));
	
	}
}

	}

	@Override
	public ResultSet getUsers() {
		ResultSet results = null;
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from users Order by raiting desc;");

			results = statement.executeQuery();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public ResultSet getCategories() {
		return getResults("categories");
	}

	@Override
	public ResultSet getUsersCategories() {
		return 	getResults("users_categories");
	}

	@Override
	public ResultSet getPictures() {
		return getResults("pictures");
	}

	@Override
	public ResultSet getColors() {
		return  getResults("colors");
	}

	@Override
	public ResultSet getFonts() {
		return 	getResults("fonts");
	}

	@Override
	public ResultSet getThemes() {
		return 	 getResults("themes");
	}

	@Override
	public ResultSet getElements() {
		return getResults("elements");
	}

	@Override
	public ResultSet getTexts() {
		return  getResults("texts");
	}

	@Override
	public ResultSet getElementsInfo() {
		return 	getResults("elements_info");
	}
	
	
	
	@Override
	public ResultSet getAlboms() {
		return 	getResults("alboms");
	}

	@Override
	public ResultSet getAlbomsElements() {
		return  getResults("alboms_elements");
	}

	@Override
	public ResultSet getAlbomsPictures() {
		return  getResults("alboms_pictures");
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
	public ResultSet getUsers(int id) {
		
		return 	getResults("users",id);
	}

	@Override
	public ResultSet getCategories(int id) {
		return getResults("categories",id);
	}

	@Override
	public ResultSet getUsersCategories(int id) {
		return 	getResults("users_categories",id);
	}

	@Override
	public ResultSet getPictures(int id) {
		return getResults("pictures",id);
	}

	@Override
	public ResultSet getColors(int id) {
		return  getResults("colors",id);
	}

	@Override
	public ResultSet getFonts(int id) {
		return 	getResults("fonts",id);
	}

	@Override
	public ResultSet getThemes(int id) {
		return 	 getResults("themes",id);
	}

	@Override
	public ResultSet getElements(int id) {
		return getResults("elements",id);
	}

	@Override
	public ResultSet getTexts(int id) {
		return  getResults("texts",id);
	}

	@Override
	public ResultSet getElementsInfo(int id) {
		return 	getResults("elements_info",id);
	}
	
	
	
	@Override
	public ResultSet getAlboms(int id) {
		return 	getResults("alboms",id);
	}

	@Override
	public ResultSet getAlbomsElements(int id) {
		return  getResults("alboms_elements",id);
	}

	@Override
	public ResultSet getAlbomsPictures(int id) {
		return  getResults("alboms_pictures",id);
	}
	
	
	@Override
	public ResultSet getElementsPictures(int id) {
		return getResults("elements_pictures",id);
	}

	@Override
	public ResultSet getAdmins(int id) {
		return getResults("admins",id);
		
	}

	@Override
	public ResultSet getUsersByCategories(List<CategoryInterface> categories) {
		// TODO Auto-generated method stub
		ResultSet results = null;
		String categoryIds="";
		//don't need ? it is int
		for(int i=0;i<categories.size(); i++){
			categoryIds+=categories.get(i).getId();
			if(i==categories.size()-1)continue;
			categoryIds+=",";
		}
		System.out.println(categoryIds);
		try {
			PreparedStatement statement = dataBaseConnection
					.prepareStatement("select * from users as u join users_categories as uc on u.ID=uc.UserID	where uc.CategoryID in ("+
			categoryIds+") 	group by u.ID order by raiting desc;");
			results = statement.executeQuery();
		} catch (SQLException e) {
			// ignore
			e.printStackTrace();
		}
		return results;
	}

}
