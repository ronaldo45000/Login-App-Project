package repository;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import model.Project;
/**
 * A repository for a user's projects.
 * @author Tin Phu
 * @version 0.2
 */
public class ProjectRepository {
    /**
     * File name
     */
    private final String FILEPATH="ProjectList.json";

    /**
     * Hashmap<Srting, Project> to hold importData();
     * Keep in mind that the key id.
     */
    private final HashMap<String, Project> listOfProject = new HashMap<String, Project>();

    /**
     * no-arg constructor
     * which automatically importData()
     * @author Tin Phu
     */
    public ProjectRepository(){
        importData();
    }

    /**
     * Import data from ProjectList.json and map to hashMap.
     * @author Tin Phu
     */
    public void importData(){
        listOfProject.clear();
        try (FileReader fileReader = new FileReader(FILEPATH)) {
            JsonArray objects = Jsoner.deserializeMany(fileReader);
            JsonObject o = (JsonObject) objects.get(0);
            for (Map.Entry<String, Object> entry : o.entrySet()) {
                JsonObject currentProject = (JsonObject) entry.getValue();
                BigDecimal totalCost = BigDecimal.valueOf(0);
                if(currentProject.get("totalCost") != null){
                    totalCost = BigDecimal.valueOf(Double.valueOf(currentProject.get("totalCost").toString()));
                }

                BigDecimal budget = BigDecimal.valueOf(0);
                if(currentProject.get("budget") != null){
                    budget = BigDecimal.valueOf(Double.valueOf(currentProject.get("budget").toString()));
                }

                String ownerName = "notFound";
                if(currentProject.get("userID") != null){
                    ownerName = currentProject.get("userID").toString();
                }

                LocalDate date = null;
                if(currentProject.get("date") != null){
                    date = LocalDate.parse(currentProject.get("date").toString());
                }

                String projectName="notFound";
                if(currentProject.get("projectName") != null){
                    projectName = currentProject.get("projectName").toString();
                }

                String id = "notFound";
                if(currentProject.get("id") != null){
                    id = currentProject.get("id").toString();
                }

                this.listOfProject.put(entry.getKey(), new Project(id, projectName, totalCost, budget, ownerName, date));


            }

            System.out.println("Import Data from ProjectList.json");

        } catch (IOException | JsonException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Export the hashmap to ProjectList.json file.
     * @author Tin Phu
     */
    public void exportData(){
        try (FileWriter fileWriter = new FileWriter(this.FILEPATH)) {
            Jsoner.serialize(this.listOfProject, fileWriter);
            System.out.println("Export Data to ProjectList.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Return the reference of the hashmap.
     * @author Tin Phu
     * @return A hashmap of id/project pairs
     */
    public HashMap<String, Project> getListOfProject() {
        return listOfProject;
    }

    /**
     * Add a project to the list
     * @author Tin Phu
     * @param theProject The project to be added
     */
    public void addProject(Project theProject){
        this.listOfProject.put(theProject.getId(), theProject);
        this.exportData();
    }

    /**
     * Deletes project by id
     * @author Tin Phu
     * @param id The id of the project to be deleted
     */
    public void deleteProject(String id){
        this.listOfProject.remove(id);
        this.exportData();
    }

    /**
     * Deletes the specified project.
     * @author Tin Phu
     * @param theProject The project to be deleted
     */
    public void deleteProject(Project theProject){
        this.listOfProject.remove(theProject.getId());
        this.exportData();
    }

    /**
     * Returns the project associated with the given id.
     * @author Tin Phu
     * @param theId The id of the project to be searched for
     * @return The project associated with the id
     */
    public Project findProjectbyId(String theId){
        return this.listOfProject.get(theId);
    }
}
