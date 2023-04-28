package model;
/**
 * Owner of the app information.
 * 
 * @author Bairu Li
 */
public class User {
	String myName;
	String myEmail;
	
    public User(final String theName, final String theEmail) {
    	myName = theName;
    	myEmail = theEmail;
    }

	public String getName() {
		return myName;
	}

	public String getEmail() {
		return myEmail;
	}
	
	public void setName(final String theName) {
		myName = theName;
	}
	
	public void setEmail(final String theEmail) {
		myEmail = theEmail;
	}
}
