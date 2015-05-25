package core;

import java.sql.ResultSet;
import java.util.List;

public interface Connection {
	/**
	 * This method returns all results form table
	 */
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
}
