package model;

import java.util.ArrayList;
public class AppInfo {
    private final double versionNumber = 0.1;
    private final String teamName = "Team Mauve";
    private final ArrayList<Developer> teamMembers = new ArrayList<>(); 
    private User user;

    public AppInfo(final User user) {
        this.user = user;      
        teamMembers.add(new Developer("Hassan Abbas", "habbas91@uw.edu"));
        teamMembers.add(new Developer("Riley Bennett", "benn3230@uw.edu"));
        teamMembers.add(new Developer("Tin Phu", "phuhutin@uw.edu"));
        teamMembers.add(new Developer("Bairu Li", "bairul@uw.edu"));
        teamMembers.add(new Developer("Thinh Le", "lenguyenducthinh2003@gmail.com"));
    }
    
    public User getUser() {
        return user;
    }
    
    public String getTeamName() {
        return teamName;
    }
    
    public double getVersion() {
        return versionNumber; 
    }
    
    public ArrayList<Developer> getDevelopers() {
        ArrayList<Developer> copy = new ArrayList<>(teamMembers);
        return copy;
    }
}
