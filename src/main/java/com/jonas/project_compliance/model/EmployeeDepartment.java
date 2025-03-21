package com.jonas.project_compliance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_employee_department")
public class EmployeeDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UUID;

    @ManyToOne
    @JoinColumn(name = "UUID_employee", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "UUID_department", nullable = false)
    private Department department;

    public EmployeeDepartment() {
    }

    public EmployeeDepartment(Long UUID, Employee employee, Department department) {
        this.UUID = UUID;
        this.employee = employee;
        this.department = department;
    }

    public Long getUUID() {
        return UUID;
    }

    public void setUUID(Long UUID) {
        this.UUID = UUID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
