/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 24-Dec-19
Time: 7:18 PM
Lau ji Ghauri aya fir
*/

package model;

public class Lecturer extends Academic{

    private int totalCourses;

    public Lecturer(){}

    public Lecturer(int id, String name, int age, float basicSalary, float courseRate, int totalCourses) {
        super(id, name, age, basicSalary, courseRate);
        this.totalCourses = totalCourses;
    }

    public Lecturer(Lecturer lecturer) {
        this.totalCourses = lecturer.totalCourses;
        this.setId(lecturer.getId());
        this.setAge(lecturer.getAge());
        this.setName(lecturer.getName());
        this.setBasicSalary(lecturer.getBasicSalary());
        this.setCourseRate(lecturer.getCourseRate());
    }

    public int getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(int totalCourses) {
        this.totalCourses = totalCourses;
    }

    @Override
    public float computeSalary() {
        return this.getBasicSalary() + (this.getCourseRate() * this.totalCourses);
    }

    @Override
    public String toString() {
        return super.toString() + "," + this.totalCourses;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this){

            return true;
        }

        if(!(obj instanceof Lecturer)){

            return false;
        }

        Lecturer lecturer = (Lecturer) obj;
        return super.equals(obj) && lecturer.totalCourses == this.totalCourses;
    }
}
