package view;

import controller.AppInfoController;
import controller.ProjectController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Project;
import model.User;

/**
 * A class to create the Home screen of the FileNtro project.
 * Tin Phu: "Please pass ProjectID to ProjectScreen() at line 46"
 * @author Riley Bennett
 * @version 0.2
 */
public class HomeScreen extends JPanel {
    /** Label for the welcome 'username'.*/
    JLabel homeLabel;
    private DefaultTableModel projectTableModel;
    private JTable projectTable;
    private JButton openButton;
    private JButton deleteButton;
    private JButton logoutButton;
    private JButton createButton;


    /**
     * Constructor to create the Home screen.
     * @author Riley Bennett
     * @param user The user of the app
     * @param cardPanel The panels to swap to/from
     * @param cardLayout The layout used to swap to/from panels
     */
    public HomeScreen(User user, JPanel cardPanel, CardLayout cardLayout) {
   
        HashMap<String, Project> listOfProjects = new HashMap<String, Project>();

//        listOfProjects = ProjectController.getProjectsByUserID(user.());
        System.out.println("the user.getName " + user.getName());
        System.out.println("the user.getID " + user.getId());
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the project table model and table
        projectTableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Date"}, 0);
        projectTable = new JTable(projectTableModel);
        projectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        projectTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

        //Load List to table here
        setProjects(listOfProjects);
        //setSize
        TableColumnModel columnModel = projectTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(projectTable);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        add(scrollPane, BorderLayout.CENTER);

        openButton = new JButton("Open Project");
        createButton = new JButton("Create");
        deleteButton = new JButton("Delete");
        logoutButton = new JButton("Log Out");

        Dimension buttonSize = new Dimension(120, 30);
        openButton.setPreferredSize(buttonSize);
        createButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(buttonSize);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.add(openButton);
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutButton);

        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(welcomeLabel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        HomeScreen home = this;
        // Event listeners for the buttons
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//"35089418-d50b-4fc8-88ea-bb82c1ea5633"
                cardPanel.add(new ProjectScreen(user, cardPanel, cardLayout,  "35089418-d50b-4fc8-88ea-bb82c1ea5633"), "ProjectScreen");
                cardLayout.show(cardPanel, "ProjectScreen");
                int selectedRow = projectTable.getSelectedRow();
                if (selectedRow != -1) {
                    String projectID = projectTable.getValueAt(selectedRow, 0).toString();
                    System.out.println("Opening project ID: " + projectID);
                } else {
                    System.out.println("No project selected.");
                }
            }
        });

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Creating new project...");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = projectTable.getSelectedRow();
                if (selectedRow != -1) {
                    String projectID = projectTable.getValueAt(selectedRow, 0).toString();
                    System.out.println("Deleting project ID: " + projectID);
                } else {
                    System.out.println("No project selected.");
                }
            }
        });

       // JButton logoutButton = new JButton("Log Out");
      logoutButton.addActionListener((new ActionListener() {
          public void actionPerformed(ActionEvent e) {

              cardLayout.show(cardPanel, "LogInScreen");
              AppInfoController.logout();
          }
      }));
    }
    public void setProjects(HashMap<String, Project> map) {
        projectTableModel.setRowCount(0);

        for (Project project : map.values()) {
            Object[] rowData = {project.getId(), project.getProjectName(), project.getDate()};
            projectTableModel.addRow(rowData);
        }
    }

    /**
     * Updates the welcome label whenever the username is changed.
     * @author Bairu Li
     */
    public void updateWelcomeName() {
        homeLabel.setText("Welcome " + AppInfoController.getCurrentUser().getName() + "!");
    }
}
