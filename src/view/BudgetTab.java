package view;

import model.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This is the budget tab where it shows the budget management.
 *
 * @author Thinh Le
 * @version 0.1
 */
public class BudgetTab extends JPanel implements ActionListener {


    double totalBudget;
    private JLabel totalBudgetLabel;
    private JLabel currentCostLabel;
    JTextField setBudgetField;
    private JTextField addDocumentCostField;
    private JButton setBudgetButton;

    private JButton addDocumentCost;
    private JButton addCostOnly;


    JButton name;
    JTextField currentNameCost;


    //from edit item
    private DefaultTableModel model;
    private JTable table;
    private JLabel totalLabel;
    private double totalCost = 0.0;


    /**
     * This is the constructor to create the budget Tab GUI.
     */
    public BudgetTab() {


        setLayout(new GridBagLayout());
        model = new DefaultTableModel();
        model.addColumn("Item Name");
        model.addColumn("Price");

        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(350, 350));
        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(15, 15, 15, 15);

        totalBudgetLabel = new JLabel("Total Budget: $");

        add(totalBudgetLabel, constraints);

        setBudgetField = new JTextField();

        setBudgetField.setEditable(false);
        constraints.gridx = 1;
        constraints.gridy = 0;

        setBudgetField.setColumns(5);

        add(setBudgetField, constraints);

        setBudgetButton = new JButton("Set Budget");
        constraints.gridx = 0;
        constraints.gridy = 1;
        setBudgetButton.addActionListener(this);
        add(setBudgetButton, constraints);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        // Create the Delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteItem();
            }
        });

        // Create the Total Cost label
        totalLabel = new JLabel("Current Cost: $0.00");
        totalLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        // Create the Edit button
        JButton editButton = new JButton("Change Name");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                editItem();
            }
        });

        JButton changeCost = new JButton("Change Cost");
        changeCost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                costItem();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 2;

        add(scrollPane, constraints);
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(totalLabel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(addButton, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(deleteButton, constraints);
        constraints.gridx = 2;
        constraints.gridy = 3;
        add(editButton, constraints);
        constraints.gridx = 3;
        constraints.gridy = 3;
        add(changeCost, constraints);

        // Add the components to the frame


//        name= new JButton("List of Name and Price");
//        name.addActionListener(this);
//        constraints.gridx = 0;
//        constraints.gridy = 4;
//
//        add(name, constraints);
//
//
//
//        currentNameCost = new JTextField("Change->");
//        currentNameCost.setEditable(false);
//        constraints.gridx = 1;
//        constraints.gridy = 4;
//        //   panel.add(currentNameCost, constraints);
//
//
//        JMenuBar menuBar = new JMenuBar();
//
//
//
//        constraints.gridx = 2;
//        constraints.gridy = 4;
//        constraints.gridwidth = -1;
//        constraints.fill =15;
//        menuBar.setBackground(Color.GREEN);
//
//        add(menuBar, constraints);
//
//        constraints.insets = new Insets(200, 0, 0, 0);
//
        addDocumentCost = new JButton("Add Document Cost");
        constraints.gridx = 0;
        constraints.gridy = 6;
        addDocumentCost.addActionListener(this);
        add(addDocumentCost, constraints);
//
//
        addCostOnly = new JButton("Add Cost Only");
        addCostOnly.addActionListener(this);
        constraints.gridx = 2;
        constraints.gridy = 6;
        constraints.weighty = 1;
        add(addCostOnly, constraints);
//
//
//        currentCostLabel = new JLabel("Current Cost: $");
//        constraints.gridx = 0;
//        constraints.gridy = 6;
//
//        add(currentCostLabel, constraints);


        addDocumentCostField = new JTextField();
        addDocumentCostField.setEditable(false);
        constraints.gridx = 1;
        constraints.gridy = 6;

//        FileReader file = null;
//        try {
//            file = new FileReader("C:\\Users\\onme7\\Downloads\\check.txt");
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        Scanner sc = new Scanner(file);
//        double sum = 0;
//        while(sc.hasNext())
//        {
//            String line = sc.nextLine();
//            // spit the line on - char
//            String [] data = line.split(",");
//            // Important : assuming price is always at index 2 parse and use value
//            sum = sum + Double.parseDouble(data[1].trim());
//        }

        //  addDocumentCostField.setColumns(5);
        // setBackground(Color.);


        //  add(addDocumentCostField, constraints);


    }

    /**
     * This is the action listener when user clicks on a button
     *
     * @param e the event to be processed
     */

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addDocumentCost) {

//            DocumentCreationFormPopUp b = new DocumentCreationFormPopUp();
//            model.addRow(new Object[]{b.getItemName(), b.getItemPrice()});
//            totalCost += Double.parseDouble(b.getItemPrice());
//            updateTotalCost();

            new  DocumentCreationPopUp();

        }
        if (e.getSource() == setBudgetButton) {
            //frame.dispose();
            //ToSetBudget w = new ToSetBudget();

            String input = JOptionPane.showInputDialog("Enter new budget:");
            try {
                double newBudget = Double.parseDouble(input);
                setTotalBudget(newBudget);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a numeric value.");

            }

        }
