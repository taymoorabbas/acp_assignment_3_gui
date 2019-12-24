/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 24-Dec-19
Time: 7:11 PM
Lau ji Ghauri aya fir
*/

import model.Lecturer;
import model.database.DatabaseConnector;
import model.database.DatabaseConstants;
import model.database.EmployeeDatabaseManager;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        DatabaseConnector.connect(DatabaseConstants.databasePath);

        ArrayList<Lecturer> lecturers = EmployeeDatabaseManager.getAllLecturer();

        if (lecturers != null){

            for(Lecturer lecturer : lecturers){

                System.out.println(lecturer.toString());
            }
        }
    }
}
