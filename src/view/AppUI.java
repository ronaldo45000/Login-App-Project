package view;

import controller.AppInfoController;
import controller.DocumentController;
import controller.ProjectController;
import controller.UserController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * AppUI is the main class of the FileNtro application.
 * @author Tin Phu
 * @author Riley Bennett
 * @version 0.2
 */
public class AppUI{
    private JFrame frame;
    /**
     * Panel containing every JPanel of the app.
     */
    private JPanel cardPanel;
    /**
     * The overall cardlayout of the app.
     */
    private CardLayout cardLayout;

    /**
     * Creates the JFrame() of the app.
     * @author Tin Phu
     * @author Riley Bennett
     */
    public void createAndShowGUI() {
        // Create the frame
        frame = new JFrame("FileNtro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a label with "Hello World" text
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);
        // Set the size of the frame to medium
        int width = 400;
        int height = 300;
        frame.setSize(2 * width, 2 * height);
        //Setup cardLayout and carPanel.
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        //Tin Phu:
        //cardPanel, cardLayout are passed to OwnerProfileForm because the button need to access them to switch
        //to AboutScreen()



        JPanel mainPanel = new LogInScreen(cardPanel, cardLayout);
        cardPanel.add(mainPanel, "LogInScreen");

        frame.add(cardPanel);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the "File" menu
        JMenu fileMenu = new JMenu("File");
        //Adding Import Menu Item
        JMenuItem importMenuItem = new JMenuItem("Import");
        JMenuItem exportMenuItem = new JMenuItem("Export");
        importMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "While importing data, you will be logged out to Home Screen.\nContinue?",
                "Warning", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    AppInfoController.importData();
                    UserController.importData();
                    DocumentController.importData();
                    ProjectController.importData();
                    if(AppInfoController.getCurrentUser() == null){

                        cardLayout.show(cardPanel, "LogInScreen");
                    } else {
                        cardPanel.add(new HomeScreen(AppInfoController.getCurrentUser(), cardPanel, cardLayout), "HomeScreen");
                        cardLayout.show(cardPanel, "HomeScreen");
                    }
                    frame.dispose();
                    frame.setVisible(true);


                }
            }
        });
       fileMenu.add(importMenuItem);
        exportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppInfoController.exportData();
                UserController.exportData();
                DocumentController.exportData();
                ProjectController.exportData();
                JOptionPane.showMessageDialog(frame, "Check program folder for exported JSON files.");
            }
        });
        fileMenu.add(exportMenuItem);

        // Create the "About" menu
        JMenu aboutMenu = new JMenu("About");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardPanel.add(new AboutScreen(AppInfoController.getCurrentUser(), cardPanel, cardLayout ), "AboutScreen");
                cardLayout.show(cardPanel, "AboutScreen");
            }
        });
        aboutMenu.add(aboutMenuItem);
        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        // Set the menu bar to the frame
        frame.setJMenuBar(menuBar);

        // Pack and center the frame
        //frame.pack();
        frame.setLocationRelativeTo(null);

        // Show the frame
        frame.setVisible(true);

        if(AppInfoController.getCurrentUser() != null){
            cardPanel.add(new HomeScreen(AppInfoController.getCurrentUser(), cardPanel, cardLayout), "HomeScreen");
            cardLayout.show(cardPanel, "HomeScreen");
        }
    }

    public static void main(String[] args) {
        // Run the GUI code in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            AppUI app = new AppUI();
            app.createAndShowGUI();
        });
    }
}