//        if (e.getSource() == name) {
//
//            EditItem w = new EditItem();
//
//        }

        if (e.getSource() == addCostOnly) {
          //  new AddCostOnly();
        }

    }

    /**
     * This to set the total budget
     *
     * @param budget
     */
    public void setTotalBudget(double budget) {
        totalBudget = budget;
        updateTotalBudgetLabel();
    }

    /**
     * Update the budget
     */
    private void updateTotalBudgetLabel() {

        setBudgetField.setText("" + totalBudget);
    }

    private void costItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No item selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the current item name and price from the selected row
        double currentPrice = (double) table.getValueAt(selectedRow, 1);

        // Prompt the user for the new price
        String message = JOptionPane.showInputDialog(this, "Enter New Price:", currentPrice);
        if (message == null) {
            return; // User clicked Cancel
        }

        // Convert the new price to a double
        double newPrice;
        try {
            newPrice = Double.parseDouble(message);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Price!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the price in the table
        table.setValueAt(newPrice, selectedRow, 1);

        // Update the total cost
        totalCost -= currentPrice;
        totalCost += newPrice;
        updateTotalCost();
    }

    /**
     * Update the current Cost.
     */
    void updateTotalCost() {

        totalLabel.setText("Current Cost: $" + String.format("%.2f", totalCost));
    }

    /**
     * This to delete item name and price
     */
    private void deleteItem() {
        // Get the selected row index
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No item selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the price of the selected item
        double price = (double) table.getValueAt(selectedRow, 1);

        // Remove the selected row from the table
        model.removeRow(selectedRow);

        // Update the total cost
        totalCost -= price;
        updateTotalCost();
    }

    /**
     * This to change name of an item name
     */
    private void editItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No item selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the current item name and price from the selected row
        String currentItemName = (String) table.getValueAt(selectedRow, 0);


        // Prompt the user for the new item name
        String newItemName = JOptionPane.showInputDialog(this, "Enter New Item Name:", currentItemName);
        if (newItemName == null) {
            return; // User clicked Cancel
        }

        // Update the item name in the table
        table.setValueAt(newItemName, selectedRow, 0);

        // Update the total cost
        updateTotalCost();
    }

    /**
     * This to add item name and price
     */
    private void addItem() {
        // Prompt the user for item name and price
        String itemName = JOptionPane.showInputDialog(this, "Enter Item Name:");
        if (itemName == null) {
            return; // User clicked Cancel
        }

        String priceStr = JOptionPane.showInputDialog(this, "Enter Price:");
        if (priceStr == null) {
            return; // User clicked Cancel
        }

        // Convert the price to a double
        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Price!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add the item to the table
        model.addRow(new Object[]{itemName, price});

        // Update the total cost
        totalCost += price;
        updateTotalCost();
    }

    public void addItemToTable(String name, String price) {

        model.addRow(new Object[]{name, price});
        totalCost += Double.parseDouble(price);
        updateTotalCost();
    }

    public class DocumentCreationPopUp extends JPanel{
        private JTextField documentNameField;
        private JTextField documentDescriptionField;
        private JTextField totalCostField;
        private JButton fileSelectorButton;
        private JButton createButton;
        JPanel contentPanel;

        public DocumentCreationPopUp() {
            // Create components
            contentPanel = new JPanel();
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
                    double totalCosts = Double.parseDouble(totalCostField.getText());
                    // Create the document using the entered values
                    // You can add your own logic here
                    System.out.println("Document created: " + documentName);
                    System.out.println("Description: " + documentDescription);
                    System.out.println("Total cost: " + totalCost);

                    model.addRow(new Object[]{documentName, totalCosts});
                    totalCost += totalCosts;
                    updateTotalCost();

                }
            });

            JDialog dialog = new JDialog();
            dialog.setModal(true);
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
        public String getItemName() {
            return documentNameField.getText();
        }

        public String getItemPrice() {
            return totalCostField.getText();
        }


    }


}
