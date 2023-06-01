package view;

import controller.DocumentController;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Creates tabs for documents and budget screens.
 * @Author Tin Phu
 * @version 0.3
 */
public class TabsPanels extends JTabbedPane {

    public TabsPanels(final String theProjectID){
        DocumentController.getDocsByProjectID(theProjectID);

        addTab("Document Tab", new JScrollPane(new DocumentTab(theProjectID)));

        addTab("Budget Tab", new JScrollPane(new BudgetTab(theProjectID)));

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

                    setComponentAt(1,new JScrollPane(new BudgetTab(theProjectID)) );

                } else if(selectedTabTitle.equals("Document Tab")){
                    setComponentAt(0,new JScrollPane(new DocumentTab(theProjectID)) );
                }
            }
        });

    }

}
