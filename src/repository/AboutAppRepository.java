package repository;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import model.Account;
import model.AppInfo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AboutAppRepository {
     private final String FILEPATH="AppInfo.json";
     private final AppInfo appInfo = new AppInfo();

    public AboutAppRepository()  {
        importData();
    }


    public void importData() {

        try (FileReader fileReader = new FileReader(FILEPATH)) {



            JsonArray objects = Jsoner.deserializeMany(fileReader);


            JsonObject o = (JsonObject) objects.get(0);
            // System.out.println(o.get("teamMembers"));
            appInfo.setVersionNumber(Double.valueOf(o.get("versionNumber").toString()));
            JsonArray  arr = (JsonArray)o.get("teamMembers");


            for(int i = 0; i < arr.size(); i++){
                String currentMemberName = (String) ((JsonObject)arr.get(i)).get("name");
                String currentMemberEmail = (String) ((JsonObject)arr.get(i)).get("name");
                appInfo.addNewDeveloper(new Account(currentMemberName,currentMemberEmail ));
            }


            // System.out.println(((Account)arr.get(0)).getName());
            //System.out.println(((JsonObject)arr.get(0)).get("name"));


        } catch (IOException | JsonException e) {
            throw new RuntimeException(e);
        }
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }
}
