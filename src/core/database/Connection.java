/*
 * Author guri
 */
package core.database;

import core.administrator.AdminInterface;
import core.administrator.Administrator;
import core.category.CategoryInterface;
import core.user.UserInterface;

import java.sql.ResultSet;
import java.util.List;

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

	public ResultSet getAlbums(int id);

	public ResultSet getAlbumsElements(int id);

	public ResultSet getAlbumsPictures(int id);
	public ResultSet getElementsPictures( int id);
	public ResultSet getAdmins( int id);


	/**
	 * returns 0 if successfully added else 1
	 */
	public int addUser(UserInterface user);

	public UserInterface getUser(String email, String password);

	public boolean existsUser(String email);

	public AdminInterface getAdmin(String email, String password);

	public int insertUserConfCode(int UserId, String confCode);
	
	public boolean isActiveUser(int id);
	
	public int activateUser(int id);
	
	public int deleteUserConfCode(int id);

	public void setBannedStatus(int id, boolean bannedStatus);

	void setBannedStatus(UserInterface user, boolean bannedStatus);

	public boolean addAdmin(Administrator administrator);

	public boolean existsAdministrator(String mail);

	public ResultSet getReports();

	public ResultSet getReports(int days);

	public ResultSet getWantedCategories(int days);

	public ResultSet getWantedCategories();
}
