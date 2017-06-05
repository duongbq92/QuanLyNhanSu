package com.example.admin.quanlynhansu.DTO;

/**
 * Created by RPG_LOVER on 04/05/2017.
 */

public class Employee {
    int employee_id;
    String firstName;
    String lastName;
    int department_id;
    String department_name;

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }


    public Employee(int employee_id, String firstName, String lastName, int department_id) {
        this.employee_id = employee_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department_id = department_id;
    }

    public Employee(String firstName, String lastName, int department_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department_id = department_id;
    }
    public Employee(int employee_id, String firstName, String lastName, int department_id, String department_name) {

        this.employee_id = employee_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department_id = department_id;
        this.department_name = department_name;
    }
}
