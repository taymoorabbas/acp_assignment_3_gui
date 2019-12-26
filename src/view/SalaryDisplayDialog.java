/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 27-Dec-19
Time: 12:20 AM
Lau ji Ghauri aya fir
*/

package view;

import util.IconRenderer;

import javax.swing.*;
import java.awt.*;

public class SalaryDisplayDialog extends JDialog {

    public SalaryDisplayDialog(JFrame parent, String message, float salary){

        super(parent, "Salary calculation", true);
        this.setSize(500, 250);

        //message label
        JLabel messageLabel = new JLabel(message, IconRenderer.getIcon(
                "/image/salary.png", 20), SwingConstants.CENTER);
        messageLabel.setFont(new Font("SanSerif", Font.PLAIN, 20));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        messageLabel.setForeground(Color.WHITE);

        //salary label
        JLabel salaryLabel = new JLabel("$" + salary, SwingConstants.CENTER);
        salaryLabel.setFont(new Font("SanSerif", Font.PLAIN, 30));
        salaryLabel.setForeground(Color.GREEN);

        //message panel
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messagePanel.setBackground(Color.DARK_GRAY);
        messagePanel.add(messageLabel);

        //salary panel
        JPanel salaryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        salaryPanel.setBackground(Color.DARK_GRAY);
        salaryPanel.add(salaryLabel);

        //main panel
        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20,20,20,20),
                BorderFactory.createLineBorder(Color.ORANGE, 2)));
        mainPanel.add(messagePanel);
        mainPanel.add(salaryLabel);

        //setup layout
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.setLocationRelativeTo(parent);
        this.setFocusableWindowState(true);
        this.setVisible(true);
    }
}
