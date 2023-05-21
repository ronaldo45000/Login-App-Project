package controller;

import repository.ProjectRepository;

public class ProjectController {
    private final static ProjectRepository projectRepository = new ProjectRepository();

    /**
     * Imports data from ProjectList.json
     * @author Riley Bennett
     */
    public static void importData() {
        projectRepository.importData();
    }

    /**
     * Exports project data to ProjectList.json
     * @author Riley Bennett
     */
    public static void exportData() {
        projectRepository.exportData();
    }
}
