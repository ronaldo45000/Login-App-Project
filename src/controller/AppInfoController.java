package controller;

import java.util.ArrayList;
import model.Account;
import model.AppInfo;
import model.User;
import repository.AboutAppRepository;


/**
 * AppInfoController will help connect GUIs and Data Repository.
 * @author Tin Phu
 * @author Riley Bennett
 * @version 0.3
 *
 */
public class AppInfoController {

    /**
     * Final Static aboutAppRepository which has access to data in Json file.
     */
    private final static AboutAppRepository aboutAppRepository = new AboutAppRepository();

    /**
     * Get the app info object associated with the aboutAppRepository.
     * @author Tin Phu
     * @return the Reference of the original data. NOT CLONE.
     */
    public static AppInfo getAppInfo() {

        return aboutAppRepository.getAppInfo();
    }

    /**
     * Sets the user in the aboutAppRepository.
     * @author Tin Phu
     */
    public static void setUser(final User theUser){

        aboutAppRepository.getAppInfo().setUser(theUser);
        aboutAppRepository.exportData();
    }
    
    /**
     * Changes user's name in aboutAppRepository.
     * @author Bairu Li
     */
    public static void changeUserName(final String theName) {
        aboutAppRepository.getAppInfo().getUser().setName(theName);
        aboutAppRepository.exportData();
    }
    
    /**
     * Changes user's email in aboutAppRepository.
     * @author Bairu Li
     */
    public static void changeUserEmail(final String theEmail) {
        aboutAppRepository.getAppInfo().getUser().setEmail(theEmail);
        aboutAppRepository.exportData();
    }

    /**
     * Adds new developer to the aboutAppRepository.
     * @author Tin Phu
     */
    public static void addNewDeveloper(final Account theDeveloper){
        aboutAppRepository.getAppInfo().addNewDeveloper(theDeveloper);
        aboutAppRepository.exportData();
    }

    /**
     * Removes a developer from the aboutAppRepository.
     * @author Tin Phu
     */
    public static void removeDeveloperByName(final String theName){
       aboutAppRepository.getAppInfo().removeDevByName(theName);
       aboutAppRepository.exportData();

    }

    /**
     * Returns the version of the app.
     * @author Riley Bennett
     * @return A double version number of the app
     */
    public static double getVersion() {
        return aboutAppRepository.getAppInfo().getVersion();
    }

    /**
     * Sets the version of the app.
     * @author Tin Phu
     * @param theVer The version to set to.
     */
    public static void setVersion(final double theVer){
        aboutAppRepository.getAppInfo().setVersionNumber(theVer);
        aboutAppRepository.exportData();
    }

    /**
     * Sets the current user of the app.
     * @author Tin Phu
     * @return The user that was set.
     */
    public static User getCurrentUser(){
        return aboutAppRepository.getAppInfo().getUser();
    }

    /**
     * Returns a list of the developers.
     * @author Riley Bennett
     * @return ArrayList of Account objects
     */
    public static ArrayList<Account> getDevelopers() {
        return aboutAppRepository.getAppInfo().getDevelopers();
    }

    /**
     * Exports app info to a JSON file.
     * @author Riley Bennett
     */
    public static void exportData() {
        aboutAppRepository.exportData();
    }

    /**
     * Imports app info from a JSON file to the program.
     * @author Riley Bennett
     */
    public static void importData() {
        aboutAppRepository.importData();
    }

    /**
     * Logs the user out of the program.
     * @author Tin Phu
     */
    public static void logout(){
        aboutAppRepository.getAppInfo().setUser(null);
        aboutAppRepository.exportData();

    }
}