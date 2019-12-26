/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 25-Dec-19
Time: 6:27 PM
Lau ji Ghauri aya fir
*/

package view;

import model.SecurityGuard;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SecurityGuardTableModel extends AbstractTableModel {

    private ArrayList<SecurityGuard> securityGuards;

    private String[] columnNames = {"ID", "Name", "Age", "Basic salary", "Hourly rate", "Total Hours"};

    public SecurityGuardTableModel(){}

    public void setData(ArrayList<SecurityGuard> securityGuards){

        this.securityGuards = securityGuards;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return securityGuards.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return columnIndex != 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        if (securityGuards == null){

            return;
        }

        SecurityGuard securityGuard = securityGuards.get(rowIndex);

        switch (columnIndex){

            case 1:
                securityGuard.setName((String) aValue);
                break;

            case 2:
                securityGuard.setAge((Integer) aValue);
                break;

            case 3:
                securityGuard.setBasicSalary((Float) aValue);
                break;

            case 4:
                securityGuard.setHourlyRate((Float) aValue);
                break;

            case 5:
                securityGuard.setTotalHours((Integer) aValue);
                break;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex){
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return Float.class;
            case 4:
                return Float.class;
            case 5:
                return Integer.class;
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        SecurityGuard securityGuard = securityGuards.get(rowIndex);

        switch (columnIndex){

            case 0:
                return securityGuard.getId();
            case 1:
                return securityGuard.getName();
            case 2:
                return securityGuard.getAge();
            case 3:
                return securityGuard.getBasicSalary();
            case 4:
                return securityGuard.getHourlyRate();
            case 5:
                return securityGuard.getTotalHours();
            default:
                return null;
        }
    }
}
