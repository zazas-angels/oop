package core;

/**
 * Created by nika on 5/24/15.
 */
public interface UserInterface {
    public String getEmail();
    //we save not passwords, we save hashStrings as password..
    public String getPassword();

    public int getId();
    public String getURL();
}
