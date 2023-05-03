package model;

/**
 * A class to store information about accounts in the FileNtro app.
 *
 * @author Thinh Le
 * @Author Riley Bennett
 */
public class Account {
  
  /** The name associated with the account. */
	String name;
	
  /** The email associated with the account. */
	String email;

	/**
	 * Constructor that creates an account with the given name and email.
	 */
	public Developer(String name, String email) {
		this.name = name;
		this.email = email;
	}

  /**
   * A getter for the name associated with the account.
   *
   * @return The name of this account.
   */
	public String getName(){
		return name;
	}
  
  /**
   * A getter for the email associated with this account.
   *
   * @return The email of this account.
   */
	public String getEmail(){
		return email;
	}
}


}
