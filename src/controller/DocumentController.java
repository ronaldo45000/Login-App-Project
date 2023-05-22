package controller;

import repository.DocumentRepository;

/**
 * A controller for the list of documents in every project.
 * @author Riley Bennett
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
}
