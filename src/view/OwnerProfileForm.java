package view;

import model.About;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author Tin Phu
 * @Version 0.1
 * OwnerProfileForm JPanel is responsible to create Ower Profile.
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

    public OwnerProfileForm(JPanel cardPanel, CardLayout  cardLayout  ){
        setLayout(new BorderLayout());

        // Create and add the application name label
        JLabel appNameLabel = new JLabel("Welcome to FileNtro");
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(appNameLabel, BorderLayout.NORTH);

        // Create the form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 0, 20);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Name:");
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        nameField = new JTextField();
        formPanel.add(nameField, gbc);



        gbc.insets = new Insets(0, 20, 0, 20);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;

        JLabel addressLabel = new JLabel("Email:");
        formPanel.add(addressLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        emailArea = new JTextField();
        formPanel.add(emailArea, gbc);

        // Add the form panel to the center of the UserInfoPanel
        add(formPanel, BorderLayout.CENTER);

        // Create and add the create button
        JButton createButton = new JButton("Create Profile");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String address = emailArea.getText();
                //Tin Phu
                //Show Error message if one of user inputs isEmty()
                if(name.isEmpty() || address.isEmpty()){
                    JOptionPane.showMessageDialog(OwnerProfileForm.this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Tin Phu
                //AboutScreen is created with new User() as argument
                //cardPanel, cardLayout are passed to AboutScreen, so we can switch back to previous JPanel.
                cardPanel.add(new AboutScreen(new User(name, address), cardPanel, cardLayout));
                //Switch Trigger Here.
                cardLayout.next(cardPanel);

            }
        });
        add(createButton, BorderLayout.SOUTH);
    }

    /**
     * NameField getter
     * @return
     */
    public String getNameField() {
        return nameField.getText();
    }

    /**
     * AddressField getter
     * @return
     */
    public String getAddressField() {
        return emailArea.getText();
    }
}
