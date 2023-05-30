package model;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.UUID;

/**
 * A class to store information about accounts in the FileNtro app.
 *
 * @author Thinh Le
 * @author Riley Bennett
 * @version 0.3
 */
public class Account implements Jsonable {
	/** The id of this account. */
	String id;
  
	/** The name associated with the account. */
	String name;
	
	/** The email associated with the account. */
	String email;

	/**
	 * Constructor that creates an account with the given name and email.
	 * @author Riley Bennett
	 * @author Thinh Le
	 * @author Tin Phu
	 * @param name The name of the user.
	 * @param email The email of the user.
	 */
	public Account(String name, String email) {
		this.name = name;
		this.email = email;
		this.id = UUID.randomUUID().toString();
	}

	/**
	 * Constructor for data mapping.
	 * @author Tin Phu
	 * @param name The name of the account
	 * @param email The email of the account
	 * @param theId The id of the account
	 */

	public Account(String name, String email, String theId) {
		this.name = name;
		this.email = email;
		this.id = theId;
	}

  	/**
   	* A getter for the name associated with this account.
   	* @author Thinh Le
   	* @return The name of this account.
   	*/
	public String getName(){
		return name;
	}

	/**
	 * A getter for the id associated with this account.
	 * @author Tin Phu
	 * @return The id of the account
	 */
	public String getId() {
		return id;
	}

  	/**
   	* A getter for the email associated with this account.
   	* @author Thinh Le
   	* @return The email of this account.
   	*/
	public String getEmail(){
		return email;
	}

	/**
	 * Converts this account to JSON formatted stream.
	 * @author Tin Phu
	 * @return String JSON formatted stream of this account
	 */
	@Override
	public String toJson() {
		final StringWriter writable = new StringWriter();
		try {
			this.toJson(writable);
		} catch (final IOException e) {
		}
		return writable.toString();
	}

	/**
	 * Converts this account to JSON formatted stream.
	 * @author Tin Phu
	 * @param writer Writer to write to stream with
	 * @throws IOException
	 */
	@Override
	public void toJson(Writer writer) throws IOException {
		final JsonObject json = new JsonObject();
		json.put("id", this.id);
		json.put("name", this.name);
		json.put("email", this.email);

		json.toJson(writer);
	}

	/**
	 * Returns String representation of this account.
	 * @author Tin Phu
	 * @return String representation of this account
	 */
	@Override
	public String toString(){
		return "id:" + this.id + "|name:" + this.name + "|email:" + this.email + "\n";
	}
}



