package model;
/**
 * A class to store information of the user of the app/
 * 
 * @author Bairu Li
 * @author Riley Bennett
 * @version 0.1
 */
public class User extends Account {
	
	public User(final String theName, final String theEmail) {
		super(theEmail, theEmail);
	}
	
	public void setName(final String theName) {
		name = theName;
	}
	
	public void setEmail(final String theEmail) {
		email = theEmail;
	}
}
