package controller;

import com.github.cliftonlabs.json_simple.JsonException;
import model.Account;
import model.AppInfo;
import model.User;
import repository.AboutAppRepository;

/**
 * @Author Tin Phu
 * @version 0.1
 *
 * Message to my teammates:
 * Why Do we need controller ? Why can't GUIs interact directly with database (Entity_Repositoty)?
 * Those are the questions I had when I first started learning MVC Architectural.
 * 1, Sometimes data need to be cooked and modified before saving to the database.
 * 2, Exception Handling !!! We are working with database, so there is big chance an exception will be thrown
 * and Controller is the best place to catch and handle it, rather than throw the exception to the GUIs.
 *
 *
 */
public class AppInfoController {

    private final static AboutAppRepository aboutAppRepository = new AboutAppRepository();


    public static AppInfo getAppInfo() {

        return aboutAppRepository.getAppInfo();
    }



    public static void setUser(User theUser){
        aboutAppRepository.getAppInfo().setUser(theUser);

    }


    public static void addNewDeveloper(Account theDeveloper){

    }

}
