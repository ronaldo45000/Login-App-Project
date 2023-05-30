package controller;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import model.Document;
import model.Project;
import repository.ProjectRepository;

/**
 * A controller for the list of projects a user has.
 * @author Riley Bennett
 * @author TIn Phu
 * @version 0.3
 */
public class ProjectController {

    /**
     * Project repository, which has access to the projects in ProjectList.json.
     */
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
     * Gets the list of projects.
     * @author Hassan Abbas
     * @param theUID The ID of the user.
     * @return Hashmap of project ID/project object pairs.
     */
    public static HashMap<String, Project> getProjectsByUserID(final String theUID) {
        HashMap<String, Project> listOfProjects = new HashMap<String, Project>();
        projectRepository.getListOfProject().forEach((k,e)->{
            if(e.getUserID().equals(theUID)) {
                listOfProjects.put(k, e);
            }
        });
        return listOfProjects;
    }
    
    /**
     * Adds a project to the database.
     * @author Hassan Abbas
     * @param theProject The project to be added.
     */
    public static void addProject(final Project theProject) {
        projectRepository.addProject(theProject);
    }
    
    /**
     * Deletes a project from the database.
     * @author Hassan Abbas
     * @param theProject The project to be deleted.
     */
    public static void deleteProject(final Project theProject) {
        projectRepository.deleteProject(theProject);
    }
    
    /**
     * Finds a project by it's ID.
     * @author Hassan Abbas
     * @param theID The ID of the project.
     * @return The associated project.
     */
    public static Project findProjectByID(final String theID) {
        return projectRepository.findProjectbyId(theID);
    }

    /**
     * Deletes a project by it's ID.
     * @author Bairu Li
     * @param theID The ID of the project to be deleted.
     */
    public static void deleteProjectByID(final String theID) throws IOException {
        DocumentController.getDocsByProjectID(theID).forEach((documentId, document) -> {
            DocumentController.deleteADocument(document);
        });

//        System.out.println(DocumentController.getDocsByProjectID(theID).size());
        projectRepository.deleteProject(theID);
        String currentPath = System.getProperty("user.dir");

        File file = new File(currentPath + "\\" +  theID);

        Files.delete(file.toPath());

    }

    /**
     * Updates the total cost of a project found by it's ID.
     * @author Bairu Li
     * @param theID The ID of the project to be updated.
     * @return The new total cost.
     */
    public static BigDecimal updateTotalCostByID(final String theID) {
        BigDecimal totaLCost = new BigDecimal(0);

        HashMap<String, Document> loopDoc =  DocumentController.getDocsByProjectID(theID);

        for(Map.Entry<String, Document> set : loopDoc.entrySet()){
            totaLCost =totaLCost.add(set.getValue().getTotalCost());
        }

        projectRepository.findProjectbyId(theID).setTotalCost(totaLCost);
        projectRepository.exportData();
        return totaLCost;
    }

    /**
     * Sets a project's total budget by it's ID.
     * @param theID The ID of the project to be updated.
     * @param theTotalBudget The new budget of the project.
     */
    public static void setTotalBudgetByID(final String theID, final BigDecimal theTotalBudget){
        projectRepository.findProjectbyId(theID).setBudget(theTotalBudget);
        projectRepository.exportData();
    }
}
