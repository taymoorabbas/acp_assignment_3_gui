/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 25-Dec-19
Time: 4:40 AM
Lau ji Ghauri aya fir
*/

package controller;

import model.EmployeeManager;
import model.Lecturer;
import model.SecurityGuard;
import model.database.DatabaseConnector;
import model.database.DatabaseConstants;
import model.database.EmployeeDatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    private EmployeeDatabaseManager employeeDatabaseManager;
    private EmployeeManager employeeManager;

    public Controller(){

        employeeDatabaseManager = new EmployeeDatabaseManager();
        employeeManager = new EmployeeManager();
    }

    public void connectToDatabase() throws SQLException, ClassNotFoundException {

        DatabaseConnector.connect(DatabaseConstants.databasePath);
    }

    public void disconnectFromDatabase() throws SQLException {

        DatabaseConnector.disconnect();
    }

    public void exportToDatabase(){

        for(Lecturer lecturer: getLecturers()){

            employeeDatabaseManager.addLecturer(lecturer);
        }

        for(SecurityGuard securityGuard: getSecurityGuards()){

            employeeDatabaseManager.addSecurityGuard(securityGuard);
        }
    }

    public void importFromDatabase(){

        getLecturers().clear();
        getSecurityGuards().clear();

        for(Lecturer lecturer: employeeDatabaseManager.getAllLecturer()){

            getLecturers().add(lecturer);
        }
        for(SecurityGuard securityGuard: employeeDatabaseManager.getAllSecurityGuard()){

            getSecurityGuards().add(securityGuard);
        }
    }
    public void addLecturer(Lecturer lecturer){

        employeeManager.addLecturer(lecturer);
    }
    public void addSecurityGuard(SecurityGuard securityGuard){

        employeeManager.addSecurityGuard(securityGuard);
    }
    public void removeLecturer(int id){

        employeeManager.removeLecturer(id);
        employeeDatabaseManager.deleteEmployee(id);
    }
    public void removeSecurityGuard(int id){

        employeeManager.removeSecurityGuard(id);
        employeeDatabaseManager.deleteEmployee(id);
    }
    public ArrayList<Lecturer> getLecturers(){

        return employeeManager.getLecturers();
    }
    public void sortLecturers(int mode){

        //0 --> ASC 1 --> DSC
        if(mode == 0){

            employeeManager.sortLecturersAscending();
        }
        else{

            employeeManager.sortLectureresDescending();
        }
    }
    public void sortSecurityGuards(int mode){

        //0 --> ASC 1 --> DSC
        if(mode == 0){

            employeeManager.sortSecurityGuardsAscending();
        }
        else{

            employeeManager.sortSecurityGuardsDescending();
        }
    }
    public ArrayList<SecurityGuard> getSecurityGuards(){

        return employeeManager.getSecurityGuards();
    }

    public float calculateLecturerSalary(){

        return employeeManager.calculateLecturerSalary();
    }

    public float calculateSecurityGuardSalary(){

        return employeeManager.calculateSecurityGuardsalary();
    }
}
