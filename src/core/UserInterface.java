package core;

/**
 * Created by nika on 5/24/15.
 */
public interface UserInterface {
    public void getEmail();

    //we save not passwords, we save hashStrings as password..
    public void getPassword();

    public void getID();
}
