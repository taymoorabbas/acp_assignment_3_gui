/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 26-Dec-19
Time: 10:20 PM
Lau ji Ghauri aya fir
*/

package model;

import java.util.Comparator;

public class EmployeeIDComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee employee1, Employee employee2) {
        return employee1.getId() - employee2.getId();
    }
}
