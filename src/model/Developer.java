package model;

/**
 * A class to hold information about the developers of the FileNtro project.
 *
 * @author Thinh Le
 * @version 0.1
 */

public class Developer {




	// name of the developers
	String name;
	// name of the email of developers
	String email;

	/**
	 *
	 *  This is the constructor of Developer that has name and email
	 */
	public Developer(String name, String email) {

		this.name = name;
		this.email = email;



	}

	public String getName(){
		return name;
	}
	public String getEmail(){
		return email;
	}

	//Main GUI call this class and type name and email


}

