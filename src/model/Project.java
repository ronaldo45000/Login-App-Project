package model;


import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

/**
 * This Project is to handle the budget for the user.
 *
 * @author Thinh Le
 * @author Tin Phu
 * @version 0.2
 */
public class Project implements Jsonable {

//    public static void main(String[] args) {
//        Project p = new Project("Cafe Racer", BigDecimal.valueOf(999.99), BigDecimal.valueOf(2000.99), "a4e3f045-ab62-44cd-ac48-e7349a8db5b2" );
//    }
    /**
     * Id of the project.
     */
   private final String id;

    /**
     * Project's name.
     */
    private  String projectName;

    /**
     * Total cost of the project.
     */
    private BigDecimal totalCost = BigDecimal.valueOf(0);

    /**
     * Budget of this project.
     */
    private BigDecimal budget = BigDecimal.valueOf(0);

    /**
     * The id of the user this project belongs to.
     */
    private final String userID;

    /**
     * The date of this project.
     */
    private LocalDate date;

    /**
     * This constructor helps to calculate the cost of item for user, and let the uses to see the current
     * budget, and this is also helps them to track item by date and total cost.
     * @author Tin Phu
     * @param theProjectName The name of the project
     * @param totalCost The total cost of the project
     * @param budget The budget of the project
     * @param theUserID The id of the user this project belongs to
     *
     */
    public Project( String theProjectName, BigDecimal totalCost, BigDecimal budget, String theUserID){
        this.totalCost = totalCost;
        this.budget = budget;
        this.id =  UUID.randomUUID().toString();
        this.date = LocalDate.now();
        this.userID = theUserID;
        this.projectName = theProjectName;
        this.createProjectFolder();
    }

    /**
     * Constructor for data mapping.
     * @author Tin Phu
     * @param theId The id of the project
     * @param theProjectName The name of the project
     * @param totalCost The total cost of the project
     * @param budget The budget of the project
     * @param theUserID The id of the user this project belongs to
     */
    public Project( String theId, String theProjectName, BigDecimal totalCost, BigDecimal budget, String theUserID, LocalDate theDate){
        this.totalCost = totalCost;
        this.budget = budget;
        this.date = theDate;
        this.userID = theUserID;
        this.projectName = theProjectName;
        this.id = theId;
    }

    /**
     * Getter for id.
     * @author Thinh Le
     * @return The id of this project
     */
    public String getId(){
        return id;
    }

    /**
     * Getter for date.
     * @author Thinh Le
     * @return The date of this project
     */
    public LocalDate  getDate(){
        return date;
    }

    /**
     * Getter for total cost.
     * @author Thinh Le
     * @return The total cost of this project
     */
    public BigDecimal getTotalCost(){
        return totalCost;
    }

    /**
     * Getter for budget.
     * @author Thinh Le
     * @return The budget of this project
     */
    public BigDecimal getBudget(){
        return budget;
    }

    /**
     * Getter for remaining available cost.
     * @author Thinh Le
     * @return The balance left on this project
     */
    public BigDecimal getBalance(){
        return budget.subtract(totalCost);
    }

    /**
     * This is the setter for budget.
     * @author Thinh Le
     * @param budget The new budget of this project
     */
    public void setBudget(BigDecimal budget){
        this.budget = budget;
    }

    /**
     * This is the setter for total cost.
     * @author Thinh Le
     * @param totalCost The new total cost of this project
     */
    public void setTotalCost(BigDecimal totalCost){
        this.totalCost = totalCost;
    }

    /**
     * Getter for the id of the user who owns this project.
     * @author Tin Phu
     * @return The id of the user
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * Converts this project to JSON formatted stream.
     * @author Tin Phu
     * @return String JSON formatted stream of this document
     */
    @Override
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException e) {
            throw new RuntimeException();
        }
        return writable.toString();
    }

    /**
     * Converts this project to JSON formatted stream.
     * @author Tin Phu
     * @param writer Writer to write to stream with
     */
    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject json = new JsonObject();
        json.put("id", this.id);
        json.put("totalCost", this.totalCost.toString());
        json.put("budget", this.budget.toString());
        json.put("userID", this.userID);
        json.put("date",  this.date.toString());
        json.put("projectName", this.projectName);
        json.toJson(writer);

    }

    /**
     * Returns a String representation of this project.
     * @author Tin Phu
     * @return String representation of this project
     */
    @Override
    public String toString(){
         return "id:" + this.id + "|" + "projectName:" + this.projectName + "|userID:" + this.userID + 
         "| totalCost" + this.totalCost + "| budget:" + this.budget + "\n";
    }

    /**
     * Create A Folder for a project.
     * @Author Tin Phu
     */
    private void createProjectFolder(){
        String currentPath = System.getProperty("user.dir");
        File theDir = new File(currentPath + "\\"+ this.id);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
    }


}
