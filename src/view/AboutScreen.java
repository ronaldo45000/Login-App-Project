package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutScreen extends JPanel {

    public AboutScreen(User user, JPanel cardPanel, CardLayout cardLayout){
        JLabel label = new JLabel("Hello World from AboutScreen()");
        JLabel labeName = new JLabel(user.getName());
        JLabel labeAddress = new JLabel(user.getEmail());


        add(label);
        add(labeName);
        add(labeAddress);


        JButton PreviousButton = new JButton("Previous JPanel");
        PreviousButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(cardPanel);
            }
        }));

        add(PreviousButton);
    }
}
