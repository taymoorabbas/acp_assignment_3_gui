/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 25-Dec-19
Time: 4:51 AM
Lau ji Ghauri aya fir
*/

package model;

import java.util.ArrayList;
import java.util.Collections;

public class EmployeeManager {

    private ArrayList<Lecturer> lecturers;
    private ArrayList<SecurityGuard> securityGuards;

    public EmployeeManager(){

        this.lecturers = new ArrayList<>();
        this.securityGuards = new ArrayList<>();
    }

    public void addLecturer(Lecturer lecturer){

        this.lecturers.add(lecturer);
    }

    public void addSecurityGuard(SecurityGuard securityGuard){

        this.securityGuards.add(securityGuard);
    }

    public void removeLecturer(int id){

        for(int x = 0; x < lecturers.size(); x++){

            if(lecturers.get(x).getId() == id){

                lecturers.remove(x);
                break;
            }
        }
    }
    public void removeSecurityGuard(int id){

        this.securityGuards.remove(id);
    }

    public ArrayList<Lecturer> getLecturers(){

        return this.lecturers;
    }

    public ArrayList<SecurityGuard> getSecurityGuards(){

        return this.securityGuards;
    }
    public void sortLecturersAscending(){

        this.lecturers.sort(new EmployeeIDComparator());
    }
    public void sortLectureresDescending(){

        Collections.reverse(this.lecturers);
    }
    public void sortSecurityGuardsAscending(){

        this.securityGuards.sort(new EmployeeIDComparator());
    }
    public void sortSecurityGuardsDescending(){

        Collections.reverse(this.securityGuards);
    }
    public float calculateLecturerSalary(){

        float salary = 0;
        for(Lecturer lecturer: lecturers){

            salary += (lecturer.getBasicSalary() + (lecturer.getCourseRate() * lecturer.getTotalCourses()));
        }
        return salary;
    }
    public float calculateSecurityGuardsalary(){

        float salary = 0;
        for(SecurityGuard securityGuard: securityGuards){

            salary += (securityGuard.getBasicSalary() + (securityGuard.getHourlyRate() * securityGuard.getTotalHours()));
        }
        return salary;
    }
}
