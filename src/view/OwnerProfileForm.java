package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tin Phu
 * @version 0.1
 * OwnerProfileForm JPanel is responsible to create Owner Profile.
 * BorderLayout and GridBagLayout are used in this JPanel.
 */
public class OwnerProfileForm extends JPanel {
    /**
     * State of nameFields
     */
    private JTextField nameField;
    /**
     * State of nameFields
     */
    private JTextField emailArea;


    private JTextField usernameField;
    private JTextField passwordField;

    public OwnerProfileForm(JPanel cardPanel, CardLayout  cardLayout  ) {
//        setLayout(new BorderLayout());
//
//        // Create and add the application name label
//        JLabel appNameLabel = new JLabel("Welcome to FileNtro");
//        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        appNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
//        add(appNameLabel, BorderLayout.NORTH);
//
//        // Create the form panel
//        JPanel formPanel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(20, 20, 0, 20);
//        gbc.anchor = GridBagConstraints.WEST;
//
//        JLabel nameLabel = new JLabel("Name:");
//        formPanel.add(nameLabel, gbc);
//
//        gbc.gridx = 1;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.weightx = 1;
//        nameField = new JTextField();
//        formPanel.add(nameField, gbc);
//
//
//
//        gbc.insets = new Insets(0, 20, 0, 20);
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.weightx = 0.0;
//
//        JLabel addressLabel = new JLabel("Email:");
//        formPanel.add(addressLabel, gbc);
//
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.weightx = 1.0;
//        gbc.weighty = 1.0;
//
//        emailArea = new JTextField();
//        formPanel.add(emailArea, gbc);
//
//
//
//        // Add the form panel to the center of the UserInfoPanel
//        add(formPanel, BorderLayout.CENTER);
//
//        // Create and add the create button
//        JButton createButton = new JButton("Create Profile");
//        createButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                String name = nameField.getText();
//                String email = emailArea.getText();
//                //Tin Phu
//                //Show Error message if one of user inputs isEmty()
//                if(name.isEmpty() || email.isEmpty()){
//                    JOptionPane.showMessageDialog(OwnerProfileForm.this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                //TinPhu
//                //Email Validation
//                if(!emailValidation(email)){
//                    JOptionPane.showMessageDialog(OwnerProfileForm.this, "Invalid Email", "Error", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//
//                //Tin Phu
//                //AboutScreen is created with new User() as argument
//                //cardPanel, cardLayout are passed to AboutScreen, so we can switch back to previous JPanel.
//
//                cardPanel.add(new HomeScreen(new User(name, email), cardPanel, cardLayout), "HomeScreen");
//                //Switch Trigger Here.
//                cardLayout.show(cardPanel, "HomeScreen");
//
//            }
//        });
//        add(createButton, BorderLayout.SOUTH);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Set padding

        JLabel titleLabel = new JLabel("Welcome to FileNtro");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel usernameLabel = new JLabel("Username:");
        nameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        emailArea = new JTextField(15);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = nameField.getText();
                String password = emailArea.getText();

                cardPanel.add(new HomeScreen(new User(username, password), cardPanel, cardLayout), "HomeScreen");
                //Switch Trigger Here.
                cardLayout.show(cardPanel, "HomeScreen");
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
