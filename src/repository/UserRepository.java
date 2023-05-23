package repository;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import model.User;

/**
 * Repository for the users of the FileNtro app.
 * @author Tin Phu
 * @author Riley Bennett
 * @version 0.2
 */
public class UserRepository {

    /**
     * File path.
     */
    private final String FILEPATH="UserProfile.json";

    /**
     * Hashmap to be mapped with json data.
     */
    private final HashMap<String, User> listOfuserProfile = new HashMap<String, User>();

    /**
     * no-arg constructor
     * which automatically importData()
     * @author Tin Phu
     */
    public UserRepository(){
        importData();
    }

    /**
     * Returns the list of userprofile.
     * @author Tin Phu
     * @return The hashmap of id/user pairs
     */
    public HashMap<String, User> getListOfuserProfile(){
        return this.listOfuserProfile;
    }

    /**
     * Import data from UserProfile.json and map to this.userProfiles hashmap.
     * @author Tin Phu
     */
    public void importData(){
        try (FileReader fileReader = new FileReader(FILEPATH)) {
            JsonArray objects = Jsoner.deserializeMany(fileReader);
            JsonObject o = (JsonObject) objects.get(0);
            for (Map.Entry<String, Object> entry : o.entrySet()) {
                
                JsonObject currentUser  = (JsonObject) entry.getValue();


                String id = "notFound";
                if(currentUser.get("id") != null){
                    id = currentUser.get("id").toString();
                }
                String email = "notFound";
                if(currentUser.get("email") != null){
                    email = currentUser.get("email").toString();
                }

                String name = "notFound";
                if(currentUser.get("name") != null){
                    name = currentUser.get("name").toString();
                }

                this.listOfuserProfile.put(id, new User(name, email, id));
            }

        System.out.println("Import Data from UserProfile.json");
//        System.out.println(this.listOfuserProfile.toString());


        } catch (IOException | JsonException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Export this.userProfiles hashmap to UserProfile.json.
     * @author Tin Phu
     */
    public void exportData(){
        //        String json = Jsoner.serialize(this.appInfo);
//        json = Jsoner.prettyPrint(json);
//        System.out.println(json);   // print out JSON to check before writing to json file.
        try (FileWriter fileWriter = new FileWriter(this.FILEPATH)) {
            Jsoner.serialize(this.listOfuserProfile, fileWriter);
            System.out.println("Export Data to UserProfile.json");
//            System.out.println(this.listOfuserProfile.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds and returns the user with the specified name
     *
     * @author Riley Bennett
     * @param theName The name of the user to be searched for
     * @param theEmail The email of the user to be searched for
     * @return The user with the given name and email
     */
    public User findUserByName(String theName, String theEmail){
        Iterator<User> itr = this.listOfuserProfile.values().iterator();
        while (itr.hasNext()) {
            User tempUser = itr.next();
            System.out.println(tempUser.getName() + " " + tempUser.getEmail());
            if (tempUser.getName().equals(theName) && tempUser.getEmail().equals(theEmail)) {
                return tempUser;
            }
        }
        return null;
    }
    
    /**
     * Changes the user information in the databse with specified new information
     *
     * @author Bairu Li
     * @param theOldName The old name of the user to be changed
     * @param theOldEmail The old email of the user to be changed
     * @param theName The new name of the user to be changed
     * @param theEmail The new email of the user to be changed
     */
    public void changeUserInfo(String theOldName, String theOldEmail, 
    		                   String theName, String theEmail){
        User tempUser = findUserByName(theOldName, theOldEmail);
        
        listOfuserProfile.get(tempUser.getId()).setName(theName);
        listOfuserProfile.get(tempUser.getId()).setEmail(theEmail);
        this.exportData();
    }

    /**
     * Deletes the specified User from the hashmap and then exports data.
     * @author Tin Phu
     * @author Riley Bennett
     * @param theName The name of the user to be deleted
     * @param theEmail The email of the user to be deleted
     */
    public void deleteUser(final String theName, final String theEmail){
        Map<String, User> tempMap = (Map<String, User>) this.listOfuserProfile.clone();
        Iterator<User> itr = tempMap.values().iterator();
        while (itr.hasNext()) {
            User tempUser = itr.next();
            if (tempUser.getName().equals(theName) && tempUser.getEmail().equals(theEmail)) {
                listOfuserProfile.remove(tempUser.getId());
            }
        }
        this.exportData();
    }

    /**
     * Adds the given User to the hashmap and then exports data.
     * @author Tin Phu
     * @author Riley Bennett
     * @param theUser The user to be added
     */
    public User addUser(User theUser){
        this.listOfuserProfile.put(theUser.getId(), theUser);
        this.exportData();
        return theUser;
    }

}
