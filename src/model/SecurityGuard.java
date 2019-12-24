/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 24-Dec-19
Time: 7:18 PM
Lau ji Ghauri aya fir
*/

package model;

public class SecurityGuard extends NonAcademic {

    private int totalHours;

    public SecurityGuard(){}

    public SecurityGuard(int id, String name, int age, float basicSalary, float hourlyRate, int totalHours) {
        super(id, name, age, basicSalary, hourlyRate);
        this.totalHours = totalHours;
    }

    public SecurityGuard(SecurityGuard securityGuard){
        this.totalHours = securityGuard.totalHours;
        this.setId(securityGuard.getId());
        this.setAge(securityGuard.getAge());
        this.setName(securityGuard.getName());
        this.setBasicSalary(securityGuard.getBasicSalary());
        this.setHourlyRate(securityGuard.getHourlyRate());
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    @Override
    public float computeSalary() {
        return this.getBasicSalary() + (this.getHourlyRate() * this.totalHours);
    }

    @Override
    public String toString() {
        return super.toString() + "," + this.totalHours;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this){

            return true;
        }

        if(!(obj instanceof SecurityGuard)){

            return false;
        }

        SecurityGuard securityGuard = (SecurityGuard) obj;
        return super.equals(obj) && securityGuard.totalHours == this.totalHours;
    }
}
