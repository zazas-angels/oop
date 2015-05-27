package core.user;

import core.SiteConstants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements UserInterface {
	private String name;
	private String mail;
	private String password;
	private String url;

	public User(String name,String mail, String password, String url) {
		this.name=name;
		this.mail = mail;
		this.password = generatePassword(password);
		this.url = url + "." + SiteConstants.DOMAIN;
	}

	private String generatePassword(String password) {
		password = "/" + password;
		try {
            MessageDigest ms = MessageDigest.getInstance("SHA");
            ms.update(password.getBytes());
            byte[] digest = ms.digest();
			return (hexToString(digest));
		} catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
		return null;
	}

	/*
	 Given a byte[] array, produces a hex String,
     such as "234a6f". with 2 chars for each byte in the array.
    */
	private String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val < 16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}

	@Override
	public String getEmail() {
		return mail;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getURL() {
		return url;
	}
	/*
	 * Returns name of user
	 * (non-Javadoc)
	 * @see core.user.UserInterface#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

}