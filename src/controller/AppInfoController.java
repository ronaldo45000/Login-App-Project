package controller;

import model.Account;
import model.AppInfo;
import model.User;
import repository.AboutAppRepository;


/**
 * @author uthor Tin Phu
 * @version 0.1
 * AppInfoController will help connect GUIs and Data Repository.
 * Messagee to Team Members:
 * Please call this class if you want to make any change to database,
 * else call .clone() which is not defined yet.
 * this class's methods are STATIC
 * WARNING! PlEASE READ getAppInfo().
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

    /**
     * Adding new developer and export current state to json file.
     * @param theDeveloper
     */
    public static void addNewDeveloper(Account theDeveloper){
        aboutAppRepository.getAppInfo().addNewDeveloper(theDeveloper);
        aboutAppRepository.exportData();
    }

    /**
     * remove Devs
     * this is created for testing of exportData()
     * @param theName
     */
    public static void removeDeveloperByName(String theName){
       aboutAppRepository.getAppInfo().removeDevByName("Phu");
       aboutAppRepository.exportData();

    }

    public  static void setVersion(double theVer){
        aboutAppRepository.getAppInfo().setVersionNumber(theVer);
        aboutAppRepository.exportData();
    }

}
