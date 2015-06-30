package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import core.SiteConstants.Notification;
import core.SiteConstants.Type;
import core.administrator.AdminInterface;
import core.administrator.Administrator;
import core.administrator.SuperAdministrator;
import core.category.CategoryInterface;
import core.category.CategoryTree;
import core.database.Connection;
import core.user.User;
import core.user.UserInterface;

public class StubDBConnection implements Connection {

	@Override
	public ResultSet getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getUsersCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getPictures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getColors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getFonts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getThemes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getTexts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getElementsInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getAlboms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getAlbomsElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getAlbomsPictures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getElementsPictures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getAdmins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getUsersByCategories(List<CategoryInterface> categories) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getUsers(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getCategories(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getUsersCategories(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getPictures(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getColors(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getFonts(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getThemes(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getElements(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getTexts(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getElementsInfo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getAlboms(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getAlbomsElements(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getAlbomsPictures(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getElementsPictures(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getAdmins(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeData(int id, String Data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getData(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInterface getUser(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminInterface getAdmin(String email, String password,
			CategoryTree categoryTree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUserConfCode(int UserId, String confCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActiveUser(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void activateUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserConfCode(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBannedStatus(int userId, boolean bannedStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AdminInterface addAdmin(String mail, String password,
			CategoryTree categoryTree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsAdministrator(String mail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsUserWithMail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsUserWithUrl(String url) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getReports() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getReports(int days) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getWantedCategories(int days) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getWantedCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addCategory(String name, int parentCategoryId)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addReport(String authorName, String authorUrl, String text)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addWantedCategory(String authorName, String authorUrl,
			String categoryName, String parentCategoryID) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNotification(String userName, String userUrl,
			Notification notification) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet getNotifications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getNotifications(int days) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMarker(String name, String address, double lat, double lang,
			int userID) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMarker(double lat, double lang, int userID)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet getMarkers(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConf(int UserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Administrator setAdmin(int userID, CategoryTree categoryTree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAdmin(int adminID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteWantedCategory(int wcID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReport(int reportID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User addUser(String name, String email, String password, String url,
			Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperAdministrator getSuperAdmin(String email, String password,
			CategoryTree categoryTree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserCategories(int id, Vector<Integer> categories) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsUserWithID(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBannedUser(int userID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkBannDate(int userID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void bannUserByDays(int userID, int days) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTag(int userId, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsTag(String tag) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
