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
		if (type.equalsIgnoreCase("facebook"))
			return Type.facebook;
		if (type.equalsIgnoreCase("googlePlus"))
			return Type.googlePlus;
		return null;
	}
}