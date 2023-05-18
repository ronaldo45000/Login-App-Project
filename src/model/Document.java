package model;

import java.io.File;

/**
 * This class has information about document such as document name, file path, document description, and id.
 *
 * @author Thinh Le
 * @version 0.1
 */
public class Document {
    /**
     * This is counter for id.
     */
    private static int idCounter=0;
    /**
     * This is name of document.
     */
    private String documentName;

    /**
     * This is the file path to open.
     */
    private File filepath;
/**
This is description about document.
 */
private String documentDescription;

/**
This is the id for document
 */
private int id;

    /**
     * This constructor creates Document witt id, document name, document description and file path.
     * @param id
     * @param documentName
     * @param documentDescription
     * @param filepath
     */
    public Document(int id,String documentName, String documentDescription, File filepath){
    this.documentName = documentName;
    this.documentDescription = documentDescription;
    this.id = idCounter;
    this.filepath = filepath;
    idCounter+=1;
}

    /**
     * This is the getter for document id.
     *
     * @return
     */
    public int id(){
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
    public File getFilepath(){
        return filepath;
    }

    /**
     * this is the getter for document description.
     * @return
     */
    public String getDocumentDescription(){
        return documentDescription;
    }

    /**
     * this is the setter for document id.
     * @param id
     */
    public void setId(int id){
        this.id = id;
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
    public void setFilepath(File filepath){
        this.filepath = filepath;
    }

    /**
     * This is the setter for document description
     * @param documentDescription
     */
    public void setDocumentDescription(String documentDescription){
        this.documentDescription = documentDescription;
    }
}
