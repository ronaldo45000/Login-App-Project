package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.AppInfoController;
import controller.UserController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tin Phu
 * @author Riley Bennett
 * @version 0.1
 * CreateProfile JPanel allows the user to create a profile.
 * BorderLayout and GridBagLayout are used in this JPanel.
 */
public class CreateProfile extends JPanel {
    /**
     * State of nameFields
     */
    private JTextField nameField;
    /**
     * State of nameFields
     */
    private JTextField emailArea;

    public CreateProfile(JPanel cardPanel, CardLayout  cardLayout  ){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Set padding

        JLabel titleLabel = new JLabel("Welcome to FileNtro");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel usernameLabel = new JLabel("Username:");
        nameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Email:");
        emailArea = new JTextField(20);
        JButton createButton = new JButton("Create");
        /**
         * @author Riley Bennett
         * @author Tin Phu
         */
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String email = emailArea.getText();
                //Tin Phu
                //Show Error message if one of user inputs isEmty()
                if(name.isEmpty() || email.isEmpty()){
                    JOptionPane.showMessageDialog(CreateProfile.this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //TinPhu
                //Email Validation
                if(!emailValidation(email)){
                    JOptionPane.showMessageDialog(CreateProfile.this, "Invalid Email", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (UserController.userExists(name, email)) {
                    JOptionPane.showMessageDialog(CreateProfile.this, "User already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Tin Phu
                //AboutScreen is created with new User() as argument
                //cardPanel, cardLayout are passed to AboutScreen, so we can switch back to previous JPanel.

                User theUser = UserController.addUser(name,email);
                AppInfoController.setUser(theUser);
                cardPanel.add(new HomeScreen(theUser, cardPanel, cardLayout), "HomeScreen");
                JOptionPane.showMessageDialog(CreateProfile.this, "Account successfully created!", "Success", JOptionPane.INFORMATION_MESSAGE);

                //Switch Trigger Here.
                cardLayout.show(cardPanel, "HomeScreen");
            }
        });

        JLabel newUserLabel = new JLabel("Already have an account?");
        JButton logInButton = new JButton("Log in");
        /**
         * @author Riley Bennett
         * @author Tin Phu
         */
        logInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "LogInScreen");
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
        add(createButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(newUserLabel, gbc);

        gbc.gridy = 5;
        add(logInButton, gbc);




    }

    /**
     * Email Validation using regex Expression
     * @Author Tin Phu
     * @return boolean
     */
    public boolean emailValidation(String emailString){
        String regex = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailString);
        return matcher.matches();
    }
}
