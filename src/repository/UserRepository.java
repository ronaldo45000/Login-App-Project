package repository;

import model.User;

import java.util.HashMap;
import java.util.List;

/**
 * @author Tin Phu
 * @version 0.1
 * Entity + Repository is the standard name in Spring Boot
 *
 *
 * Boring Fact !
 * In the real database, MySql for exmaple.
 * All the methods usually interact with database via @Query statement
 * For Example: findbyUsername() will run @Query("SELECT u FROM User u WHERE u.username = theInputUsername")
 */
public class UserRepository {
    /**
     * Tin Phu
     * HashMap to hold the import data.
     */
     static HashMap<String, User> userRepository;

    static void importData() throws Exception{
        //import data from the file to this userRepository;
    }

    static void exportData() throws Exception {
        //Tin
        //export userRepository to the userRepository file
        //this method should be called everything time userRepository changes
    }

    static User findbyUsername() throws Exception {

        return null;
    }

    static void delete(User theUser) throws Exception {


    }
    static List<User> getAll() throws Exception {

        return null;
    }
    static void save(User theUser){
        //add a new User to database
    }

}
