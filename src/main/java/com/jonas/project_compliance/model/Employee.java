package com.jonas.project_compliance.model;

import jakarta.persistence.*;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UUID;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "contract_date")
    private LocalDateTime contractDate;

    public Employee() {
    }

    public Employee(
            Long UUID,
            String name,
            String address,
            String neighborhood,
            String zipCode,
            String phoneNumber,
            BigDecimal salary,
            LocalDateTime contractDate
    ) {
        this.UUID = UUID;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.contractDate = contractDate;
    }

    public Long getUUID() {
        return UUID;
    }

    public void setUUID(Long UUID) {
        this.UUID = UUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDateTime getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDateTime contractDate) {
        this.contractDate = contractDate;
    }
}
