package view;

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
        frame.setSize(width, height);
        //Setup cardLayout and carPanel.
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        //Tin Phu:
        //cardPanel, cardLayout are passed to OwnerProfileForm because the button need to access them to switch
        //to AboutScreen()
        JPanel mainPanel = new OwnerProfileForm(cardPanel, cardLayout);
        cardPanel.add(mainPanel);



       frame.add(cardPanel);


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