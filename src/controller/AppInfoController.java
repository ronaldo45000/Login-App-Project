package controller;

import model.Account;
import model.AppInfo;
import model.User;
import repository.AboutAppRepository;


/**
 * AppInfoController will help connect GUIs and Data Repository.
 * Message to Team Members:
 * Please call this class if you want to make any change to database.
 * GUIs are not allowed to directly Data Repository, because Data Repository potentially throws Exception
 * ,and we may want to handle them here.
 * this class's methods are STATIC
 * @author Tin Phu
 * @version 0.1
 *
 */
public class AppInfoController {
    /**
     * Final Static aboutAppRepository which has access to data in Json file.
     */
    private final static AboutAppRepository aboutAppRepository = new AboutAppRepository();

    /**
     * get App Information
     * @return the Reference of the original data. NOT CLONE.
     * The benefit of return reference: any change to the original data will automatically
     * update to the GUI
     * ForExample: In AboutScreen Class:
     *
     * Line 34: info = AppInfoController.getAppInfo();
     *      35: AppInfoController.addNewDeveloper(new Account("Dummy", "Dummy@gmail.com"));
     * adding new Developer to the original data, and info (bz info points to og data's address)
     * so the GUI will automatically update.
     * @Author Tin Phu
     */
    public static AppInfo getAppInfo() {

        return aboutAppRepository.getAppInfo();
    }

    /**
     * Set User to aboutAppRepository
     * @Author Tin Phu
     */
    public static void setUser(User theUser){

        aboutAppRepository.getAppInfo().setUser(theUser);
        aboutAppRepository.exportData();
    }

    /**
     * Adding new developer and export current state to json file.
     * @Author Tin Phu
     */
    public static void addNewDeveloper(Account theDeveloper){
        aboutAppRepository.getAppInfo().addNewDeveloper(theDeveloper);
        aboutAppRepository.exportData();
    }

    /**
     * remove Devs
     * this is created for testing of exportData()
     * @Author Tin Phu
     */
    public static void removeDeveloperByName(String theName){
       aboutAppRepository.getAppInfo().removeDevByName("Phu");
       aboutAppRepository.exportData();

    }

    /**
     * @Author Tin Phu
     */
    public  static void setVersion(double theVer){
        aboutAppRepository.getAppInfo().setVersionNumber(theVer);
        aboutAppRepository.exportData();
    }

    /**
     * @Author Tin Phu
     * @return User
     */
    public static User getCurrentUser(){
        return aboutAppRepository.getAppInfo().getUser();
    }

    /**
     * Exports app info to a JSON file.
     *  
     * @author Riley Bennett
     */
    public static void exportData() {
        aboutAppRepository.exportData();
    }

    /**
     * Imports app info from a JSON file to the program.
     *  
     * @author Riley Bennett
     */
    public static void importData() {
        aboutAppRepository.importData();
    }
}
