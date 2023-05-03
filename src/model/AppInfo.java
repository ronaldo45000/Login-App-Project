package model;

import java.util.ArrayList;

/**
 * A class to store info about the FileNtro app.
 *
 * @author Hassan Abbas
 * @author Riley Bennett
 * @version 0.1
 */
public class AppInfo {
    
    /** The version number of the app. */
    private final double versionNumber = 0.1;
    
    /** The developers' team name. */
    private final String teamName = "Team Mauve";
    
    /** A list to store each developer. */
    private final ArrayList<Account> teamMembers = new ArrayList<>(); 
    
    /** The current user of the app. */
    private User user;

    /**
     * Constructor to add developer names and set the user.
     *
     * @param user The user of the app
     */
    public AppInfo(final User user) {
        this.user = user;      
        teamMembers.add(new Account("Hassan Abbas", "habbas91@uw.edu"));
        teamMembers.add(new Account("Riley Bennett", "benn3230@uw.edu"));
        teamMembers.add(new Account("Thinh Le", "lenguyenducthinh2003@gmail.com"));
        teamMembers.add(new Account("Bairu Li", "bairul@uw.edu"));
        teamMembers.add(new Account("Tin Phu", "phuhutin@uw.edu"));
    }
    
    /**
     * A getter for the user object.
     *
     * @return The current user
     */
    public User getUser() {
        return user;
    }
    
    /**
     * A getter for the developer team name.
     *
     * @return The developer team name.
     */
    public String getTeamName() {
        return teamName;
    }
    
    /**
     * A getter for the app version number.
     *
     * @return The version of the app
     */
    public double getVersion() {
        return versionNumber; 
    }
    
    /**
     * A getter for the developers of the app.
     *
     * @return List of the developers
     */
    public ArrayList<Account> getDevelopers() {
        ArrayList<Account> copy = new ArrayList<>(teamMembers);
        return copy;
    }
}
