package repository;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import model.Account;
import model.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tin Phu
 * @version 0.1
 * Keep in mind that we use Hashmap<String, User> to hold data. the key is user's name.
 */
public class UserRepository {
    public static void main(String[] args) {
        UserRepository test  = new UserRepository();
        User u = new User("Pppphu","phuuu@gmail.com");
        test.addUser(u );
        test.exportData();

    }
    private final String FILEPATH="UserProfile.json";

    private HashMap<String, User> userProfiles = new HashMap<String, User>();

    public UserRepository(){
        importData();
    }


    public HashMap<String, User> getUserProfiles(){
        return this.userProfiles;
    }

    public void importData(){
        try (FileReader fileReader = new FileReader(FILEPATH)) {
            JsonArray objects = Jsoner.deserializeMany(fileReader);
            JsonObject o = (JsonObject) objects.get(0);
            for (Map.Entry<String, Object> entry : o.entrySet()) {

                JsonObject u = (JsonObject) entry.getValue();
                if(u.get("email") != null ){
                    this.userProfiles.put(entry.getKey(), new User(entry.getKey(),u.get("email").toString()));
                }


            }

        System.out.println("Import Data from UserProfile.json");
        System.out.println(this.userProfiles.toString());


        } catch (IOException | JsonException e){
            throw new RuntimeException(e);
        }
    }

    public void exportData(){
        //        String json = Jsoner.serialize(this.appInfo);
//        json = Jsoner.prettyPrint(json);
//        System.out.println(json);   // print out JSON to check before writing to json file.
        try (FileWriter fileWriter = new FileWriter(this.FILEPATH)) {
            Jsoner.serialize(this.userProfiles, fileWriter);
            System.out.println("Export Data to UserProfile.json");
            System.out.println(this.userProfiles.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
    }
    }

    public User findUserByName(String theName){
        return this.userProfiles.get(theName);

    }

    public void deleteUserByName(String theName){
        this.userProfiles.remove(theName);
        this.exportData();
    }
    public void addUser(User theUser){
        this.userProfiles.put(theUser.getName(), theUser);
        this.exportData();
    }

}
