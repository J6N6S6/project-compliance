package com.jonas.project_compliance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UUID;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "employees_number")
    private int employeesNumber;

    public Department() {
    }

    public Department(Long UUID, String departmentName, int employeesNumber) {
        this.UUID = UUID;
        this.departmentName = departmentName;
        this.employeesNumber = employeesNumber;
    }

    public Long getUUID() {
        return UUID;
    }

    public void setUUID(Long UUID) {
        this.UUID = UUID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }
}
