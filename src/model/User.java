package model;

/**
 * This class helps to see the username, email and name of the users.
 *
 * @author Thinh Le
 * @version 0.1
 */
public class User {

    /**
     * This is the username
     */
    private String username;
    /**
     * This is the user email.
     */

private String userEmail;
    /**
     * This is the name of users.
     */
    private String name;


    /**
     * This constructor provides user with their username, email and name.
     * @param username
     * @param userEmail
     * @param name
     * @param password
     */
    public User(String username, String userEmail, String name, String password){
    this.username = username;
    this.userEmail = userEmail;
    this.name = name;

}

    /**
     * This is the getter of username.
     * @return
     */
    public String getUsername(){
    return  username;
}

    /**
     * Getter for user email.
     * @return
     */
    public String getUserEmail(){
    return userEmail;
}

    /**
     * Getter for name of users.
     * @return
     */
    public String getName(){
    return name;
}

    /**
     * Setter for username.
     * @param username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Setter for user email.
     * @param userEmail
     */

    public void getUserEmail( String userEmail){
        this.userEmail = userEmail;
    }

    /**
     * Setter for name of users.
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }



}

