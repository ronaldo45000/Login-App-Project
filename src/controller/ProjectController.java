package controller;

import repository.ProjectRepository;

/**
 * A controller for the list of projects a user has.
 * @author Riley Bennett
 * @version 0.2
 */
public class ProjectController {
    private final static ProjectRepository projectRepository = new ProjectRepository();

    /**
     * Imports data from ProjectList.json
     * @author Riley Bennett
     */
    public static void importData() {
        projectRepository.importData();
    }

    /**
     * Exports project data to ProjectList.json
     * @author Riley Bennett
     */
    public static void exportData() {
        projectRepository.exportData();
    }
    
    /**
     * Get the list of projects
     * 
     * @author Hassan Abbas
     */
    public static HashMap<String, Project> getProjectsByID(String thePID) {
        HashMap<String, Project> listOfProjects = new HashMap<String, Project>();
        projectRepository.getListOfProject().forEach((k,e)->{
            if(e.getId().equals(thePID)) {
                listOfProjects.put(k, e);
            }
        });
        return listOfProjects;
    }
    
    /**
     * Adding project to json file
     * 
     * @author Hassan Abbas
     */
    public static void addProject(Project theProject) {
        projectRepository.addProject(theProject);
    }
    
    /**
     * Deleting project
     * 
     * @author Hassan Abbas
     */
    public static void deleteProject(Project theProject) {
        projectRepository.deleteProject(theProject);
    }
    
    /**
     * Find a project by ID
     * 
     * @author Hassan Abbas
     */
    public static Project findProjectByID(String theID) {
        return projectRepository.findProjectbyId(theID);
    }
}
