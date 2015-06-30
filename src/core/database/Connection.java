/*
 * Author guri
 */
package core.database;

import core.SiteConstants;
import core.SiteConstants.Type;
import core.administrator.AdminInterface;
import core.administrator.Administrator;
import core.administrator.SuperAdministrator;
import core.category.CategoryInterface;
import core.category.CategoryTree;
import core.user.User;
import core.user.UserInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public interface Connection {
	public ResultSet getUsers();
	public ResultSet getCategories();
	public ResultSet getUsersCategories();
	public ResultSet getPictures();
	public ResultSet getColors();
	public ResultSet getFonts();
	public ResultSet getThemes();
	public ResultSet getElements();
	public ResultSet getTexts();
	public ResultSet getElementsInfo();
	public ResultSet getAlboms();
	public ResultSet getAlbomsElements();
	public ResultSet getAlbomsPictures();
	public ResultSet getElementsPictures();
	public ResultSet getAdmins();
	
	
	//get users by categories list, ordered by rating 
	public ResultSet getUsersByCategories(List<CategoryInterface>  categories);

	
	/**
	 * This method returns all results form table with specified id
	 */
	public ResultSet getUsers( int id);
	public ResultSet getCategories( int id);
	public ResultSet getUsersCategories( int id);
	public ResultSet getPictures( int id);
	public ResultSet getColors( int id);
	public ResultSet getFonts( int id);
	public ResultSet getThemes( int id);
	public ResultSet getElements( int id);
	public ResultSet getTexts( int id);
	public ResultSet getElementsInfo( int id);
	public ResultSet getAlboms( int id);
	public ResultSet getAlbomsElements (int id);
	public ResultSet getAlbomsPictures( int id);
	public ResultSet getElementsPictures( int id);
	public ResultSet getAdmins( int id);
	public void changeData(int id, String Data);
	public String getData(int id);

	/*
	 * Add user in database (non-Javadoc)
     *
     * @see core.database.Connection#addUser(core.user.UserInterface)
     */

	public UserInterface getUser(String email, String password);

	public AdminInterface getAdmin(String email, String password, CategoryTree categoryTree);

	public void insertUserConfCode(int UserId, String confCode);
	
	public boolean isActiveUser(int id);
	
	public void activateUser(int id);
	
	public void deleteUserConfCode(int userId);

	public void setBannedStatus(int userId, boolean bannedStatus);

	public AdminInterface addAdmin(String mail, String password, CategoryTree categoryTree);


	public boolean existsAdministrator(String mail);

	/*
	 * Checks if user exist in database by email (non-Javadoc)
	 *
	 * @see core.database.Connection#existsUser(java.lang.String)
	 */
	boolean existsUserWithMail(String email);

	boolean existsUserWithUrl(String url);

	public ResultSet getReports();

	public ResultSet getReports(int days);

	public ResultSet getWantedCategories(int days);

	public ResultSet getWantedCategories();

	public int addCategory(String name, int parentCategoryId) throws SQLException;

	public void addReport(String authorName, String authorUrl, String text) throws SQLException;

	public void addWantedCategory(String authorName, String authorUrl, String categoryName, String parentCategoryID) throws SQLException;

	public void addNotification(String userName, String userUrl, SiteConstants.Notification notification) throws SQLException;

	public ResultSet getNotifications();

	public ResultSet getNotifications(int days);

	public void addMarker(String name, String address, double lat, double lang, int userID) throws SQLException;

	public void removeMarker(double lat, double lang, int userID) throws SQLException;

	public ResultSet getMarkers(int userID);
	
	public String getConf(int UserId);

	public Administrator setAdmin(int userID, CategoryTree categoryTree);

	public void deleteAdmin(int adminID);

	public void deleteWantedCategory(int wcID);

	public void deleteReport(int reportID);

	public User addUser(String name, String email, String password, String url, Type type);

	public SuperAdministrator getSuperAdmin(String email, String password, CategoryTree categoryTree);
	
	public void addUserCategories(int id, Vector<Integer> categories);

	public boolean existsUserWithID(int id);

	public boolean isBannedUser(int userID);

	public boolean checkBannDate(int userID);

	public void bannUserByDays(int userID, int days);

	public void addTag(int userId, String name);
	public boolean existsTag(String tag);
}
