/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 24-Dec-19
Time: 10:22 PM
Lau ji Ghauri aya fir
*/

package view;

import util.IconRenderer;
import view.Interface.ToolbarListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ToolbarPanel extends JPanel implements ActionListener {

    private JButton exportButton;
    private JButton importButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton salaryButton;
    private JButton sortButton;
    private JButton searchButton;
    private JTextField searchBar;
    private JLabel searchLabel;
    private JPanel buttonsPanel;
    private JPanel searchPanel;

    private int iconSize = 40;
    private ToolbarListener toolbarListener;

    //to control sorts
    private static final int ASC = 0;
    private static final int DSC = 1;
    private int sortMode = ASC;

    public ToolbarPanel(){

        //export button
        this.exportButton = new JButton(IconRenderer.getIcon("/image/export.png", iconSize));
        this.exportButton.setToolTipText("Save to database");
        this.exportButton.addActionListener(this);
        this.exportButton.setBorderPainted(false);
        this.exportButton.setContentAreaFilled(false);

        //import button
        this.importButton = new JButton(IconRenderer.getIcon("/image/import.png", iconSize));
        this.importButton.setToolTipText("Load from database");
        this.importButton.addActionListener(this);
        this.importButton.setBorderPainted(false);
        this.importButton.setContentAreaFilled(false);

        //add button
        this.insertButton = new JButton(IconRenderer.getIcon("/image/insert.png", iconSize));
        this.insertButton.setToolTipText("Add new record");
        this.insertButton.addActionListener(this);
        this.insertButton.setBorderPainted(false);
        this.insertButton.setContentAreaFilled(false);

        //delete button
        this.deleteButton = new JButton(IconRenderer.getIcon("/image/delete.png", iconSize));
        this.deleteButton.setToolTipText("Delete row(s)");
        this.deleteButton.addActionListener(this);
        this.deleteButton.setBorderPainted(false);
        this.deleteButton.setContentAreaFilled(false);

        //salary button
        this.salaryButton = new JButton(IconRenderer.getIcon("/image/salary.png", iconSize));
        this.salaryButton.setToolTipText("Calculate salaries");
        this.salaryButton.addActionListener(this);
        this.salaryButton.setBorderPainted(false);
        this.salaryButton.setContentAreaFilled(false);

        //sort button
        this.sortButton = new JButton(IconRenderer.getIcon("/image/sort.png", iconSize));
        this.sortButton.setToolTipText("Sort by ID");
        this.sortButton.addActionListener(this);
        this.sortButton.setBorderPainted(false);
        this.sortButton.setContentAreaFilled(false);

        //search button
        this.searchButton = new JButton(IconRenderer.getIcon("/image/search.png", iconSize));
        this.searchButton.setToolTipText("Search employee");
        this.searchButton.addActionListener(this);
        this.searchButton.setBorderPainted(false);
        this.searchButton.setContentAreaFilled(false);

        //search bar with formatted data type
        this.searchBar = new JTextField(10);
        this.searchBar.setFont(new Font("SansSerif", Font.PLAIN|Font.BOLD, 28));
        this.searchBar.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
                if(c == KeyEvent.VK_ENTER && searchBar.getText().length() > 0){

                    toolbarListener.onClickSearch(Integer.parseInt(searchBar.getText().trim()));
                }
            }
        });

        //search label
        this.searchLabel = new JLabel("Search by ID  ");
        this.searchLabel.setFont(new Font("SansSerif", Font.ITALIC|Font.BOLD, 18));
        this.searchLabel.setForeground(Color.WHITE);

        //buttons panel
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        buttonsPanel.add(exportButton);
        buttonsPanel.add(importButton);
        buttonsPanel.add(insertButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(salaryButton);
        buttonsPanel.add(sortButton);

        //search panel
        searchPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        searchPanel.add(searchLabel);
        searchPanel.add(searchBar);
        searchPanel.add(searchButton);

        //layout setup
        this.setLayout(new BorderLayout());
        this.add(buttonsPanel, BorderLayout.WEST);
        this.add(searchPanel, BorderLayout.EAST);
        setTheme(Color.BLACK, Color.ORANGE);
    }

    public void setTheme(Color background, Color border){

        this.setBackground(background);
        buttonsPanel.setBackground(background);
        searchPanel.setBackground(background);

        this.setBorder(BorderFactory.
                createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),
                        BorderFactory.createLineBorder(border, 2, false)));
    }

    public void setToolbarListener(ToolbarListener toolbarListener) {
        this.toolbarListener = toolbarListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(toolbarListener != null){

            if(e.getSource() == exportButton){

                toolbarListener.onClickExport();
            }
            if(e.getSource() == importButton){

                toolbarListener.onClickImport();
            }
            if(e.getSource() == insertButton){

                toolbarListener.onClickInsert();
            }
            if(e.getSource() == deleteButton){

                toolbarListener.onClickDelete();
            }
            if(e.getSource() == salaryButton){

                toolbarListener.onClickCalculate();
            }
            if(e.getSource() == sortButton){

                if(sortMode == ASC){

                    sortMode = DSC;
                }
                else{

                    sortMode = ASC;
                }

                toolbarListener.onClickSort(sortMode);
            }
            if(e.getSource() == searchButton){

                if(!searchBar.getText().trim().equals("")){

                    toolbarListener.onClickSearch(Integer.
                            parseInt(searchBar.getText().trim()));
                }
            }
        }
    }
}
