package model;

import java.util.ArrayList;

/**
 * A class to store info about the FileNtro app.
 *
 * @author Hassan Abbas
 * @author Riley Bennett
 * @version 2023-5-2
 */
public class AppInfo {
    
    /** The version number of the app. */
    private final double versionNumber = 0.1;
    
    /** The developers' team name. */
    private final String teamName = "Team Mauve";
    
    /** A list to store each developer. */
    private final ArrayList<Developer> teamMembers = new ArrayList<>(); 
    
    /** The current user of the app. */
    private User user;

    /**
     * Constructor to add developer names and set the user.
     *
     * @param user The user of the app
     */
    public AppInfo(final User user) {
        this.user = user;      
        teamMembers.add(new Developer("Hassan Abbas", "habbas91@uw.edu"));
        teamMembers.add(new Developer("Riley Bennett", "benn3230@uw.edu"));
        teamMembers.add(new Developer("Tin Phu", "phuhutin@uw.edu"));
        teamMembers.add(new Developer("Bairu Li", "bairul@uw.edu"));
        teamMembers.add(new Developer("Thinh Le", "lenguyenducthinh2003@gmail.com"));
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
    public ArrayList<Developer> getDevelopers() {
        ArrayList<Developer> copy = new ArrayList<>(teamMembers);
        return copy;
    }
}
