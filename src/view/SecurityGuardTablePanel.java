/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 25-Dec-19
Time: 5:36 AM
Lau ji Ghauri aya fir
*/

package view;

import model.SecurityGuard;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class SecurityGuardTablePanel extends JPanel {

    private JTable table;
    private SecurityGuardTableModel tableModel;

    public SecurityGuardTablePanel(){

        this.tableModel = new SecurityGuardTableModel();

        //table setup
        setupTable();

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(this.table), BorderLayout.CENTER);
    }

    private void setupTable(){

        this.table = new JTable(tableModel);

        //miscellaneous
        this.table.setFont(new Font("SanSerif", Font.ITALIC|Font.BOLD, 20));
        this.table.setRowHeight(40);
        this.table.setGridColor(Color.BLACK);
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        //custom table header renderer
        CustomHeaderRenderer customHeaderRenderer = new CustomHeaderRenderer();
        this.table.getTableHeader().setDefaultRenderer(customHeaderRenderer);

        //table columns size
        this.table.getColumnModel().getColumn(0).setPreferredWidth(3);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(120);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(10);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(40);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(40);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(40);

        //custom column renderer
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        renderer.setBackground(Color.WHITE);
        renderer.setForeground(Color.BLACK);
        for(int x = 0; x < 6; x++){

            table.getColumnModel().getColumn(x).setCellRenderer(renderer);
        }

        //custom cell editor (string)
        CustomCellEditor stringEditor = new CustomCellEditor("string");
        table.getColumnModel().getColumn(1).setCellEditor(stringEditor);

        //custom cell editor (float)
        CustomCellEditor floatEditor = new CustomCellEditor("float");
        table.getColumnModel().getColumn(3).setCellEditor(floatEditor);
        table.getColumnModel().getColumn(4).setCellEditor(floatEditor);

        //custom cell editor (int)
        CustomCellEditor intEditor = new CustomCellEditor("int");
        table.getColumnModel().getColumn(2).setCellEditor(intEditor);
        table.getColumnModel().getColumn(5).setCellEditor(intEditor);
    }

    public void setData(ArrayList<SecurityGuard> securityGuards){

        tableModel.setData(securityGuards);
    }

    public void refresh(){

        this.tableModel.fireTableDataChanged();
    }
    public int getSelectedRow(){

        return table.getSelectedRow();
    }
    public void setSelectedRow(int row){

        table.getSelectionModel().setSelectionInterval(row, row);
        table.scrollRectToVisible(new Rectangle(table.getCellRect(row, 0, true)));
    }
    public int getValue(int row){

        return (int)tableModel.getValueAt(row,0);
    }
}

