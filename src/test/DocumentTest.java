package test;
import controller.DocumentController;
import model.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
/**
 * JUnit test Document Controller and Repository.
 * @author Tin Phu
 */
public class DocumentTest {
    Document testDoc = new Document("DocTest1","DocTest1Drecription",
            "projectID","UserID", BigDecimal.valueOf(111));
    @BeforeEach
    public void setUp(){
        DocumentController.addDocument(testDoc);
    }

    /**
     * Fetch Document Test.
     * @Author Tin Phu
     */
    @Test
    public void fetchDocByIDTest() {
        Document currentDoc = DocumentController.findDocbyID(testDoc.getId());
        assertEquals(testDoc.getId(), currentDoc.getId());
        assertEquals(testDoc.getDate(), currentDoc.getDate());
        assertEquals(testDoc.getDocumentName(), currentDoc.getDocumentName());
        assertEquals(testDoc.getProjectID(), currentDoc.getProjectID());
    }

    /**
     * Deleting Document Test.
     * @Author Tin Phu
     */
    @Test
    public void DeletingDocByIDTest() {
        DocumentController.deleteADocument(testDoc);
        assertNull(DocumentController.findDocbyID(testDoc.getId()));
    }
}
