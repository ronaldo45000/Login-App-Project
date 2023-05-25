package view;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class is to create window for Add Cost only.
 *
 * @author Thinh Le
 * @version 0.1
 */
public class AddCostOnly  {
    JFrame jam;
    JPanel panel;
    JTextField text;
    JTextField text2;


    JLabel itemN;
    JLabel totalBudget;
    JButton setBudget;
    JButton currentCost;
    JButton adddocumentCost;
    JButton addCostOnly;

    /**
     * This is the construct to create Add Cost only window.
     */
    public AddCostOnly() {
        jam = new JFrame();
        panel = new JPanel();


        text2 = new JTextField();
        text = new JTextField();
        totalBudget = new JLabel("Name: ");
        itemN = new JLabel("Cost: ");
        setBudget = new JButton("Confirm");

        currentCost = new JButton("Current Cost");
        adddocumentCost = new JButton("Add document cost");
        addCostOnly = new JButton("Add cost only");


        text.setColumns(8);

        text2.setColumns(8);


        panel.add(totalBudget);
        panel.add(text);

        //panel.add(setBudget);
        panel.add(itemN);
        panel.add(text2);


        jam.add(panel);


        jam.dispose();

        jam.setSize(500, 500);
        jam.setVisible(true);
    }


}

