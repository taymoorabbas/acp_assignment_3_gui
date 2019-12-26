/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 26-Dec-19
Time: 8:07 PM
Lau ji Ghauri aya fir
*/

package view;

import util.IconRenderer;
import view.Interface.DialogListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDialog extends JDialog implements ActionListener {

    private JFrame parent;
    private JButton cancelButton, confirmButton;
    private DialogListener dialogListener;

    public DeleteDialog(JFrame parent){

        super(parent, true);
        this.parent = parent;
        this.setTitle("Delete");

        //message label
        JLabel messageLabel = new JLabel("Delete selected row ?",
                IconRenderer.getIcon("/image/info.png",40),
                SwingConstants.CENTER);
        messageLabel.setFont(new Font("SanSerif", Font.PLAIN, 20));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        messageLabel.setForeground(Color.WHITE);

        //cancel button
        cancelButton = new JButton("Cancel");
        cancelButton .setOpaque(true);
        cancelButton.setBackground(Color.ORANGE);
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setFont(new Font("SanSerif", Font.BOLD, 20));
        cancelButton.addActionListener(this);

        //confirm button
        confirmButton = new JButton("Confirm");
        confirmButton .setOpaque(true);
        confirmButton.setBackground(Color.ORANGE);
        confirmButton.setForeground(Color.BLACK);
        confirmButton.setFont(new Font("SanSerif", Font.BOLD, 20));
        confirmButton.addActionListener(this);

        //message panel
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messagePanel.setBackground(Color.DARK_GRAY);
        messagePanel.add(messageLabel);

        //button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(cancelButton);
        buttonPanel.add(confirmButton);

        //main panel
        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20,20,20,20),
                BorderFactory.createLineBorder(Color.ORANGE, 2)));
        mainPanel.add(messagePanel);
        mainPanel.add(buttonPanel);

        //setup layout
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.setSize(500,300);
        this.setResizable(false);

    }

    public void showDialog(){

        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public void setDialogListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == cancelButton){

            dispose();
        }
        if(e.getSource() == confirmButton){

            if(dialogListener != null){

                dialogListener.onClickConfirm();
                dispose();
            }
        }
    }
}