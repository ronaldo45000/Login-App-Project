package view;

import controller.DocumentController;
import controller.ProjectController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.text.DecimalFormat;
import model.Document;
/**
 * The budget tab for a project in the FileNtro program.
 * @author Thinh Le
 * @author Riley Bennett
 * @author Tin Phu (fixing bugs only)
 * @version 0.3
 */
public class BudgetTab extends JPanel {

    /**
     * The ID of the project this tab belongs to.
     */
    private String theProjectID;

    /**
     * The total cost of this project.
     */
    public double theTotalCost;

    /**
     * The total budget of this project.
     */
    public double totalBudget;

    /**
     * The label to display the total cost with.
     */
    private JLabel totalLabel;

    /**
     * This is the label for total budget.
     */
    private JLabel totalBudgetLabel;

    /**
     * This is the text field for set budget.
     */
    private JTextField setBudgetField;

    /**
     * Button to set the budget with.
     */
    private JButton setBudgetButton;

    /**
     * Mapping of Document ID/Document object pairs.
     */
    public HashMap<String, Document> myDoc;

    /**
     * The model used to implement the table.
     */
    public final DefaultTableModel model;
    /**
     * The table to display items in.
     */
    public final JTable table;

    /**
     * Formatter to display doubles with 2 decimal places.
     */
    private final DecimalFormat df = new DecimalFormat("#.##");

    /**
     * This is the constructor to create the Budget Tab.
     *
     * @param theProjectID The ID of the project
     * @author Thinh Le
     */
    public BudgetTab(final String theProjectID) {

        this.theProjectID = theProjectID;

        // Get documents associated with the project.
        myDoc = DocumentController.getDocsByProjectID(theProjectID);

        setLayout(new GridBagLayout());

        // Create the table model
        model = new DefaultTableModel();
        // Add id column
        model.addColumn("ID");
        // Add Name column
        model.addColumn("Name");
        // Add Total cost column
        model.addColumn("Cost");

        // Create the table and set the model
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(350, 350));

        totalLabel = new JLabel("CurrentCost:$0.00");
        totalLabel.setFont(new Font("Verdana", Font.PLAIN, 18));

        updateTotalCost();

        // Put list of documents into table
        myDoc.forEach((k, e) -> {
            addRowToTable(k, e.getDocumentName(), e.getTotalCost().toString());
        });

        // Make table uneditable
        table.setDefaultEditor(Object.class, null);

        // Add total budget label
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(15, 15, 15, 15);
        totalBudgetLabel = new JLabel("Total Budget:$");

        add(totalBudgetLabel, c);

        // Add budget field
        setBudgetField = new JTextField();
        setBudgetField.setText("");
        setBudgetField.setEditable(false);
        c.gridx = 1;
        c.gridy = 0;

        setBudgetField.setColumns(5);

        add(setBudgetField, c);
        c.gridx = 2;
        c.gridy = 2;

        add(totalLabel, c);

        setBudgetButton = new JButton("Set Budget");
        c.gridx = 0;
        c.gridy = 1;

        add(setBudgetButton, c);
        JScrollPane scrollPane = new JScrollPane(table);
        c.gridx = 0;
        c.gridy = 2;
        add(scrollPane, c);

        // Add document button
        JButton addButton = new JButton("Add Document");
        c.gridx = 0;
        c.gridy = 3;
        add(addButton, c);

        // Delete button
        JButton deleteButton = new JButton("Delete");
        c.gridx = 1;
        c.gridy = 3;
        add(deleteButton, c);

        JButton changeButton = new JButton("Change Name");
        c.gridx = 2;
        c.gridy = 3;
        add(changeButton, c);

        JButton changeCostButton = new JButton("Change Cost");
        c.gridx = 3;
        c.gridy = 3;
        add(changeCostButton, c);

        JButton addCostButton = new JButton("Add Cost only");
        c.gridx = 0;
        c.gridy = 4;
        add(addCostButton, c);

        updateTotalBudgetLabel();

        /**
         * Action listener for add cost only button.
         */
        addCostButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                // Prompt the user for item name and price
                String itemName = JOptionPane.showInputDialog("Enter Item Name:");
                if (itemName == null) {
                    return; // User clicked Cancel
                }

                String priceStr = JOptionPane.showInputDialog("Enter Price:");
                if (priceStr == null) {
                    return; // User clicked Cancel
                }

