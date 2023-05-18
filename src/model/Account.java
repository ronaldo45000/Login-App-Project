package model;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * A class to store information about accounts in the FileNtro app.
 *
 * @author Thinh Le
 * @author Riley Bennett
 * @version 0.1
 */
public class Account implements Jsonable {
  
  /** The name associated with the account. */
	String name;
	
  /** The email associated with the account. */
	String email;

	/**
	 * Constructor that creates an account with the given name and email.
	 */
	public Account(String name, String email) {
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

	@Override
	public String toJson() {
		final StringWriter writable = new StringWriter();
		try {
			this.toJson(writable);
		} catch (final IOException e) {
		}
		return writable.toString();
	}

	@Override
	public void toJson(Writer writer) throws IOException {
		final JsonObject json = new JsonObject();
		json.put("name", this.name);
		json.put("email", this.email);


		json.toJson(writer);
	}
}



