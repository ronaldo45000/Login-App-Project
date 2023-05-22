package test;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import controller.AppInfoController;
import java.io.*;
import model.User;
import model.Account;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.AboutAppRepository;

/**
 * Tests for the AppInfoController and AboutAppRepository classes.
 * @author Riley Bennett
 * @version 0.2
 */
public class AppInfoTests {
    
    User user = new User("Test User", "Test@gmail.com");

    /**
     * Sets up test data.
     * @author Riley Bennett
     */
    @BeforeEach
    void setUp() {
        AppInfoController.getAppInfo().clearDevelopers();
        AppInfoController.setVersion(382.4);
        AppInfoController.addNewDeveloper(new Account("John Doe", "jdoe@yahoo.com"));
        AppInfoController.addNewDeveloper(new Account("Jane Doe", "jadoe@yahoo.com"));
        AppInfoController.setUser(user);    // Calls export() from repository
    }

    /**
     * Test method for controller.AppInfoController and model.AppInfo
     * @author Riley Bennett
     */
    @Test
    public void testCorrectUser() {
        assertEquals(user, AppInfoController.getCurrentUser());
    }

    /**
     * Test method for controller.AppInfoController and model.AppInfo
     * @author Riley Bennett
     */
    @Test
    public void testAddDeveloper() {
        assertEquals(2, AppInfoController.getDevelopers().size());
    }

    /**
     * Test method for controller.AppInfoController and model.AppInfo
     * @author Riley Bennett
     */
    @Test
    public void testRemoveDeveloper() {
        AppInfoController.removeDeveloperByName("John Doe");
        assertEquals("Jane Doe", AppInfoController.getDevelopers().get(0).getName());
    }

    /**
     * Test method for controller.AppInfoController and model.AppInfo
     * @author Riley Bennett
     */
    @Test
    public void testRemoveDeveloperDNE() {
        AppInfoController.removeDeveloperByName("Jeff Doe");
        assertEquals(2, AppInfoController.getDevelopers().size());
    }

    /**
     * Test method for controller.AppInfoController and model.AppInfo
     * @author Riley Bennett
     */
    @Test
    public void testCorrectVersion() {
        assertEquals(382.4, AppInfoController.getVersion());
    }

    /**
     * Test method for controller.AppInfoController and model.AppInfo
     * @author Riley Bennett
     */
    @Test
    public void testLogout() {
        AppInfoController.logout();
        assertNull(AppInfoController.getCurrentUser());
    }

    /**
     * Test method for controller.AppInfoController and model.AppInfo
     * @author Riley Bennett
     */
    @Test
    public void testExport() {
        AppInfoController.exportData();
        double version = 0.0;
        
        try (FileReader fileReader = new FileReader("AppInfo.json")) {
            JsonArray objects = Jsoner.deserializeMany(fileReader);
                JsonObject o = (JsonObject) objects.get(0);
                if(o.get("versionNumber") != null){
                    version = Double.parseDouble(o.get("versionNumber").toString());
                }
        } catch (IOException | JsonException e) {
            throw new RuntimeException(e);
        }

        assertEquals(382.4, version);
    }

    /**
     * Test method for controller.AppInfoController and model.AppInfo
     * @author Riley Bennett
     */
    @Test
    public void testImport() {
        AboutAppRepository tempRepo = new AboutAppRepository();
        tempRepo.getAppInfo().setUser(new User("Test 2 User", "test2@gmail.com"));

        try (FileWriter fileWriter = new FileWriter("AppInfo.json")) {
            Jsoner.serialize(tempRepo.getAppInfo(), fileWriter);
        } catch (IOException e) {}

        AppInfoController.importData();
        assertEquals("Test 2 User", AppInfoController.getCurrentUser().getName());
    }

    /**
     * Deletes test data.
     * @author Riley Bennett
     */
    @AfterAll
    public static void deleteTestUsers() {
        AppInfoController.removeDeveloperByName("John Doe");
        AppInfoController.removeDeveloperByName("Jane Doe");
        AppInfoController.setVersion(0.2);
        AppInfoController.setUser(null);
    }
}
