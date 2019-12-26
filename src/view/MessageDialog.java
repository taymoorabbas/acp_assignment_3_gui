/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 27-Dec-19
Time: 1:05 AM
Lau ji Ghauri aya fir
*/

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageDialog extends JDialog {

    public MessageDialog(JFrame parent, String title, String message, ImageIcon icon){

        super(parent, title, true);

        //message label
        JLabel messageLabel = new JLabel(message, icon, SwingConstants.CENTER);
        messageLabel.setFont(new Font("SanSerif", Font.PLAIN, 20));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        messageLabel.setForeground(Color.WHITE);

        //ok button
        JButton okButton = new JButton("OK");
        okButton .setOpaque(true);
        okButton.setBackground(Color.ORANGE);
        okButton.setForeground(Color.BLACK);
        okButton.setFont(new Font("SanSerif", Font.BOLD, 20));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });

        //message panel
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messagePanel.setBackground(Color.DARK_GRAY);
        messagePanel.add(messageLabel);

        //button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(okButton);

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

        this.setSize(500,250);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);
        this.setVisible(true);
    }
}
