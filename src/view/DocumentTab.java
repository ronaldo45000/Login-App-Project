package view;

import model.Document;
import repository.DocumentRepository;

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
    private HashMap<String, Document> ListOfDocs;
    private DefaultListModel<String> listModel;
    private JList<String> itemList;
    private DefaultTableModel model;

    private JTable table;


    public DocumentTab(){
        ListOfDocs = new HashMap<String, Document>();

        Document d1 = (new Document("Doc2", "Description2", "theProejectId", "UseID", BigDecimal.valueOf(222)));
        Document d2 =(new Document("Doc1", "Description1", "theProejectId", "UseID", BigDecimal.valueOf(111)));
        Document d3 =(new Document("Doc3", "Description3", "theProejectId", "UseID", BigDecimal.valueOf(333)));

        ListOfDocs.put(d1.getId(),d1);
        ListOfDocs.put(d2.getId(),d2);
        ListOfDocs.put(d3.getId(),d3);


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
                    ListOfDocs.get(id).setTotalCost(BigDecimal.valueOf(Double.valueOf(totalCost)));
                    ListOfDocs.get(id).setDocumentName(name);
                    ListOfDocs.get(id).setDocumentDescription(description);


                    System.out.println("Value changed at row " + row + ", column " + columnName + ": " + newValue);
                    System.out.println(ListOfDocs.get(id));
                }
            }
        });

        // Add some example data to the table





        ListOfDocs.forEach((k,e)->{
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
                String name = (String) model.getValueAt(row, 1);
                String description = (String) model.getValueAt(row, 2);
                System.out.println("Open item: " + ListOfDocs.get(id).getDocumentName() + ", " + ListOfDocs.get(id).getDocumentDescription());
                String currentPath = System.getProperty("user.dir");
                System.out.println("Current path: " + currentPath);

                File file = new File(currentPath + "\\AppInfo.json");
                Desktop desktop = Desktop.getDesktop();
                if(file.exists())         //checks file exists or not
                {
                    try {
                        desktop.open(file);              //opens the specified file
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

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
                    ListOfDocs.remove(id);
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


                new DocumentCreationFormPopUp() ;

            }
        });


        add(addButton, BorderLayout.SOUTH);
    }



    private void addRow(String id, String name, String description, String date,  String totalCost) {
        Object[] rowData = {id, name, description, date,totalCost};
        model.addRow(rowData);
    }

    public void updateTable() {
        // Clear existing rows
        model.setRowCount(0);



        // Populate the table with the updated data from the list
        ListOfDocs.forEach((k,e)->{
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

    }

