package model;

public enum EmployeeType {

    lecturer("Lecturer"),
    securityGuard("Security guard"),
    all("All employees");

    private String text;

    private EmployeeType(String text){

        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
