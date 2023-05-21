package controller;

import repository.DocumentRepository;

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
