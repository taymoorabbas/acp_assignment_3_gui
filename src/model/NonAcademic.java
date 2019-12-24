/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 24-Dec-19
Time: 7:18 PM
Lau ji Ghauri aya fir
*/

package model;

public abstract class NonAcademic extends Employee{

    private float hourlyRate;

    public NonAcademic(){}

    public NonAcademic(int id, String name, int age, float basicSalary, float hourlyRate) {
        super(id, name, age, basicSalary);
        this.hourlyRate = hourlyRate;
    }

    public NonAcademic(NonAcademic nonAcademic){

        this.hourlyRate = nonAcademic.hourlyRate;
        this.setId(nonAcademic.getId());
        this.setAge(nonAcademic.getAge());
        this.setName(nonAcademic.getName());
        this.setBasicSalary(nonAcademic.getBasicSalary());
    }

    public float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public String toString() {
        return super.toString() + "," + this.hourlyRate;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this){

            return true;
        }

        if(!(obj instanceof NonAcademic)){
            return false;
        }

        NonAcademic nonAcademic = (NonAcademic) obj;
        return super.equals(obj) && nonAcademic.hourlyRate == this.hourlyRate;
    }
}
