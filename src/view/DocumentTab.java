package view;

import controller.DocumentController;
import model.Document;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.EventObject;
import java.util.HashMap;

/**
 * DocumentTab for a specific Project.
 * @Author Tin Phu
 * @version 0.1
 */
public class DocumentTab extends JPanel {
    /**
     * List of Docs belong to a specific Project
     */
    private HashMap<String, Document> listOfDocs;
    /**
     * DefaultTableModel model
     */
    private final DefaultTableModel model;
    /**
     * JTable table
     */
    private final JTable table;


    /**
     * DocumentTab set up.
     * @author Tin Phu
     * @param theProjectID
     */
    public DocumentTab(String theProjectID){
        listOfDocs = DocumentController.getDocsByProjectID(theProjectID);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> itemList = new JList<>(listModel);
        setLayout(new BorderLayout());
        // Create the table model
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Description");
        model.addColumn("Date");
        model.addColumn("Total Cost");
        // Create the table and set the model
        table = new JTable(model);


        // Set the cell editor for the locked ID column
        TableColumn lockedColumn = table.getColumnModel().getColumn(0);
        lockedColumn.setCellEditor(new LockedColumnEditor());
        //Set the cell editor for the locked date column
        lockedColumn =  table.getColumnModel().getColumn(3);
        lockedColumn.setCellEditor(new LockedColumnEditor());
        // Set column widths
        TableColumn column = table.getColumnModel().getColumn(2); // Description column
        column.setPreferredWidth(350);
        column = table.getColumnModel().getColumn(0); // Date column
        column.setPreferredWidth(10);
        column = table.getColumnModel().getColumn(3); // Date column
        column.setPreferredWidth(30);
        column = table.getColumnModel().getColumn(4); // Total Cost column
        column.setPreferredWidth(30);


        /**
         * export data when tableChanged
         * @Author Tin Phu
         */
        model.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() >= 0 && e.getFirstRow() >= 0) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    String columnName = model.getColumnName(column);
                    Object newValue = model.getValueAt(row, column);
                    String id = table.getValueAt(row,0 ).toString();
                    String name = model.getValueAt(row, 1).toString();
                    String description = model.getValueAt(row, 2).toString();
                    String totalCost = model.getValueAt(row, 4).toString();
                    listOfDocs.get(id).setTotalCost(BigDecimal.valueOf(Double.valueOf(totalCost)));
                    listOfDocs.get(id).setDocumentName(name);
                    listOfDocs.get(id).setDocumentDescription(description);
                    DocumentController.addDocument(listOfDocs.get(id));
                }
            }
        });





        // Load listOfDocs to the Table.
        listOfDocs.forEach((k, e)->{
            addRow(k, e.getDocumentName(), e.getDocumentDescription(), e.getDate().toString(), e.getTotalCost().toString());
        });
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem readItem = new JMenuItem("Read");
        // Add action listeners to openItem
        openItem.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String id = (String) table.getValueAt(row, 0);
                if(!listOfDocs.get(id).openDoc()){
                    JOptionPane.showMessageDialog(DocumentTab.this, "This document does not have an attached file !");
                };
        }
        });

        readItem.addActionListener(e->{
            int row = table.getSelectedRow();
            if (row >= 0) {
                String id = (String) table.getValueAt(row, 0);
                new DocumentTabRead(listOfDocs.get(id));
            }
        });

        // Add action listeners to deleteItem
        deleteItem.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int result = JOptionPane.showConfirmDialog(DocumentTab.this, "Are you sure you want to Delete the document?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    String name = model.getValueAt(row, 1).toString();
                    System.out.println("Deleting " + name);
                    String id = (String) table.getValueAt(row, 0);
                    DocumentController.deleteADocument(listOfDocs.get(id));
                    listOfDocs.remove(id);
                    updateTable();
                } else {
                    System.out.println("User clicked No");
                    // Add your code for the "No" option here
                }
            }
        });
        // Add the menu items to the popup menu
        popupMenu.add(openItem);
        popupMenu.add(readItem);
        popupMenu.add(deleteItem);
        /**
         * Add a mouse listener to show the popup menu on right-click
         * export data when tableChanged
         * @Author Tin Phu
         */
        table.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < table.getRowCount()) {
                        table.setRowSelectionInterval(row, row);
                        popupMenu.show(table, e.getX(), e.getY());
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        JButton addButton = new JButton("Add Document");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DocumentCreationFormPopUp(theProjectID) ;
            }
        });
        add(addButton, BorderLayout.SOUTH);
    }


    /**
     * add a row to table.
     * @Author Tin Phu
     * @param id
     * @param name
     * @param description
     * @param date
     * @param totalCost
     */
    private void addRow(String id, String name, String description, String date,  String totalCost) {
        Object[] rowData = {id, name, description, date,totalCost};
        model.addRow(rowData);
    }

    /**
     * updateTable with new data.
     * @Author Tin Phu
     */
    public  void updateTable() {
        // Clear existing rows
        model.setRowCount(0);
        // Populate the table with the updated data from the list
        listOfDocs.forEach((k, e)->{
            addRow(k, e.getDocumentName(), e.getDocumentDescription(), e.getDate().toString(), e.getTotalCost().toString());
        });

    }

    /**
     * Table Column Setting.
     * @author Tin Phu
     */
    private class LockedColumnEditor implements TableCellEditor {
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return null; // Return null to make the cell non-editable
        }

        public Object getCellEditorValue() {
            return null;
        }

        public boolean isCellEditable(EventObject anEvent) {
            return false; // Return false to prevent editing
        }

        public boolean shouldSelectCell(EventObject anEvent) {
            return true;
        }

        public boolean stopCellEditing() {
            return true;
        }

        public void cancelCellEditing() {
        }

        public void addCellEditorListener(CellEditorListener l) {
        }

        public void removeCellEditorListener(CellEditorListener l) {
        }
    }
    /**
     * DocumentCreationFormPopUp
     * @author Tin Phu
     *
     */
    public class DocumentCreationFormPopUp extends JPanel{
        private final JTextField documentNameField;
        private final JTextField documentDescriptionField;
        private final JTextField totalCostField;
        private String srcFileString = "";
        private final JDialog dialog = new JDialog();

        /**
         * @Author Tin Phu
         * @param theProjectID
         */
        public DocumentCreationFormPopUp(String theProjectID) {
            // Create components
            JLabel documentNameLabel = new JLabel("Document Name:");
            documentNameField = new JTextField(20);

            JLabel documentDescriptionLabel = new JLabel("Document Description:");
            documentDescriptionField = new JTextField(20);

            JLabel totalCostLabel = new JLabel("Total Cost:");
            totalCostField = new JTextField(10);

            JLabel fileSrcStringLabel = new JLabel("(option)");
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






            //
            /**
             * set fileSelectorButton Action Listener
             * @Author Tin Phu
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
             * Create button eventLisener
             * @Author Tin Phu
             */
            createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String documentName = documentNameField.getText();
                    String documentDescription = documentDescriptionField.getText();
                    Document newDoc = null;
                    if(documentName.isEmpty() || documentDescription.isEmpty() || totalCostField.getText().isEmpty() ){
                        JOptionPane.showMessageDialog(DocumentTab.this, "Please enter required information!");
                    } else if(documentDescription.length() >= 1300){
                        JOptionPane.showMessageDialog(DocumentTab.this, "The Description is too long!");
                    }else {


                        double totalCost = Double.parseDouble(totalCostField.getText());
                        try {
                            if(srcFileString.isEmpty()){
                                newDoc = new Document(documentName,documentDescription,theProjectID,"", BigDecimal.valueOf(totalCost) );

                            }else {
                                newDoc = new Document(documentName,documentDescription,theProjectID,"", BigDecimal.valueOf(totalCost), srcFileString );

                            }
                            DocumentController.addDocument(newDoc);
                            listOfDocs.put(newDoc.id(), newDoc);
                            dialog.setVisible(false);
                            updateTable();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(DocumentTab.this, "Something went wrong! The File could not be coppied");
                        }
                    }

                }
            });
            dialog.setLocationRelativeTo(DocumentTab.this);
            dialog.setContentPane(panel);
            dialog.setTitle("Document Creation Form");
            dialog.setSize(500, 400);
            dialog.setLocationRelativeTo(null); // Center the dialog on the screen
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

        }
    }

}






