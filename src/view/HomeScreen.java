package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JPanel {

    public HomeScreen(User user, JPanel cardPanel, CardLayout cardLayout){

        setLayout(new BorderLayout());


        JLabel homeLabel = new JLabel("Welcome " + user.getName() + "!");
        JButton projectButton = new JButton("Go to projects...");
        JButton aboutButton = new JButton("About...");

        homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        homeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(homeLabel,BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        buttonPanel.add(projectButton, cons);

        cardPanel.add(new AboutScreen(user, cardPanel, cardLayout), "AboutScreen");
        cons.insets = new Insets(30, 0, 0, 0);
        cons.gridy = 1;
        aboutButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"AboutScreen");
            }
        }));
        buttonPanel.add(aboutButton, cons);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "LogInScreen");
            }
        }));
        
        cons.gridy = 2;
        cons.insets = new Insets(100, 0, 0, 0);
        buttonPanel.add(logoutButton, cons);

        add(buttonPanel, BorderLayout.CENTER);
     }
}
