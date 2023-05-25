package view;

import javax.swing.*;
import java.awt.*;

/**
 * @Author  Tin Phu
 * @version 0.1
 */
public class TabsPanels extends JTabbedPane {
    public TabsPanels(String theProjectID){
        JPanel panel1 = new JPanel();

        addTab("Document Tab", new DocumentTab(theProjectID));
        JPanel panel2 = new JPanel();

        addTab("Budget Tab", new BudgetTab());

    }

}
