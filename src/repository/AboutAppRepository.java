package repository;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import model.Account;
import model.AppInfo;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author  Tin Phu
 * @version 0.1
 * AboutAppRepository reads and writes to/from AppInfo.json file
 * com.github.cliftonlabs.json_simple library is used to work with Json.
 *
 */
public class AboutAppRepository {
    /**
     * Path to the AppInfo.json
     */
     private final String FILEPATH="AppInfo.json";
    /**
     * appInfo holds data.
     */
    private final AppInfo appInfo = new AppInfo();

    /**
     * No-arg constructor.
     * which will simple importData() and populate data to this.appInfo.
     */
    public AboutAppRepository()  {
        importData();
    }

    /**
     * Data is imported and mapped to this.appInfo
     */
    public void importData() {

        try (FileReader fileReader = new FileReader(FILEPATH)) {
            JsonArray objects = Jsoner.deserializeMany(fileReader);
            JsonObject o = (JsonObject) objects.get(0);
            appInfo.setVersionNumber(Double.parseDouble(o.get("versionNumber").toString()));
            JsonArray  arr = (JsonArray)o.get("teamMembers");

            //Mapping List of Devs information.
            for(int i = 0; i < arr.size(); i++){
                String currentMemberName = (String) ((JsonObject)arr.get(i)).get("name");
                String currentMemberEmail = (String) ((JsonObject)arr.get(i)).get("email");
                appInfo.addNewDeveloper(new Account(currentMemberName,currentMemberEmail ));
            }
        } catch (IOException | JsonException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * exporting or serialize this.appInfo to AppInfor.json.
     */
    public void exportData(){


        String json = Jsoner.serialize(this.appInfo);
//        json = Jsoner.prettyPrint(json);
//        System.out.println(json); print out JSON to check before writing to json file.
        try (FileWriter fileWriter = new FileWriter(this.FILEPATH)) {
            Jsoner.serialize(this.appInfo, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("test");


    }

    /**
     * getter.
     * @return
     */
    public AppInfo getAppInfo() {
        return appInfo;
    }
}
