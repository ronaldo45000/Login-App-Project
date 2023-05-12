package controller;

import com.github.cliftonlabs.json_simple.JsonException;
import model.Account;
import model.AppInfo;
import model.User;
import repository.AboutAppRepository;

/**
 * @Author Tin Phu
 * @version 0.1
 * AppInfoController will help connect GUIs and Data Repository.
 */
public class AppInfoController {
    /**
     * Final Static aboutAppRepository which has access to data in Json file.
     */
    private final static AboutAppRepository aboutAppRepository = new AboutAppRepository();

    /**
     * get App Information
     * @return
     */
    public static AppInfo getAppInfo() {

        return aboutAppRepository.getAppInfo();
    }


    /**
     * Set User to aboutAppRepository
     * @param theUser
     */
    public static void setUser(User theUser){
        aboutAppRepository.getAppInfo().setUser(theUser);
    }


    public static void addNewDeveloper(Account theDeveloper){
        aboutAppRepository.getAppInfo().addNewDeveloper(theDeveloper);
    }

}
