package repository;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.Account;
import model.AppInfo;
import model.User;

/**
 * AboutAppRepository reads and writes to/from AppInfo.json file
 * com.github.cliftonlabs.json_simple library is used to work with Json.
 * @author Tin Phu
 * @version 0.3
 */
public class AboutAppRepository {

    /**
     * Path to the AppInfo.json.
     */
     private final String FILEPATH="AppInfo.json";
    /**
     * AppInfo object to hold app data.
     */
    private AppInfo appInfo = new AppInfo();

    /**
     * No-arg constructor, which will simply import data and populate data from AppInfo.json to the appInfo object.
     * @author Tin Phu
     */
    public AboutAppRepository()  {
        importData();
    }

    /**
     * Data is imported and then mapped to the appInfo object.
     * @author Tin Phu
     */
    public void importData() {

        appInfo = new AppInfo();

        try (FileReader fileReader = new FileReader(FILEPATH)) {
            JsonArray objects = Jsoner.deserializeMany(fileReader);
                JsonObject o = (JsonObject) objects.get(0);
                if(o.get("versionNumber") != null){
                    appInfo.setVersionNumber(Double.parseDouble(o.get("versionNumber").toString()));
                }


                //Mapping CurrentUser
                 if(o.get("user") != null){
                    JsonObject currentUserJObject =  (JsonObject) o.get("user");
                    appInfo.setUser(new User(currentUserJObject.get("name").toString(),currentUserJObject.get("email").toString(), currentUserJObject.get("id").toString()));
                }
                JsonArray  arr = (JsonArray)o.get("teamMembers");
                appInfo.clearDevelopers();
                 if(arr != null){
                     //Mapping List of Devs information.
                     for(int i = 0; i < arr.size(); i++){
                         String currentMemberName = (String) ((JsonObject)arr.get(i)).get("name");
                         String currentMemberEmail = (String) ((JsonObject)arr.get(i)).get("email");
                         appInfo.addNewDeveloper(new Account(currentMemberName,currentMemberEmail ));
                     }
                 }
        } catch (IOException | JsonException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Import Data from AppInfo.json");
    }

    /**
     * Exporting or serialize the appInfo object to AppInfo.json.
     * @author Tin Phu
     */
    public void exportData(){
        try (FileWriter fileWriter = new FileWriter(this.FILEPATH)) {
            Jsoner.serialize(this.appInfo, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Export Data to AppInfo.json");
    }

    /**
     * Returns the AppInfo object this repository is associated with.
     * @author Tin Phu
     * @return The AppInfo object this repository is associated with.
     */
    public AppInfo getAppInfo() {
        return appInfo;
    }
}