                // Convert the price to a double
                double price;
                try {
                    price = Double.parseDouble(priceStr);
                    
                    // Round to 2 decimal places
                    price = Double.valueOf(df.format(price));
                } catch (NumberFormatException ex) {
                         JOptionPane.showMessageDialog(BudgetTab.this, "Invalid Price!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Document doc = null;

                doc = new Document(itemName, "", theProjectID, "", BigDecimal.valueOf(price));
                DocumentController.addDocument(doc);
                myDoc.put(doc.id(), doc);

                theTotalCost += myDoc.get(doc.id()).getTotalCost().doubleValue();
                totalLabel.setText("CurrentCost:$" + theTotalCost);

                updateTable();

                //This is a warning message when it is over your budget.
                if(theTotalCost>totalBudget){

                    JOptionPane.showMessageDialog(BudgetTab.this,"You are over your budget!","Reminder",JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        /**
         * Action Listener for add document button.
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DocumentCreationFormPopUp(theProjectID);



            }
        });

        /**
         * Action Listener for set budget button.
         */
        setBudgetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String input = JOptionPane.showInputDialog("Enter new budget:");
                try {
                    double newBudget = Double.parseDouble(input);

                    // Round to 2 decimal places
                    newBudget = Double.valueOf(df.format(newBudget));

                    ProjectController.setTotalBudgetByID(theProjectID, BigDecimal.valueOf(newBudget));
                    setTotalBudget(newBudget);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BudgetTab.this, "Please enter a numeric value.");

                }
            }
        });

