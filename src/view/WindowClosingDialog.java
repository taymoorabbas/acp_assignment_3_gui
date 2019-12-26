/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 27-Dec-19
Time: 2:23 AM
Lau ji Ghauri aya fir
*/

package view;

import util.IconRenderer;
import view.Interface.DialogListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowClosingDialog extends JDialog implements ActionListener {

    private JFrame parent;
    private JButton noButton, yesButton;
    private DialogListener dialogListener;

    public WindowClosingDialog(JFrame parent){

        super(parent, true);
        this.parent = parent;
        this.setTitle("Delete");

        //message label
        JLabel messageLabel = new JLabel("Save changes before exit ?",
                IconRenderer.getIcon("/image/info.png",40),
                SwingConstants.CENTER);
        messageLabel.setFont(new Font("SanSerif", Font.PLAIN, 20));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        messageLabel.setForeground(Color.WHITE);

        //no button
        noButton = new JButton("No");
        noButton.setOpaque(true);
        noButton.setBackground(Color.ORANGE);
        noButton.setForeground(Color.BLACK);
        noButton.setFont(new Font("SanSerif", Font.BOLD, 20));
        noButton.addActionListener(this);

        //yes button
        yesButton = new JButton("Yes");
        yesButton.setOpaque(true);
        yesButton.setBackground(Color.ORANGE);
        yesButton.setForeground(Color.BLACK);
        yesButton.setFont(new Font("SanSerif", Font.BOLD, 20));
        yesButton.addActionListener(this);

        //message panel
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messagePanel.setBackground(Color.DARK_GRAY);
        messagePanel.add(messageLabel);

        //button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(noButton);
        buttonPanel.add(yesButton);

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

        if(e.getSource() == noButton){

            dispose();
        }
        if(e.getSource() == yesButton){

            if(dialogListener != null){

                dialogListener.onClickConfirm();
                dispose();
            }
        }
    }
}