package core;
/*
 * Author guri
 */
public final class SiteConstants {
	public static final double USER_IMG_HEIGTH=80;
	public static final double USER_IMG_WIDTH=80;
	public static final String DOMAIN = "chveniSaiti.ge";

	public enum Type {
		email, facebook, googlePlus
	}

	public static Type getType(String type) {
		if (type.equalsIgnoreCase("email"))
			return Type.email;
		if (type.equalsIgnoreCase("fb") || type.equalsIgnoreCase("facebook"))
			return Type.facebook;
		if (type.equalsIgnoreCase("googlePlus") || type.equalsIgnoreCase("gp"))
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