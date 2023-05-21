package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Tin Phu
 * @version 0.1
 * AppUI is the main class of the application
 * Messeges to Team members: Please do a quick research on CardLayout which helps switch between JPanels in Swing.
 */
public class AppUI {
    private JFrame frame;
    /**
     * Tin Phu
     * cardPanel will contain all the JPanels we want to switch.
     */
    private JPanel cardPanel;
    /**
     * Tin Phu
     * cardLayout.next() will switch the next JPanel of cardPanel above.
     */
    private CardLayout cardLayout;

    /**
     * The main JFrame and other components are created and then run in main().
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
        JPanel mainPanel = new OwnerProfileForm(cardPanel, cardLayout);
        cardPanel.add(mainPanel, "LogInScreen");



       frame.add(cardPanel);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the "File" menu
        JMenu fileMenu = new JMenu("File");
        //Adding Import Menu Item
        JMenuItem importMenuItem = new JMenuItem("Import");
        importMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Importing");
            }
        });
        fileMenu.add(importMenuItem);

        JMenuItem exportMenuItem = new JMenuItem("Export");
        exportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exporting");
            }
        });
        fileMenu.add(exportMenuItem);

        // Create the "About" menu
        JMenu aboutMenu = new JMenu("About");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(new AboutScreen( new User("",""), cardPanel, cardLayout, "LogInScreen" ), "AboutScreen");
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
    }

    public static void main(String[] args) {
        // Run the GUI code in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            AppUI app = new AppUI();
            app.createAndShowGUI();
        });
    }
}