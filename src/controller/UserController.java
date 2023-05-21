package controller;
import repository.UserRepository;
import model.User;

/**
 * A controller for the user accounts between the UI and repository.
 * 
 * @author Riley Bennett
 * @version 0.2
 */
public class UserController {
    private final static UserRepository userRepository = new UserRepository();

    /**
     * Finds the specified user in the database.
     * @author Riley Bennett
     * @param theName
     * @param theEmail
     * @return The specified user, or null if not found.
     */
    public static User findUser(final String theName, final String theEmail) {
        User theUser = userRepository.findUserByName(theName);
        if (theUser == null) {
            return null;
        } else if (!theUser.getEmail().equalsIgnoreCase(theEmail)) {
            return null;
        } else {
            return theUser;
        }
    }

    /**
     * Finds the specified user in the database.
     * @author Riley Bennett
     * @param theUser
     * @return The specified user, or null if not found.
     */
    public static User findUser(final User theUser) {
        return findUser(theUser.getName(), theUser.getEmail());
    }

    /**
     * Adds the specified user to the database.
     * @author Riley Bennett
     * @param theName
     * @param theEmail
     * @return The user object that was added
     */
    public static User addUser(final String theName, final String theEmail) {
        return userRepository.addUser(new User(theName, theEmail));
    }

    /**
     * Adds the specified user to the database.
     * @author Riley Bennett
     * @param theUser
     * @return The user object that was added
     */
    public static User addUser(final User theUser) {
        return userRepository.addUser(theUser);
    }

    /**
     * Determines if the specified user exists in the database.
     * @author Riley Bennett
     * @param theName
     * @param theEmail
     * @return True if the user was found, false otherwise
     */
    public static boolean userExists(final String theName, final String theEmail) {
        return findUser(theName, theEmail) != null;
    }

    /**
     * Determines if the specified user exists in the database.
     * @author Riley Bennett
     * @param theUser
     * @return True if the user was found, false otherwise
     */
    public static boolean userExists(final User theUser) {
        return findUser(theUser) != null;
    }

    /**
     * Imports the UserProfile.json file into the repository.
     */
    public static void importData() {
        userRepository.importData();
    }

    /**
     * Exports the user repository to UserProfile.json.
     * @author Riley Bennett
     */
    public static void exportData() {
        userRepository.exportData();
    }
}
