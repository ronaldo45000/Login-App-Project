package repository;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import model.Project;
import model.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tin Phu
 * @version 0.1
 */
public class ProjectRepository {


    public static void main(String[] args) {
        ProjectRepository rep = new ProjectRepository();
        rep.importData();
    }
    private final String FILEPATH="ProjectList.json";

    private final HashMap<String, Project> listOfProject = new HashMap<String, Project>();



    public void importData(){
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
                if(currentProject.get("ownerName") != null){
                    ownerName = currentProject.get("ownerName").toString();
                }

                LocalDate date;
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

                this.listOfProject.put(entry.getKey(), new Project(id, projectName, totalCost, budget, ownerName));


            }

            System.out.println("Import Data from UserProfile.json");
            System.out.println(this.listOfProject.toString());


        } catch (IOException | JsonException e){
            throw new RuntimeException(e);
        }
    }


    public void exportData(){
        try (FileWriter fileWriter = new FileWriter(this.FILEPATH)) {
            Jsoner.serialize(this.listOfProject, fileWriter);
            System.out.println("Export Data to UserProfile.json");
            System.out.println(this.listOfProject.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public String getFILEPATH() {
        return FILEPATH;
    }

    public HashMap<String, Project> getListOfProject() {
        return listOfProject;
    }

    public void addProject( Project theProject){
        this.listOfProject.put(theProject.getId(), theProject);
        this.exportData();
    }
    public void deleteProject(String id){
        this.listOfProject.remove(id);
        this.exportData();
    }

    public void deleteProject(Project theProject){
        this.listOfProject.remove(theProject.getId());
    }

    public Project findProjectbyId(String theId){
        return this.listOfProject.get(theId);
    }

}
