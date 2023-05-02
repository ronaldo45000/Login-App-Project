package model;

public class About {
    private String versionNumber;
    private String userName;
    private String teamName;
    private ArrayList<Developer> teamMembers;    
    
    public About(String userName, String teamName, ArrayList<Developer> teamMembers,
            String versionNumber) {
        this.userName = userName;
        this.teamName = teamName;
        this.teamMembers = new ArrayList<>(teamMembers);
        this.versionNumber = versionNumber;
    }
    
    public String getUsername() {
        return userName;
    }
    
    public String getTeamName() {
        return teamName;
    }
    
    public String getVersion() {
        return versionNumber; 
    }
    
    public List<Developer> getDevelopers() {
        ArrayList<Developer> copy = new ArrayList<>(teamMembers);
        return copy;
    }
    
    public void addDeveloper(Developer dev) {
        teamMembers.add(dev);
    }
}
