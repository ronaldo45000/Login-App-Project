package model;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import controller.DocumentController;
import repository.DocumentRepository;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

/**
 * This class has information about document such as document name, file path, document description, and id.
 *
 * @author Thinh Le
 * @author Tin Phu
 * @version 0.2
 */
public class Document implements Jsonable {
    public static void main(String[] args) {
//        try {
//            Document d3 = (new Document("Doc3", "Description2", "35089418-d50b-4fc8-88ea-bb82c1ea5633", "UseID", BigDecimal.valueOf(222), "D:\\360\\TestDocs\\TestDoc3.pdf"));
//            Document d2 = (new Document("Doc2", "Description2", "35089418-d50b-4fc8-88ea-bb82c1ea5633", "UseID", BigDecimal.valueOf(222), "D:\\360\\TestDocs\\TestDoc2.docx"));
//            Document d1 = (new Document("Doc1", "Description2", "35089418-d50b-4fc8-88ea-bb82c1ea5633", "UseID", BigDecimal.valueOf(222), "D:\\360\\TestDocs\\TestDoc1.png"));
//            DocumentRepository rep = new DocumentRepository();
//            rep.addDocument(d1);
//            rep.addDocument(d2);
//            rep.addDocument(d3);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//

        DocumentRepository rep = new DocumentRepository();
        HashMap<String, Document> l = DocumentController.getDocsByProjectID("35089418-d50b-4fc8-88ea-bb82c1ea5633");
        l.get("92ac0f56-258b-4ac0-aa40-b939744c3a29").setTotalCost(BigDecimal.valueOf(11111));
        rep.addDocument(l.get("92ac0f56-258b-4ac0-aa40-b939744c3a29"));
        System.out.println(rep.getListOfDocument().get("92ac0f56-258b-4ac0-aa40-b939744c3a29").getTotalCost());
        System.out.println(l.get("92ac0f56-258b-4ac0-aa40-b939744c3a29").getTotalCost());

    }

    /**
     * This is name of document.
     */
    private String documentName;

    /**
     * This is the file path to open.
     */
    private String filePath ="not yet";
    /**
        This is description about document.
     */
    private String documentDescription;
    /**
     * Total cost of anything that comes with this documents.
     */
    private BigDecimal totalCost;
    /**
     * this is the projectID which this document belongs to.
     */
    private final String projectID;

    /**
     * this is the User Id which this document belongs to.
     */
    private final String userID;
    /**
        This is the id for document
     */
    private final String id;

    private final LocalDate date;

    /**
     * This constructor creates Document with id, document name, document description.
     * @author Thinh Le
     * @author Tin Phu
     * @param documentName Name of the document
     * @param documentDescription Description associated with the document
     * @param theProjectId The id of the project this document is in
     * @param theUserID The id of the user this document belongs to
     * @param theTotalCost The cost associated with this document 
     */
    public Document( String documentName, String documentDescription, String theProjectId, String theUserID, BigDecimal theTotalCost){
    this.documentName = documentName;
    this.documentDescription = documentDescription;
    this.id =   UUID.randomUUID().toString();
    this.projectID = theProjectId;
    this.userID = theUserID;
    this.date = LocalDate.now();
    this.totalCost = theTotalCost;
    this.filePath = "\\" + theProjectId + this.id;
}
    /**
     * This constructor creates Document with id, document name, document description and
     * "file path"
     * @author Thinh Le
     * @author Tin Phu
     * @param documentName Name of the document
     * @param documentDescription Description associated with the document
     * @param theProjectId The id of the project this document is in
     * @param theUserID The id of the user this document belongs to
     * @param theTotalCost The cost associated with this document
     */
    public Document( String documentName, String documentDescription, String theProjectId, String theUserID, BigDecimal theTotalCost, String srcFileString) throws IOException {
        this.documentName = documentName;
        this.documentDescription = documentDescription;
        this.id =   UUID.randomUUID().toString();
        this.projectID = theProjectId;
        this.userID = theUserID;
        this.date = LocalDate.now();
        this.totalCost = theTotalCost;
        this.filePath = this.copyFileToAppRep(srcFileString);
    }

