package view;

import controller.DocumentController;
import model.Document;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.EventObject;
import java.util.HashMap;

public class DocumentTab extends JPanel {
    private HashMap<String, Document> listOfDocs;
    private DefaultListModel<String> listModel;
    private JList<String> itemList;
    private DefaultTableModel model;

    private JTable table;


    public DocumentTab(String theProjectID){
        listOfDocs = DocumentController.getDocsByProjectID(theProjectID);

//        Document d1 = (new Document("Doc2", "Description2", "theProejectId", "UseID", BigDecimal.valueOf(222)));
//        Document d2 =(new Document("Doc1", "Description1", "theProejectId", "UseID", BigDecimal.valueOf(111)));
//        Document d3 =(new Document("Doc3", "Description3", "theProejectId", "UseID", BigDecimal.valueOf(333)));
//
//        listOfDocs.put(d1.getId(),d1);
//        listOfDocs.put(d2.getId(),d2);
//        listOfDocs.put(d3.getId(),d3);


        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
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


        // Set the cell editor for the locked colum
        //to ID
        TableColumn lockedColumn = table.getColumnModel().getColumn(0);
        lockedColumn.setCellEditor(new LockedColumnEditor());
        //to Date
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

        ListSelectionModel selectionModel = table.getSelectionModel();
//        selectionModel.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if (e.getValueIsAdjusting()) {
//                    int selectedRow = table.getSelectedRow();
//                    if (selectedRow != -1) {
//
//                        String id = (String) table.getValueAt(selectedRow, 0);
//                        String name = (String) table.getValueAt(selectedRow, 1);
//                        String description = (String) table.getValueAt(selectedRow, 2);
//                        String date = (String) table.getValueAt(selectedRow, 3);
//                        String totalCost = (String) table.getValueAt(selectedRow, 4);
//
//                        ListOfDocs.get(id).setTotalCost(BigDecimal.valueOf(Double.valueOf(totalCost)));
//                        ListOfDocs.get(id).setDocumentName(name);
//                        ListOfDocs.get(id).setDocumentDescription(description);
//                        System.out.println("Changing:");
//                        System.out.println(ListOfDocs.get(id));
//                    }
////                System.out.println("////////////////////////////////////////");
////                    System.out.println(ListOfDocs);
//
//                }
//            }
//        });

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


                    System.out.println("Value changed at row " + row + ", column " + columnName + ": " + newValue);
                    System.out.println(listOfDocs.get(id));
                }
            }
        });

        // Add some example data to the table





        listOfDocs.forEach((k, e)->{
            addRow(k, e.getDocumentName(), e.getDocumentDescription(), e.getDate().toString(), e.getTotalCost().toString());
        });


        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem deleteItem = new JMenuItem("Delete");

        // Add action listeners to the menu items
        openItem.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String id = (String) table.getValueAt(row, 0);
                listOfDocs.get(id).openDoc();

        }
        });

        deleteItem.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int result = JOptionPane.showConfirmDialog(DocumentTab.this, "Are you sure you want to Delete the document?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    String name = model.getValueAt(row, 1).toString();
                    System.out.println("Deleting " + name);
                    String id = (String) table.getValueAt(row, 0);
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
        popupMenu.add(deleteItem);

        // Add a mouse listener to show the popup menu on right-click

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



    private void addRow(String id, String name, String description, String date,  String totalCost) {
        Object[] rowData = {id, name, description, date,totalCost};
        model.addRow(rowData);
    }

    public  void updateTable() {
        // Clear existing rows
        model.setRowCount(0);



        // Populate the table with the updated data from the list
        listOfDocs.forEach((k, e)->{
            addRow(k, e.getDocumentName(), e.getDocumentDescription(), e.getDate().toString(), e.getTotalCost().toString());
        });

    }

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
        private JTextField documentNameField;
        private JTextField documentDescriptionField;
        private JTextField totalCostField;
        private String srcFileString;
        private JButton fileSelectorButton;
        private JButton createButton;
        private JDialog dialog = new JDialog();
        private Document newDoc = null;


        public DocumentCreationFormPopUp(String theProjectID) {
            // Create components
            JLabel documentNameLabel = new JLabel("Document Name:");
            documentNameField = new JTextField(20);

            JLabel documentDescriptionLabel = new JLabel("Document Description:");
            documentDescriptionField = new JTextField(20);

            JLabel totalCostLabel = new JLabel("Total Cost:");
            totalCostField = new JTextField(10);

            JLabel fileSrcStringLabel = new JLabel("");
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
            panel.add(fileSrcStringLabel, constraints);
            constraints.gridx = 1;
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
                    double totalCost = Double.parseDouble(totalCostField.getText());
                    try {
                        Document newDoc = new Document(documentName,documentDescription,theProjectID,"", BigDecimal.valueOf(totalCost), srcFileString );
                        DocumentController.addDocument(newDoc);
                        listOfDocs.put(newDoc.id(), newDoc);
                        dialog.setVisible(false);

                        updateTable();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(DocumentTab.this, "Something went wrong! The File could not be coppied");
                    }
                }
            });

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



}






