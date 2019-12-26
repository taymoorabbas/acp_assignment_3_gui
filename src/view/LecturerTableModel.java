/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 25-Dec-19
Time: 4:39 AM
Lau ji Ghauri aya fir
*/

package view;

import model.Lecturer;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class LecturerTableModel extends AbstractTableModel {

    private ArrayList<Lecturer> lecturers;

    private String[] columnNames = {"ID", "Name", "Age", "Basic salary", "Course rate", "Total courses"};

    public LecturerTableModel(){}

    public void setData(ArrayList<Lecturer> lecturers){

        this.lecturers = lecturers;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return lecturers.size();
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

        if (lecturers == null){

            return;
        }

        Lecturer lecturer = lecturers.get(rowIndex);

        switch (columnIndex){

            case 1:
                lecturer.setName((String) aValue);
                break;

            case 2:
                lecturer.setAge((Integer) aValue);
                break;

            case 3:
                lecturer.setBasicSalary((Float) aValue);
                break;

            case 4:
                lecturer.setCourseRate((Float) aValue);
                break;

            case 5:
                lecturer.setTotalCourses((Integer) aValue);
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

        Lecturer lecturer = lecturers.get(rowIndex);

        switch (columnIndex){

            case 0:
                return lecturer.getId();
            case 1:
                return lecturer.getName();
            case 2:
                return lecturer.getAge();
            case 3:
                return lecturer.getBasicSalary();
            case 4:
                return lecturer.getCourseRate();
            case 5:
                return lecturer.getTotalCourses();
                default:
                    return null;
        }
    }
}