    /**
     * A constructor for data mapping.
     * @author Tin Phu
     * @param documentName Name of the document
     * @param documentDescription Description associated with the document
     * @param theProjectId The id of the project this document is in
     * @param theUserID The id of the user this document belongs to
     * @param theTotalCost The cost associated with this document 
     * @param theID The id of this document
     * @param theDate The date associated with this document
     * @param thePath The file path of this document
     */
    public Document(String documentName, String documentDescription, String theProjectId, String theUserID, BigDecimal theTotalCost, String theID, LocalDate theDate, String thePath ){
        this.documentName = documentName;
        this.documentDescription = documentDescription;
        this.id =   theID;
        this.projectID = theProjectId;
        this.userID = theUserID;
        this.date = theDate;
        this.totalCost = theTotalCost;
        this.filePath = thePath;
    }

    /**
     * This is the getter for document id.
     * @author Thinh Le
     * @return The id of the document
     */
    public String id(){
        return id;
    }

    /**
     * This is getter for document name.
     * @author Thinh Le
     * @return The name of the document
     */
    public String getDocumentName(){
        return documentName;
    }

    // /**
    //  * This is the getter for file path.
    //  * @author Tin Phu
    //  * @return The file path of the document
    //  */
//    public String getFilepath(){
//        return filepath;
//    }

    /**
     * This is the getter for document description.
     * @author Thinh Le
     * @return This document's description
     */
    public String getDocumentDescription(){
        return documentDescription;
    }

    /**
     * This is the setter for document name.
     * @author Thinh Le
     * @param documentName The new name of this document
     */
    public void setDocumentName(String documentName){
        this.documentName = documentName;
    }

    /**
     * This is the setter for file path.
     * @author Thinh Le
     * @param filepath The new file path of this document
     */
    public void setFilepath(String filepath){
        this.filePath = filepath;
    }

    /**
     * This is the setter for document description
     * @author Thinh Le
     * @param documentDescription The new description of this document
     */
    public void setDocumentDescription(String documentDescription){
        this.documentDescription = documentDescription;
    }

    /**
     * Returns the id of this document.
     * @author Tin Phu
     * @return The id of this document
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the cost associated with this document.
     * @author Tin Phu
     * @param totalCost The cost to associate this document with
     */
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Returns the id of the project this document is in.
     * @author Tin Phu
     * @return The id of the project
     */
    public String getProjectID() {
        return projectID;
    }

    /**
     * Returns the cost associated with this document.
     * @author Tin Phu
     * @return The cost associated with this document
     */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    /**
     * Returns the id of the user this document belongs to.
     * @author Tin Phu
     * @return The id of the user
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Converts this document to JSON formatted stream.
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
     * Converts this document to JSON formatted stream.
     * @author Tin Phu
     * @param writer Writer to write to stream with
     * @throws IOException
     */
    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject json = new JsonObject();
        json.put("id", this.id);
        json.put("totalCost", this.totalCost.toString());
        json.put("documentDescription", this.documentDescription);
        json.put("projectID", this.projectID);
        json.put("date",  this.date.toString());
        json.put("userID", this.userID);
        json.put("documentName", this.documentName);
        json.put("filePath",this.filePath);
        json.toJson(writer);
    }

    /**
     * Returns a String representation of this document.
     * @author Tin Phu
     * @return String representation of this document
     */
    @Override
    public String toString(){
        return "id: " + this.id + "|documentName:"+this.documentName + "|documentDescription:" + this.documentDescription +"|projectID:" + this.projectID
                + "|userID:"+ this.userID+"|totalCost:" + this.totalCost + "|filePath:" + this.filePath + "|date:" + this.date + "\n" ;
    }


//    public BigDecimal getTotalCost() {
//        return totalCost;
//    }
//
//    public String getUserID() {
//        return userID;
//    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Copy a file from user input to a dest location.
     * This Model could throw except straight to GUIs.
     * @Author Tin Phu
     * @param srcString
     * @throws IOException
     */
    private String copyFileToAppRep(String srcString) throws IOException {
        File src = new File(srcString);
        String fileType = "";
        for(int i = srcString.length()-1; i >= 0; i-- ){
            if(srcString.charAt(i) != '.'){
                fileType =  srcString.charAt(i) + fileType;
            } else {
                fileType = "." + fileType;
                break;
            }
        }


        String currentPath = System.getProperty("user.dir");

        String destString = "\\" + this.projectID + "\\" + this.id + fileType;


        File dest = new File(currentPath + destString);

        Files.copy(src.toPath(), dest.toPath());


        return  destString;
    }

    public void openDoc(){
        String currentPath = System.getProperty("user.dir");

        File file = new File(currentPath + this.filePath);
        Desktop desktop = Desktop.getDesktop();
        if(file.exists())         //checks file exists or not
        {
            try {
                desktop.open(file);              //opens the specified file
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
