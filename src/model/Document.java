package model;

import java.io.File;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * This class has information about document such as document name, file path, document description, and id.
 *
 * @author Thinh Le
 * @version 0.1
 */
public class Document {

    /**
     * This is name of document.
     */
    private String documentName;

    /**
     * This is the file path to open.
     */
    //private String filepath;
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
    private String projectID;

    /**
        This is the id for document
     */
    private String id;

    /**
     * This constructor creates Document witt id, document name, document description and file path.
     * @param documentName
     * @param documentDescription

     */
    public Document( String documentName, String documentDescription, String theProjectId){
    this.documentName = documentName;
    this.documentDescription = documentDescription;
    this.id =   UUID.randomUUID().toString();
    this.projectID = theProjectId;

}

    /**
     * This is the getter for document id.
     *
     * @return
     */
    public String id(){
    return id;
}

    /**
     * This is getter for document name.
     *
     * @return
     */
    public String getDocumentName(){
    return documentName;
}

    /**
     * This is the getter for file path.
     * @return
     */
//    public String getFilepath(){
//        return filepath;
//    }

    /**
     * this is the getter for document description.
     * @return
     */
    public String getDocumentDescription(){
        return documentDescription;
    }


    /**
     * This is the setter for document name.
     * @param documentName
     */
    public void setDocumentName(String documentName){
        this.documentName = documentName;
    }

    /**
     * This is the setter for file path.
     * @param filepath
     */
   // public String setFilepath(File filepath){
    //    this.filepath = filepath;
   // }

    /**
     * This is the setter for document description
     * @param documentDescription
     */
    public void setDocumentDescription(String documentDescription){
        this.documentDescription = documentDescription;
    }
    public String getId() {
        return id;
    }
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getProjectID() {
        return projectID;
    }


}
