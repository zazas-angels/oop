package core;

/*
 * Author guri, celqi pranwia..
 */
public final class SiteConstants {
	public static final double USER_IMG_HEIGTH = 80;
	public static final double USER_IMG_WIDTH = 80;
	public static final String DOMAIN = "arran.ge";

	public static final String ADMINS_MAP_NAME = "admins_map";
	public static final String USERS_MAP_NAME = "users_map";
	public static final String SESSIONS_MAP_ADMINS = "sessions_map_admins";
	public static final String SESSIONS_MAP_USERS = "sessions_map_users";
	public static final String LOGIN_COOKIE_NAME = "login_session_id";
	public static final String DATABASE = "database";
	public static final String CATEGORY_TREE = "categories";



	public static final String BUSY_MAIL = " ეს მეილი უკვე გამოყენებულია ";
	public static final String BUSY_URL = " ასეთი url დაკავებულია ";


	public enum Notification {
		createdUser
	}

	// inner html taggs constants for user page
	private static final String innertagName = "innerelement";
	public static final String startInnerTagName = "<" + innertagName + ">";
	public static final String endInnerTagName = "</" + innertagName + ">";


	public enum Type {
		email, facebook, googlePlus
	}

	public static Type getType(String type) {
		if (type == null) return null;
		if (type.equalsIgnoreCase("email"))
			return Type.email;
		if (type.equalsIgnoreCase("fb") || type.equalsIgnoreCase("facebook"))
			return Type.facebook;
		if (type.equalsIgnoreCase("googlePlus") || type.equalsIgnoreCase("gp") || type.equalsIgnoreCase("g+"))
			return Type.googlePlus;
		return null;
	}

	public static String typeToString(Type type) {
		if (type == Type.email)
			return "email";
		if (type == Type.facebook)
			return "facebook";
		if (type == Type.googlePlus)
			return "gp";
		return null;
	}
}