        /**
         * Action Listener for delete button.
         */
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {

                String name = model.getValueAt(row, 1).toString();
//                System.out.println("Deleting " + name);
                String id = (String) table.getValueAt(row, 0);
                theTotalCost -= myDoc.get(id).getTotalCost().doubleValue();
                totalLabel.setText("CurrentCost:$" + theTotalCost); 
                DocumentController.deleteADocument(myDoc.get(id));

                myDoc.remove(id);

                updateTable();
            }
        });

        /**
         * Action listener for change name button.
         */
        changeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "No item selected!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String currentID = (String) table.getValueAt(selectedRow, 0); //CURRENT ID
            String currentName = (String) table.getValueAt(selectedRow, 1); //CURRENT Name
            String newMessage = JOptionPane.showInputDialog(this, "Enter New Name:", currentName); //NEW name
            String currentDecription = DocumentController.findDocbyID(currentID).getDocumentDescription();

            if (newMessage == null) {
                return;
            }

            Document doc2 = new Document(newMessage, currentDecription, theProjectID, "",
                                         myDoc.get(currentID).getTotalCost());

            DocumentController.deleteADocument(myDoc.get(currentID));
            myDoc.remove(currentID);

            DocumentController.addDocument(doc2);
            myDoc.put(currentID, doc2);

            updateTable();
        });

        /**
         * Action listener for change cost button.
         */
        changeCostButton.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "No item selected!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String currentID = (String) table.getValueAt(selectedRow, 0); //CURRENT ID

            String currentItemPrice = (String) table.getValueAt(selectedRow, 2); //CURRENT PRICE
            double price = Double.parseDouble(currentItemPrice);

            //Round to 2 decimal places
            price = Double.valueOf(df.format(price));

            String message = JOptionPane.showInputDialog(this, "Enter New Price:", price); //NEW PRICE

            if (message == null) {
                return;
            }

            double newPrice;
            newPrice = Double.parseDouble(message);

            //Round to 2 decimal places
            newPrice = Double.valueOf(df.format(newPrice));

            //Update the price in the table
            theTotalCost -= myDoc.get(currentID).getTotalCost().doubleValue();
            String currentDecription = DocumentController.findDocbyID(currentID).getDocumentDescription();

            Document newDoc2 = new Document(myDoc.get(currentID).getDocumentName(), currentDecription,
                                            theProjectID, "", new BigDecimal(newPrice));

            DocumentController.deleteADocument(myDoc.get(currentID));
            myDoc.remove(currentID);

            DocumentController.addDocument(newDoc2);
            myDoc.put(currentID, newDoc2);
            theTotalCost += myDoc.get(currentID).getTotalCost().doubleValue();

            totalLabel.setText("CurrentCost:$" + theTotalCost);

            updateTable();
        });
    }

    /**
     * Adds a row to the table.
     *
     * @param id The ID of the item to be added.
     * @param name The name of the item to be added.
     * @param totalCost The cost of the item to be added.
     *
     */
    public void addRowToTable(String id, String name, String totalCost) {
        Object[] row = {id, name, totalCost};
        model.addRow(row);
    }

    /**
     * Updates the table with new data.
     */
    public void updateTable() {
        // Clear the rows
        model.setRowCount(0);

        //Update each row
        myDoc.forEach((k, e) -> {
            addRowToTable(k, e.getDocumentName(), df.format(e.getTotalCost()));
        });

    }

    /**
     * Sets the total budget of the project.
     * @param budget The budget to set to.
     */
    public void setTotalBudget(double budget) {
        totalBudget = budget;
        updateTotalBudgetLabel();
    }

    /**
     * Updates the label of the total budget.
     */
    public void updateTotalBudgetLabel() {
        setBudgetField.setText("" + ProjectController.findProjectByID(theProjectID).getBudget());
    }

    /**
     * Updates the total cost of this project.
     */
    public void updateTotalCost() {
        myDoc.forEach((k, b) -> {
            theTotalCost += b.getTotalCost().doubleValue();
        });

        // Round to 2 decimal places
        theTotalCost = Double.valueOf(df.format(theTotalCost));

        totalLabel.setText("CurrentCost:$" + String.format("%.2f", theTotalCost));
    }

    /**
     * Class to create the document creation popup.
     * @author Tin Phu
     * @author Riley Bennett
     */
    public class DocumentCreationFormPopUp extends JPanel{

        /**
         * Name field of the popup.
         */
        public final JTextField documentNameField;

        /**
         * Description field of the popup.
         */
        public final JTextField documentDescriptionField;

        /**
         * Cost field of the popup.
         */
        public final JTextField totalCostField;

        /**
         * File path associated with this document (if any)
         */
        private String srcFileString = "";

        /**
         * Dialog to create this popup with.
         */
        public final JDialog dialog = new JDialog();

        /**
         * Constructor to create the popup.
         * @author Tin Phu
         * @param theProjectID
         */
        public DocumentCreationFormPopUp(String theProjectID) {
            // NOTE: See DocumentTab for more in-depth inline comments.

            // Create components
            JLabel documentNameLabel = new JLabel("Document Name:");
            documentNameField = new JTextField(20);

            JLabel documentDescriptionLabel = new JLabel("Document Description:");
            documentDescriptionField = new JTextField(20);

            JLabel totalCostLabel = new JLabel("Total Cost:");
            totalCostField = new JTextField(10);

            JLabel fileSrcStringLabel = new JLabel("(option)");

            // Set max width for file name
            Dimension d = fileSrcStringLabel.getPreferredSize();
            fileSrcStringLabel.setPreferredSize(new Dimension(d.width + 90, d.height));
            
            JButton fileSelectorButton = new JButton("Select File");

            JButton createButton = new JButton("Create Document");

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
            panel.add(fileSrcStringLabel, constraints);
            constraints.gridx = 1;
            constraints.gridy = 3;
            constraints.gridwidth = 2;
            panel.add(fileSelectorButton, constraints);

            constraints.gridy = 4;
            constraints.anchor = GridBagConstraints.CENTER;
            panel.add(createButton, constraints);

            /**
             * Action Listener for the file selector button.
             * @author Tin Phu
             */
            fileSelectorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(panel);
                    if(result == JFileChooser.APPROVE_OPTION){
                        srcFileString = fileChooser.getSelectedFile().toString();
                        File file = new File(srcFileString);
                        fileSrcStringLabel.setText(file.getName());
                    }
                }
            });

            /**
             * Action listener for the create button.
             * @author Tin Phu
             */
            createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String documentName = documentNameField.getText();
                    String documentDescription = documentDescriptionField.getText();
                    Document newDoc = null;
                    if(documentName.isEmpty() || documentDescription.isEmpty() || totalCostField.getText().isEmpty() ){
                        JOptionPane.showMessageDialog(BudgetTab.this, "Please enter required information!");

                    } else if(documentDescription.length() >= 1300){
                        JOptionPane.showMessageDialog(BudgetTab.this, "The Description is too long!");

                    } else {
                        double totalCost = Double.parseDouble(totalCostField.getText());
                        DecimalFormat df = new DecimalFormat("#.##");
                        
                        // Round to 2 decimal places
                        totalCost = Double.valueOf(df.format(totalCost));

                        try {
                            if(srcFileString.isEmpty()){
                                newDoc = new Document(documentName,documentDescription,theProjectID,"", 
                                BigDecimal.valueOf(totalCost));

                            } else {
                                newDoc = new Document(documentName,documentDescription,theProjectID,"", 
                                BigDecimal.valueOf(totalCost), srcFileString );
                            }

                            DocumentController.addDocument(newDoc);
                            myDoc.put(newDoc.id(), newDoc);
                            dialog.setVisible(false);
                            
                            theTotalCost += myDoc.get(newDoc.id()).getTotalCost().doubleValue();

                            // Round to 2 decimal places
                            theTotalCost = Double.valueOf(df.format(theTotalCost));
                            totalLabel.setText("Current Cost: $" + theTotalCost);

                            updateTable();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(BudgetTab.this, "Something went wrong! The File could not be coppied");
                        }
                    }
                }
            });
            dialog.setLocationRelativeTo(BudgetTab.this);
            dialog.setContentPane(panel);
            dialog.setTitle("Document Creation Form");
            dialog.setSize(500, 400);
            dialog.setLocationRelativeTo(null); // Center the dialog on the screen
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
    }
}