package controller;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import model.Document;
import model.Project;
import repository.ProjectRepository;

import java.util.HashMap;
import java.util.Map;

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
    public static HashMap<String, Project> getProjectsByUserID(String theUID) {
        HashMap<String, Project> listOfProjects = new HashMap<String, Project>();
        projectRepository.getListOfProject().forEach((k,e)->{
            if(e.getUserID().equals(theUID)) {
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

    /**
     * Deletes a project by Id.
     * @author Bairu Li
     * @param theID
     */
    public static void deleteProjectByID(String theID) throws IOException {
        DocumentController.getDocsByProjectID(theID).forEach((documentId, document) -> {
            DocumentController.deleteADocument(document);
        });
        System.out.println(DocumentController.getDocsByProjectID(theID).size());
        projectRepository.deleteProject(theID);
        String currentPath = System.getProperty("user.dir");

        File file = new File(currentPath + "\\" +  theID);

        Files.delete(file.toPath());

    }

    /**
     * Updates the total cost of a project by Id.
     *
     * @author Bairu Li
     * @param theID
     */
    public static BigDecimal updateTotalCostByID(String theID) {
        BigDecimal  totaLCost = new BigDecimal(0);
        Double      tempTotal = 0.0;

        HashMap<String, Document> loopDoc =  DocumentController.getDocsByProjectID(theID);

        for(Map.Entry<String, Document> set : loopDoc.entrySet()){
            totaLCost =totaLCost.add(set.getValue().getTotalCost());
        }

        projectRepository.findProjectbyId(theID).setTotalCost(totaLCost);
        projectRepository.exportData();
        return totaLCost;
    }

    public static void setTotalBudgetByID(String theID, BigDecimal theTotalBudget){
        projectRepository.findProjectbyId(theID).setBudget(theTotalBudget);
        projectRepository.exportData();
    }


}
