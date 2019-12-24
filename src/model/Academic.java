/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 24-Dec-19
Time: 7:17 PM
Lau ji Ghauri aya fir
*/

package model;

public abstract class Academic extends Employee {

    private float courseRate;

    public Academic(){}

    public Academic(int id, String name, int age, float basicSalary, float courseRate) {
        super(id, name, age, basicSalary);
        this.courseRate = courseRate;
    }

    public Academic(Academic academic){
        this.courseRate = academic.courseRate;
        this.setId(academic.getId());
        this.setAge(academic.getAge());
        this.setName(academic.getName());
        this.setBasicSalary(academic.getBasicSalary());
    }

    public float getCourseRate() {
        return courseRate;
    }

    public void setCourseRate(float courseRate) {
        this.courseRate = courseRate;
    }

    @Override
    public String toString() {
        return super.toString() + "," + this.courseRate;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this){

            return true;
        }

        if(!(obj instanceof  Academic)){

            return false;
        }

        Academic academic = (Academic) obj;
        return super.equals(obj) && academic.courseRate == this.courseRate;
    }
}
