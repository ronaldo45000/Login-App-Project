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
import model.Document;

/**
 * A repository for the documents of the program.
 * @author Tin Phu
 * @version 0.3
 */
public class DocumentRepository {

    /**
     * File path of DocumentList.json.
     */
    private final String FILEPATH = "DocumentList.json";

    /**
     * Mapping of Document ID/Document object pairs.
     */
    private final HashMap<String, Document> listOfDocument = new HashMap<String, Document>();

    /**
     * No-arg constructor, which automatically imports data.
     */
    public DocumentRepository(){
        importData();
    }

    /**
     * Returns a hashmap of ids to documents.
     * @author Tin Phu
     * @return Hashmap of id/document pairs.
     */
    public HashMap<String, Document> getListOfDocument(){
        return this.listOfDocument;
    }

    /**
     * Import data from DocumentList.json and map to hashMap.
     * @author Tin Phu
     */
    public void importData() {
        listOfDocument.clear();
        try (FileReader fileReader = new FileReader(FILEPATH)) {
            JsonArray objects = Jsoner.deserializeMany(fileReader);
            JsonObject o = (JsonObject) objects.get(0);
            for (Map.Entry<String, Object> entry : o.entrySet()) {
                JsonObject currentDoc = (JsonObject) entry.getValue();
                BigDecimal totalCost = BigDecimal.valueOf(0);
                if(currentDoc.get("totalCost") != null){
                    totalCost = BigDecimal.valueOf(Double.valueOf(currentDoc.get("totalCost").toString()));
                }

                String documentDescription = "notFound";
                if (currentDoc.get("documentDescription") != null) {
                    documentDescription = currentDoc.get("documentDescription").toString();
                }

                String projectID = "notFound";
                if (currentDoc.get("projectID") != null) {
                    projectID = currentDoc.get("projectID").toString();
                }

                LocalDate date = null;
                if (currentDoc.get("date") != null) {
                    date = LocalDate.parse(currentDoc.get("date").toString());
                }

                String userID = "notFound";
                if (currentDoc.get("userID") != null) {
                    userID = currentDoc.get("userID").toString();
                }

                String id = "notFound";
                if (currentDoc.get("id") != null) {
                    id = currentDoc.get("id").toString();
                }
                String documentName = "notFound";
                if (currentDoc.get("documentName") != null) {
                    documentName = currentDoc.get("documentName").toString();
                }

                String filePath = "not Yet";
                if (currentDoc.get("filePath") != null) {
                    filePath = currentDoc.get("filePath").toString();
                }

                Document theDoc = new Document(documentName,documentDescription,projectID, userID, totalCost, id, date, filePath );
                this.getListOfDocument().put(theDoc.getId(), theDoc);

            }
//            System.out.println("Import Data from DocumentList.json");
        } catch (IOException | JsonException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Exports the hashmap to DocumentList.json.
     * @author Tin Phu
     */
    public void exportData(){
        try (FileWriter fileWriter = new FileWriter(this.FILEPATH)) {
            Jsoner.serialize(this.listOfDocument, fileWriter);
//            System.out.println("Export Data to DocumentList.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Returns the file path of DocumentList.json.
     * @author Tin Phu
     * @return The file path of the json file.
     */
    public String getFilepath() {
        return FILEPATH;
    }

    /**
     * Adds a document to the list of documents.
     * @author Tin Phu
     * @param theDoc The document to add.
     */
    public void addDocument(final Document theDoc){
        this.listOfDocument.put(theDoc.getId(), theDoc);
        this.exportData();
    }

    /**
     * Deletes a Document by it's ID.
     * @author Tin Phu
     * @param id The ID of the document to be removed.
     */
    public void deleteDocument(final String id){
        this.listOfDocument.remove(id);
        this.exportData();
    }

    /**
     * Deletes the given document.
     * @author Tin Phu
     * @param theDoc The document to be deleted.
     */
    public void deleteDocument(final Document theDoc){
        this.listOfDocument.remove(theDoc.getId());
        this.exportData();
    }

    /**
     * Finds document by it's ID.
     * @author Tin Phu
     * @param id The ID of the document to be found.
     * @return The document the id is associated with.
     */
    public Document findDocumentById(final String id){
        return this.listOfDocument.get(id);
    }
}
