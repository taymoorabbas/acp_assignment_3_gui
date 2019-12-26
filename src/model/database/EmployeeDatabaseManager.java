/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 24-Dec-19
Time: 8:05 PM
Lau ji Ghauri aya fir
*/
package model.database;

import model.Employee;
import model.Lecturer;
import model.SecurityGuard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static model.database.DatabaseConstants.*;

public class EmployeeDatabaseManager {

    private PreparedStatement statement;
    private ResultSet resultSet;
    private String query;

    /******************************************EMPLOYEE QUERIES********************************************************/

    private void insertEmployee(int id, String name, int age, float salary){

        query = "INSERT INTO " + employeeTableLabel + " VALUES (?, ?, ?, ?)";

        try {
            statement = DatabaseConnector
                    .getConnection()
                    .prepareStatement(query);

            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, age);
            statement.setFloat(4, salary);

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                statement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Employee getEmployee(int employeeID){

        Lecturer lecturer = getLecturer(employeeID);

        if(lecturer != null) {

            query = "SELECT * FROM " + academicTableLabel + " WHERE " + academicIdLabel + " = ?";

            try {
                statement = DatabaseConnector.getConnection().prepareStatement(query);
                statement.setInt(1, employeeID);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {

                    lecturer.setCourseRate(resultSet.getFloat(courseRateLabel));

                    query = "SELECT * FROM " + employeeTableLabel + " WHERE "+ employeeIdLabel + " = ?";

                    statement = DatabaseConnector.getConnection().prepareStatement(query);
                    statement.setInt(1, employeeID);
                    resultSet = statement.executeQuery();

                    if (resultSet.next()) {

                        lecturer.setName(resultSet.getString(employeeNameLabel));
                        lecturer.setAge(resultSet.getInt(employeeAgeLabel));
                        lecturer.setBasicSalary(resultSet.getFloat(employeeBasicSalaryLabel));

                        return lecturer;
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (statement != null && resultSet != null) {
                        statement.close();
                        resultSet.close();
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        SecurityGuard securityGuard = getSecurityGuard(employeeID);

        if(securityGuard != null){

            query = "SELECT * FROM " + nonAcademicTableLabel + " WHERE " + nonAcademicIdLabel + " = ?";

            try {
                statement = DatabaseConnector.getConnection().prepareStatement(query);
                statement.setInt(1, employeeID);
                resultSet = statement.executeQuery();

                if(resultSet.next()){

                    securityGuard.setHourlyRate(resultSet.getFloat(hourlyRateLabel));

                    query = "SELECT * FROM " + employeeTableLabel + " WHERE " + employeeIdLabel + " = ?";
                    statement = DatabaseConnector.getConnection().prepareStatement(query);
                    statement.setInt(1, employeeID);
                    resultSet = statement.executeQuery();

                    if(resultSet.next()){

                        securityGuard.setName(resultSet.getString(employeeNameLabel));
                        securityGuard.setAge(resultSet.getInt(employeeAgeLabel));
                        securityGuard.setBasicSalary(resultSet.getFloat(employeeBasicSalaryLabel));

                        return securityGuard;
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (statement != null && resultSet != null) {
                        statement.close();
                        resultSet.close();
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void updateEmployee(int employeeID, Employee updatedEmployee){

        if(updatedEmployee instanceof  Lecturer){

            Lecturer updatedLecturer = (Lecturer) updatedEmployee;

            query = "UPDATE " + lecturerTableLabel + " SET " +
                    totalCoursesLabel + " = ? WHERE " + lecturerIdLabel + " = ?";

            try {
                statement = DatabaseConnector.getConnection().prepareStatement(query);
                statement.setInt(1, updatedLecturer.getTotalCourses());
                statement.setInt(2, employeeID);

                if(statement.executeUpdate() != 0){

                    query = "UPDATE " + academicTableLabel + " SET " +
                            courseRateLabel + " = ? WHERE " + academicIdLabel + " = ?";

                    statement = DatabaseConnector.getConnection().prepareStatement(query);
                    statement.setFloat(1, updatedLecturer.getCourseRate());
                    statement.setInt(2, employeeID);

                    if(statement.executeUpdate() != 0){

                        query = "UPDATE " + employeeTableLabel + " SET " + employeeNameLabel + " = ?, "
                                + employeeAgeLabel + " = ?, " + employeeBasicSalaryLabel
                                + " = ? WHERE " + employeeIdLabel + " = ?";

                        statement = DatabaseConnector.getConnection().prepareStatement(query);
                        statement.setString(1, updatedLecturer.getName());
                        statement.setInt(2, updatedLecturer.getAge());
                        statement.setFloat(3, updatedLecturer.getBasicSalary());
                        statement.setInt(4,employeeID);

                        statement.executeUpdate();
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else {

            SecurityGuard updatedSecurityGuard = (SecurityGuard) updatedEmployee;

            query = "UPDATE " + securityGuardTableLabel + " SET " +
                    totalHoursLabel + " = ? WHERE " + securityGuardIDLabel + " = ?";

            try {
                statement = DatabaseConnector.getConnection().prepareStatement(query);
                statement.setInt(1, updatedSecurityGuard.getTotalHours());
                statement.setInt(2, employeeID);

                if(statement.executeUpdate() != 0){

                    query = "UPDATE " + nonAcademicTableLabel + " SET "
                            + hourlyRateLabel + " = ? WHERE non_academic_id = ?";

                    statement = DatabaseConnector.getConnection().prepareStatement(query);
                    statement.setFloat(1, updatedSecurityGuard.getHourlyRate());
                    statement.setInt(2, employeeID);

                    if(statement.executeUpdate() != 0){

                        String query3 = "UPDATE " + employeeTableLabel + " SET " + employeeNameLabel + " = ?, "
                                + employeeAgeLabel + " = ?, " + employeeBasicSalaryLabel
                                + " = ? WHERE " + employeeIdLabel + " = ?";

                        statement = DatabaseConnector.getConnection().prepareStatement(query3);
                        statement.setString(1, updatedSecurityGuard.getName());
                        statement.setInt(2, updatedSecurityGuard.getAge());
                        statement.setFloat(3, updatedSecurityGuard.getBasicSalary());
                        statement.setInt(4, employeeID);

                        statement.executeUpdate();
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteEmployee(int employeeID){

        System.out.println("DELETING....");

        query = "DELETE FROM " + employeeTableLabel + " WHERE " + employeeIdLabel + " = ?";

        try {
            statement = DatabaseConnector.getConnection().prepareStatement(query);
            statement.setInt(1, employeeID);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /******************************************ACADEMIC QUERIES********************************************************/

    private void insertAcademic(int academicID, float courseRate) {

        query = "INSERT INTO " + academicTableLabel + " VALUES (?, ?)";

        try {
            statement = DatabaseConnector
                    .getConnection()
                    .prepareStatement(query);

            statement.setInt(1, academicID);
            statement.setFloat(2, courseRate);

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /******************************************NON-ACADEMIC QUERIES****************************************************/
    private void insertNonAcademic(int nonAcademicID, float hourlyRate) {

        query = "INSERT INTO " + nonAcademicTableLabel + " VALUES (?, ?)";

        try {
            statement = DatabaseConnector
                    .getConnection()
                    .prepareStatement(query);

            statement.setInt(1, nonAcademicID);
            statement.setFloat(2, hourlyRate);

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /******************************************LECTURER QUERIES********************************************************/

    public void addLecturer(Lecturer lecturer){

        if(getEmployee(lecturer.getId()) != null){

            updateEmployee(lecturer.getId(), lecturer);
            return;
        }
        insertEmployee(lecturer.getId(), lecturer.getName(), lecturer.getAge(), lecturer.getBasicSalary());
        insertAcademic(lecturer.getId(), lecturer.getCourseRate());
        insertLecturer(lecturer.getId(), lecturer.getTotalCourses());
    }

    private void insertLecturer(int lecturerID, int totalCourses) {

        query = "INSERT INTO " + lecturerTableLabel + " VALUES (?, ?)";

        try {
            statement = DatabaseConnector
                    .getConnection()
                    .prepareStatement(query);

            statement.setInt(1, lecturerID);
            statement.setInt(2, totalCourses);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Lecturer getLecturer(int id) {

        query = "SELECT * FROM " + lecturerTableLabel + " WHERE " + lecturerIdLabel + "= ?";

        try {
            statement = DatabaseConnector.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            Lecturer lecturer = null;

            if (resultSet.next()) {
                lecturer = new Lecturer();
                lecturer.setId(resultSet.getInt(lecturerIdLabel));
                lecturer.setTotalCourses(resultSet.getInt(totalCoursesLabel));
            }
            return lecturer;

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null && resultSet != null) {
                    statement.close();
                    resultSet.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Lecturer> getAllLecturer(){

        query = "SELECT " + employeeIdLabel + ",\n" +
                employeeNameLabel + ",\n" +
                employeeAgeLabel + ",\n" +
                employeeBasicSalaryLabel + ",\n" +
                courseRateLabel + ",\n" +
                totalCoursesLabel + "\n" +
                "\n" +
                "FROM " + employeeTableLabel + ", " + academicTableLabel + ", " + lecturerTableLabel + "\n" +
                "WHERE " + academicIdLabel + " = " + employeeIdLabel + " AND " +
                lecturerIdLabel + " = " + employeeIdLabel + ";";

        try {
            statement = DatabaseConnector.getConnection().prepareStatement(query);
            resultSet = statement.executeQuery();

            if(resultSet != null){
                ArrayList<Lecturer> lecturers = new ArrayList<>();

                while (resultSet.next()){
                    lecturers.add(new Lecturer(
                            resultSet.getInt(employeeIdLabel),
                            resultSet.getString(employeeNameLabel),
                            resultSet.getInt(employeeAgeLabel),
                            resultSet.getFloat(employeeBasicSalaryLabel),
                            resultSet.getFloat(courseRateLabel),
                            resultSet.getInt(totalCoursesLabel)));
                }

                return lecturers;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null && resultSet != null) {
                    statement.close();
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /******************************************SECURITY GUARD QUERIES**************************************************/

    public void addSecurityGuard(SecurityGuard securityGuard){

        if(getEmployee(securityGuard.getId()) != null){

            updateEmployee(securityGuard.getId(), securityGuard);
            return;
        }
        insertEmployee(securityGuard.getId(), securityGuard.getName(), securityGuard.getAge(),
                securityGuard.getBasicSalary());
        insertNonAcademic(securityGuard.getId(), securityGuard.getHourlyRate());
        insertSecurityGuard(securityGuard.getId(), securityGuard.getTotalHours());
    }

    private void insertSecurityGuard(int securityGuardID, int totalHours) {

        query = "INSERT INTO " + securityGuardTableLabel + " VALUES (?, ?)";

        try {
            statement = DatabaseConnector
                    .getConnection()
                    .prepareStatement(query);

            statement.setInt(1, securityGuardID);
            statement.setInt(2, totalHours);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private SecurityGuard getSecurityGuard(int id) {

        query = "SELECT * FROM " + securityGuardTableLabel + " WHERE " + securityGuardIDLabel + " = ?";

        try {
            statement = DatabaseConnector.getConnection().prepareStatement(query);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            SecurityGuard security_guard = null;
            if (resultSet.next()) {

                security_guard = new SecurityGuard();
                security_guard.setId(resultSet.getInt(securityGuardIDLabel));
                security_guard.setTotalHours(resultSet.getInt(totalHoursLabel));
            }
            return security_guard;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (statement != null && resultSet != null) {
                try {
                    statement.close();
                    resultSet.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public ArrayList<SecurityGuard> getAllSecurityGuard(){

        query = "SELECT " + employeeIdLabel + ",\n" +
                employeeNameLabel + ",\n" +
                employeeAgeLabel + ",\n" +
                employeeBasicSalaryLabel + ",\n" +
                hourlyRateLabel + ",\n" +
                totalHoursLabel + "\n" +
                "\n" +
                "FROM " + employeeTableLabel + ", " + nonAcademicTableLabel + ", " + securityGuardTableLabel + "\n" +
                "WHERE " + nonAcademicIdLabel + " = " + employeeIdLabel + " AND " +
                securityGuardIDLabel + " = " + employeeIdLabel + ";";

        try {
            statement = DatabaseConnector.getConnection().prepareStatement(query);
            resultSet = statement.executeQuery();

            if(resultSet != null){
                ArrayList<SecurityGuard> securityGuards = new ArrayList<>();

                while (resultSet.next()){

                    securityGuards.add(new SecurityGuard(
                            resultSet.getInt(employeeIdLabel),
                            resultSet.getString(employeeNameLabel),
                            resultSet.getInt(employeeAgeLabel),
                            resultSet.getFloat(employeeBasicSalaryLabel),
                            resultSet.getFloat(hourlyRateLabel),
                            resultSet.getInt(totalHoursLabel)));
                }
                return securityGuards;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null && resultSet != null) {
                    statement.close();
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
