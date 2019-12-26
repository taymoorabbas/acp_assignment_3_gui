/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 26-Dec-19
Time: 11:18 PM
Lau ji Ghauri aya fir
*/

package view;

import model.EmployeeType;
import util.IconRenderer;
import view.Interface.SalaryDialogListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalaryDialog extends JDialog implements ActionListener {

    private JLabel messageLabel;
    private JComboBox optionsComboBox;
    private JButton calculateButton;
    private JFrame parent;
    private SalaryDialogListener salaryDialogListener;

    public SalaryDialog(JFrame parent){

        super(parent,"Calculate salary", true);
        this.parent = parent;
        this.setSize(500,250);

        //message label
        this.messageLabel = new JLabel("Calculate salary for",
                IconRenderer.getIcon("/image/salary.png", 30), SwingConstants.CENTER);
        this.messageLabel.setFont(new Font("SanSerif", Font.PLAIN, 20));
        this.messageLabel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        this.messageLabel.setForeground(Color.WHITE);

        //combo box
        this.optionsComboBox = new JComboBox(EmployeeType.values());
        this.optionsComboBox.setFont(new Font("SanSerif", Font.PLAIN, 20));
        this.optionsComboBox.setPreferredSize(new Dimension(200, 30));

        //calculate button;
        this.calculateButton = new JButton("Calculate");
        this.calculateButton.setOpaque(true);
        this.calculateButton.setBackground(Color.ORANGE);
        this.calculateButton.setForeground(Color.BLACK);
        this.calculateButton.setFont(new Font("SanSerif", Font.BOLD, 20));
        this.calculateButton.addActionListener(this);

        //message panel
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messagePanel.setBackground(Color.DARK_GRAY);
        messagePanel.add(messageLabel);

        //comboBox panel
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        comboPanel.setBackground(Color.DARK_GRAY);
        comboPanel.add(optionsComboBox);

        //buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(calculateButton);

        //setup layout
        JPanel mainPanel = new JPanel(new GridLayout(3,1));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20,20,20,20),
                BorderFactory.createLineBorder(Color.ORANGE, 2)));
        mainPanel.setBackground(Color.DARK_GRAY);

        mainPanel.add(messagePanel);
        mainPanel.add(comboPanel);
        mainPanel.add(buttonPanel);

        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
    }

    public void setSalaryDialogListener(SalaryDialogListener salaryDialogListener) {
        this.salaryDialogListener = salaryDialogListener;
    }

    public void showDialog(){

        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == calculateButton){

            if(salaryDialogListener != null){

                salaryDialogListener.calculateSalary((EmployeeType) optionsComboBox.getSelectedItem());
                this.dispose();
            }
        }
    }
}
