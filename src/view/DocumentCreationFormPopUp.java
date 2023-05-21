package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * DocumentCreationFormPopUp
 * @author Tin Phu
 *
 */
public class DocumentCreationFormPopUp extends JPanel{
    private JTextField documentNameField;
    private JTextField documentDescriptionField;
    private JTextField totalCostField;
    private JButton fileSelectorButton;
    private JButton createButton;

    public DocumentCreationFormPopUp() {
        // Create components
        JLabel documentNameLabel = new JLabel("Document Name:");
        documentNameField = new JTextField(20);

        JLabel documentDescriptionLabel = new JLabel("Document Description:");
        documentDescriptionField = new JTextField(20);

        JLabel totalCostLabel = new JLabel("Total Cost:");
        totalCostField = new JTextField(10);

        fileSelectorButton = new JButton("Select File");

        createButton = new JButton("Create Document");

        // Create layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(documentNameLabel, constraints);

        constraints.gridx = 1;
        panel.add(documentNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(documentDescriptionLabel, constraints);

        constraints.gridx = 1;
        panel.add(documentDescriptionField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(totalCostLabel, constraints);

        constraints.gridx = 1;
        panel.add(totalCostField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(fileSelectorButton, constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(createButton, constraints);

        // Set action listeners
        /**
         * @Author Tin Phu
         */
        fileSelectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(panel);
                System.out.println(fileChooser.getSelectedFile());
            }
        });
        /**
         * @Author Tin Phu
         */
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String documentName = documentNameField.getText();
                String documentDescription = documentDescriptionField.getText();
                double totalCost = Double.parseDouble(totalCostField.getText());
                // Create the document using the entered values
                // You can add your own logic here
                System.out.println("Document created: " + documentName);
                System.out.println("Description: " + documentDescription);
                System.out.println("Total cost: " + totalCost);
            }
        });

        JDialog dialog = new JDialog();
        dialog.setContentPane(panel);

        // Set dialog properties
        dialog.setTitle("Document Creation Form");
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null); // Center the dialog on the screen
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Display the dialog
        dialog.setVisible(true);

        // Display the panel
       // JOptionPane.showMessageDialog(null, panel, "Document Creation Form", JOptionPane.PLAIN_MESSAGE);
    }

}
