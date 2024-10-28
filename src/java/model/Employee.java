package model;

import java.sql.Date;

public class Employee {
    private int id;
    private String name;
    private int departmentId;
    private String phoneNumber;
    private String address;
    private int salaryId;
    private Date dob;
    private Department department;
    private Salary salary;

    public Employee() {}

    public Employee(int id, String name, int departmentId, String phoneNumber, String address, int salaryId, Date dob) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.salaryId = salaryId;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

 
    @Override
    public String toString() {
        return "Employee{id=" + id + ", name=" + name + ", departmentId=" + departmentId + "}";
    }
}
