package repository;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import model.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tin Phu
 * @author Riley Bennett
 * @version 0.2
 * Keep in mind that we use Hashmap<String, User> to hold data. the key is user's name.
 */
public class UserRepository {



    /**
     * File name
     */
    private final String FILEPATH="UserProfile.json";
    /**
     * Hashmap to be mapped with json data.
     */
    private final HashMap<String, User> listOfuserProfile = new HashMap<String, User>();

    /**
     * no-arg constructor
     * which automatically importData()
     */
    public UserRepository(){
//        importData();
    }

    /**
     * Return the list of  userprofile.
     *
     * @return the reference of this.userProfiles.
     */
    public HashMap<String, User> getListOfuserProfile(){
        return this.listOfuserProfile;
    }

    /**
     * @Author TinPhu
     *  import data from Json and map to this.userProfiles hashmap.
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
     * @Author Tin Phu
     * export this.userProfiles hashmap to UserProfile.json
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
     * findUserByName.
     *
     * @Author Riley Bennett
     * @param theName
     * @return the reference of that User in hashmap !!!.
     */
    public User findUserByName(String theName){
        Iterator<User> itr = this.listOfuserProfile.values().iterator();
        while (itr.hasNext()) {
            User tempUser = itr.next();
            if (tempUser.getName().equals(theName)) {
                return tempUser;
            }
        }
        return null;
    }

    /**
     * @Author Tin Phu
     * @author Riley Bennett
     * Delete a User from the hashmap and then exportData().
     * @param theName
     */
    public boolean deleteUserByName(String theName){
        Iterator<User> itr = this.listOfuserProfile.values().iterator();
        while (itr.hasNext()) {
            User tempUser = itr.next();
            if (tempUser.getName().equals(theName)) {
                listOfuserProfile.remove(tempUser.getId());
                return true;
            }
        }
        return false;
    }

    /**
     * @author Tin Phu
     * @author Riley Bennett
     * Add the User to the hasmap and then exportData()
     * @param theUser
     */
    public User addUser(User theUser){
        this.listOfuserProfile.put(theUser.getId(), theUser);
        this.exportData();
        return theUser;
    }

}
