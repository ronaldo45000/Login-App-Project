package controller;

import model.Document;
import repository.DocumentRepository;

import java.util.HashMap;

/**
 * A controller for the list of documents in every project.
 * @author Riley Bennett
 * @author Tin Phu.
 * @version 0.2
 */
public class DocumentController {
    private final static DocumentRepository documentRepository = new DocumentRepository();

    /**
     * Imports data from DocumentList.json
     * @author Riley Bennett
     */
    public static void importData() {
        documentRepository.importData();
    }

    /**
     * Exports document data to DocumentList.json
     * @author Riley Bennett
     */
    public static void exportData() {
        documentRepository.exportData();
    }

    /**
     * Get List of Doc of a specific projectID.
     * @author Tin Phu
     * @param thePID
     * @return a copy of HashMap of the list, NOT REFERENCE.
     */
    public static HashMap<String, Document> getDocsByProjectID(String thePID){
        HashMap<String, Document> listOfDoc = new HashMap<String, Document>();
        documentRepository.getListOfDocument().forEach((k,e)->{
            if(e.getProjectID().equals(thePID)){
                listOfDoc.put(k,e);
            }
        });

        return listOfDoc;
    }

    /**
     * Adding a document to json file.
     * @author Tin Phu
     * @param theDoc
     */
    public static void addDocument(Document theDoc){
        documentRepository.addDocument(theDoc);
    }

    /**
     * Deleting a Doc by ID.
     * @author Tin Phu
     * @param theDocID
     */
    public static void deleteADocumentByID(String theDocID){
        documentRepository.deleteDocument(theDocID);
    }


}
