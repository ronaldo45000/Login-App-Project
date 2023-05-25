package view;

import controller.AppInfoController;
import controller.UserController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.User;

/**
 * @author Riley Bennett
 * @version 0.2
 * OwnerProfileForm JPanel is responsible to create Owner Profile.
 * BorderLayout and GridBagLayout are used in this JPanel.
 */
public class LogInScreen extends JPanel {
    /**
     * State of nameFields
     */
    private JTextField nameField;
    /**
     * State of nameFields
     */
    private JTextField emailArea;

    /**
     * Creates the panel for the user to log in.
     * @author Riley Bennett
     * @author Tin Phu
     * @param cardPanel The cardpanel to be used
     * @param cardLayout The cardlayout to be used
     */
    public LogInScreen(JPanel cardPanel, CardLayout  cardLayout  ){

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Set padding

        JLabel titleLabel = new JLabel("Welcome to FileNtro");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel usernameLabel = new JLabel("Username:");
        nameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Email:");
        emailArea = new JTextField(20);
        JButton loginButton = new JButton("Log In");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String email = emailArea.getText();

                //Show Error message if one of user inputs isEmpty()
                if(name.isEmpty() || email.isEmpty()){
                    JOptionPane.showMessageDialog(LogInScreen.this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User theUser = UserController.findUser(name, email);

                if (theUser == null) {
                    JOptionPane.showMessageDialog(LogInScreen.this, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //AboutScreen is created with new User() as argument
                //cardPanel, cardLayout are passed to AboutScreen, so we can switch back to previous JPanel.

                cardPanel.add(new HomeScreen(theUser, cardPanel, cardLayout), "HomeScreen");
                AppInfoController.setUser(theUser);
                nameField.setText("");
                emailArea.setText("");
                //Switch Trigger Here

                cardLayout.show(cardPanel, "HomeScreen");

            }
        });

        JLabel newUserLabel = new JLabel("New to FileNtro?");
        JButton createAcct = new JButton("Sign Up");

        createAcct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(new CreateProfile(cardPanel, cardLayout), "CreateProfileScreen");
                nameField.setText("");
                emailArea.setText("");
                cardLayout.show(cardPanel, "CreateProfileScreen");
            }
        });


        // Add components to the panel using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(emailArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(newUserLabel, gbc);

        gbc.gridy = 5;
        add(createAcct, gbc);

    }
}
