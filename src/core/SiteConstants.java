package core;

/*
 * Author guri
 */
public final class SiteConstants {
	public static final double USER_IMG_HEIGTH = 80;
	public static final double USER_IMG_WIDTH = 80;
	public static final String DOMAIN = "chveniSaiti.ge";
	// inner html taggs constants for user page
	private static final String innertagName = "innerelement";
	public static final String startInnerTagName = "<" + innertagName + ">";
	public static final String endInnerTagName = "</" + innertagName + ">";

	public enum Type {
		email, facebook, googlePlus
	}

	public static Type getType(String type) {
		if (type.equalsIgnoreCase("email"))
			return Type.email;
		if (type.equalsIgnoreCase("facebook"))
			return Type.facebook;
		if (type.equalsIgnoreCase("googlePlus"))
			return Type.googlePlus;
		return null;
	}
}