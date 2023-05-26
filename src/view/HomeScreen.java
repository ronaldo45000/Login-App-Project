package view;

import controller.AppInfoController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.User;

/**
 * A class to create the Home screen of the FileNtro project.
 * Tin Phu: "Please pass ProjectID to ProjectScreen() at line 46"
 * @author Riley Bennett
 * @version 0.2
 */
public class HomeScreen extends JPanel {
    JLabel homeLabel;

    /**
     * Constructor to create the Home screen.
     * @author Riley Bennett
     * @param user The user of the app
     * @param cardPanel The panels to swap to/from
     * @param cardLayout The layout used to swap to/from panels
     */
    public HomeScreen(User user, JPanel cardPanel, CardLayout cardLayout){

        setLayout(new BorderLayout());

        // Creating labels/button
        homeLabel = new JLabel("Welcome " + user.getName() + "!");
        JButton projectButton = new JButton("Go to projects...");

        // Setting up look of GUI
        homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        homeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(homeLabel,BorderLayout.NORTH);
    
        // Use GridBagLayout to organize buttons in a panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        buttonPanel.add(projectButton, cons);
        
        //Add project screen to card, switch to project screen when button is pressed
        cardPanel.add(new ProjectScreen(user, cardPanel, cardLayout, this, "35089418-d50b-4fc8-88ea-bb82c1ea5633"), "ProjectScreen");
        projectButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "ProjectScreen");
            }
        }));
        
         // Switch back to log in screen when logout button is pressed
         JButton logoutButton = new JButton("Log Out");
         logoutButton.addActionListener((new ActionListener() {
             public void actionPerformed(ActionEvent e) {
 
                 cardLayout.show(cardPanel, "LogInScreen");
                 AppInfoController.logout();
             }
         }));

        cons.gridx  = 1;
        cons.insets = new Insets(0, 40, 0, 0);
        buttonPanel.add(logoutButton, cons);

        add(buttonPanel, BorderLayout.CENTER);
     }

     public void updateWelcomeName() {
         homeLabel.setText("Welcome " + AppInfoController.getCurrentUser().getName() + "!");
     }
}
