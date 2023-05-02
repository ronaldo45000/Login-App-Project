package view;

import model.AppInfo;
import model.Developer;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
public class AboutScreen extends JPanel {
    
    private final AppInfo info;

    public AboutScreen(User user, JPanel cardPanel, CardLayout cardLayout) {
        info = new AppInfo(user);

        setLayout(new BorderLayout());
        JLabel aboutLabel = new JLabel("About");
        aboutLabel.setFont(new Font("Arial", Font.BOLD, 30));
        aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(aboutLabel, BorderLayout.NORTH);

        JPanel aboutInfo = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        JLabel versionLabel = new JLabel("App Version " + info.getVersion());
        versionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        cons.anchor = GridBagConstraints.SOUTH;
        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(20, 0, 0, 0);
        aboutInfo.add(versionLabel, cons);

        JLabel userLabel = new JLabel("This app is registered to: " + user.getName() + " (" + user.getEmail() + ")");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        cons.gridy = 1;
        cons.insets = new Insets(50, 0, 0, 0);
        aboutInfo.add(userLabel, cons);

        JLabel devLabel = new JLabel("This app is provided by " + info.getTeamName() + ":");
        devLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        devLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cons.gridy = 2;
        cons.insets = new Insets(40, 0, 0, 0);
        aboutInfo.add(devLabel, cons);

        Iterator<Developer> itr = info.getDevelopers().iterator();
        cons.gridy = 3;
        cons.insets = new Insets(0, 0, 0, 0);
        StringBuilder str = new StringBuilder();
        str.append("<html><ul>");
        while (itr.hasNext()) {
            Developer dev = itr.next();
            str.append("<li>" + dev.getName() + " (" + dev.getEmail() + ")</li>");
        }
        str.append("</ul><html>");
        JLabel devs = new JLabel(str.toString());
        aboutInfo.add(devs, cons);

        cons.gridy = 4;
        cons.weighty = 1;
        cons.insets = new Insets(0, 0, 30, 0);
        JButton backButton = new JButton("Back");
        backButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "HomeScreen");
            }
        }));

        aboutInfo.add(backButton, cons);

        add(aboutInfo, BorderLayout.CENTER);
    }
}
