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

    /**
     * Copy and Delete File Tests.
     * Test1: A new doc with AppInfo.json is created and copy to \src\test
     * Check if the file at that location exists
     * Test2: Delete the document
     * check if the file at that location still exists.
     * Unfortunately I have to two tests into a single @Test,
     * because I want to copy and delete the file immediately.
     * @Author Tin Phu.
     */
    @Test
    public void copyAndDeleteFileWhenCreateDocTest(){
        String currentPath = System.getProperty("user.dir");
        String srcFile = currentPath + "\\AppInfo.json";
        try {
            Document newDoc = new Document("DocTest1","DocTest1Drecription",
                    "\\src\\test","UserID", BigDecimal.valueOf(111),srcFile );

            File destFile = new File(currentPath + newDoc.getFilePath());
            //Test1: Copy
            if(destFile.exists()){
                Assertions.assertTrue(true);
            } else {
                Assertions.fail();
            }

            DocumentController.addDocument(newDoc);
            //Test2:Delete
            DocumentController.deleteADocument(newDoc);
            if(!destFile.exists()){
                Assertions.assertTrue(true);
            } else {
                Assertions.fail();
            }
        } catch (IOException e) {
            Assertions.fail();
        }
    }


}
