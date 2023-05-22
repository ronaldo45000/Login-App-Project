package controller;

import java.util.Collection;
import model.User;
import repository.UserRepository;

/**
 * A controller for the user accounts between the UI and repository.
 * 
 * @author Riley Bennett
 * @version 0.2
 */
public class UserController {
    private final static UserRepository userRepository = new UserRepository();

    /**
     * Returns a list of users in the database.
     * @author Riley Bennett
     * @return Collection of User objects.
     */
    public static Collection<User> getUsers() {
        return userRepository.getListOfuserProfile().values();
    }

    /**
     * Finds the specified user in the database.
     * @author Riley Bennett
     * @param theName String name of the user
     * @param theEmail String email of the user
     * @return The specified user, or null if not found
     */
    public static User findUser(final String theName, final String theEmail) {
        return userRepository.findUserByName(theName, theEmail);
    }

    /**
     * Finds the specified user in the database.
     * @author Riley Bennett
     * @param theUser User object to be found
     * @return The specified user, or null if not found
     */
    public static User findUser(final User theUser) {
        return findUser(theUser.getName(), theUser.getEmail());
    }

    /**
     * Adds the specified user to the database.
     * @author Riley Bennett
     * @param theName String name of the user
     * @param theEmail String email of the user
     * @return The user object that was added
     */
    public static User addUser(final String theName, final String theEmail) {
        return userRepository.addUser(new User(theName, theEmail));
    }

    /**
     * Adds the specified user to the database.
     * @author Riley Bennett
     * @param theUser User object to be added
     * @return The user object that was added
     */
    public static User addUser(final User theUser) {
        return userRepository.addUser(theUser);
    }

    /**
     * Deletes the user with the specified name and email from the database.
     * @author Riley Bennett
     * @param theName String name of the user
     */
    public static void deleteUser(final String theName, final String theEmail) {
        userRepository.deleteUser(theName, theEmail);
    }

    /**
     * Deletes the specified user from the database.
     * @author Riley Bennett
     * @param theUser The User object to be deleted
     */
    public static void deleteUser(final User theUser) {
        userRepository.deleteUser(theUser.getName(), theUser.getEmail());
    }

    /**
     * Determines if the specified user exists in the database.
     * @author Riley Bennett
     * @param theName String name of the user
     * @param theEmail String email of the user
     * @return True if the user was found, false otherwise
     */
    public static boolean userExists(final String theName, final String theEmail) {
        return findUser(theName, theEmail) != null;
    }

    /**
     * Determines if the specified user exists in the database.
     * @author Riley Bennett
     * @param theUser User object to be searched for
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
