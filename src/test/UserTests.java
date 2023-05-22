package test;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import controller.UserController;
import java.io.*;
import java.util.Map;
import model.User;
import repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the UserController and UserRepository classes.
 * @author Riley Bennett
 * @version 0.2
 */
public class UserTests {

    User user = new User("John Doe", "jdoe@gmail.com");

    /**
     * Sets up test data.
     * @author Riley Bennett
     */
    @BeforeEach
    public void setUp() {
        UserController.addUser(user);   // Calls exportData()
    }
    /**
     * Test method for model.User
     * @author Riley Bennett
     */
    @Test
    public void testEmail() {
        assertEquals("jdoe@gmail.com", user.getEmail());
    }

    /**
     * Test method for model.User
     * @author Riley Bennett
     */
    @Test
    public void testName() {
        assertEquals("John Doe", user.getName());
    }

    /**
     * Test method for controller.UserController and repository.UserRepository
     * @author Riley Bennett
     */
    @Test
    public void testAddUserNotNull() {
        assertNotNull(UserController.findUser(user));
    }

    /**
     * Test method for controller.UserController and repository.UserRepository
     * @author Riley Bennett
     */
    @Test
    public void testAddUserCorrect() {
        assertEquals("John Doe", UserController.findUser(user).getName());
    }

    /**
     * Test method for controller.UserController and repository.UserRepository
     * @author Riley Bennett
     */
    @Test
    public void testUserExists() {
        assertTrue(UserController.userExists(user));
    }

    /**
     * Test method for controller.UserController and repository.UserRepository
     * @author Riley Bennett
     */
     @Test
     public void testDeleteUser() {
        UserController.deleteUser(user);
        assertNull(UserController.findUser(user));
     }

     /**
      * Test method for controller.UserController and repository.UserRepository
      * @author Riley Bennett
      */
      @Test
      public void testExport() {

        // ExportData() called by addUser in setUp()
        
        User testUser = new User("empty", "empty");

        try (FileReader fileReader = new FileReader("UserProfile.json")) {
            JsonArray objects = Jsoner.deserializeMany(fileReader);
            JsonObject o = (JsonObject) objects.get(0);
            String name = "notFound";
            String email = "notFound";
            for (Map.Entry<String, Object> entry : o.entrySet()) {
                
                JsonObject currentUser  = (JsonObject) entry.getValue();

                if(currentUser.get("email").equals("jdoe@gmail.com")){
                    email = "jdoe@gmail.com";
                }

                if(currentUser.get("name").equals("John Doe")){
                    name = "John Doe";
                }

                testUser = new User(name, email);
            }
        } catch (IOException | JsonException e){
            throw new RuntimeException(e);
        }

        assertEquals("John Doe", testUser.getName());
      }

      /**
       * Test method for controller.UserController and repository.UserRepository
       * @author Riley Bennett
       */
      @Test
      public void testImport() {
        UserRepository tempRepo = new UserRepository();
        tempRepo.addUser(new User("Jane Doe", "jadoe@gmail.com"));

        try (FileWriter fileWriter = new FileWriter("UserProfile.json")) {
            Jsoner.serialize(tempRepo.getListOfuserProfile(), fileWriter);
        } catch (IOException e) {}

        UserController.importData();
        assertTrue(UserController.userExists("Jane Doe", "jadoe@gmail.com"));
      }

    /**
     * Deletes test data.
     * @author Riley Bennett
     */
    @AfterAll
    public static void deleteTestUsers() {
        UserController.deleteUser("John Doe", "jdoe@gmail.com");
        UserController.deleteUser("Jane Doe", "jadoe@gmail.com");
    }
}
