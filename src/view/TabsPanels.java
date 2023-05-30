package view;

import controller.DocumentController;
import model.Document;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.HashMap;

/**
 * @Author  Tin Phu
 * @version 0.1
 */
public class TabsPanels extends JTabbedPane {
    private HashMap<String, Document> listOfDocs;
    public TabsPanels(String theProjectID){
        DocumentController.getDocsByProjectID(theProjectID);

        JPanel panel1 = new JPanel();


        addTab("Document Tab", new DocumentTab(theProjectID));
        JPanel panel2 = new JPanel();

        addTab("Budget Tab", new BudgetTab(theProjectID));

        /**
         * This Change Listener helps Budget Tab and Document Tab
         * get the newest data from json. which fixes  we had with the List of Documents not being synced between the two tabs.
         * @author Tin Phu
         */
        this.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = getSelectedIndex();
                String selectedTabTitle = getTitleAt(selectedIndex);

                if (selectedTabTitle.equals("Budget Tab")) {

                    setComponentAt(1,new BudgetTab(theProjectID) );

                } else if(selectedTabTitle.equals("Document Tab")){
                    setComponentAt(0,new DocumentTab(theProjectID) );
                }
            }
        });

    }

}
