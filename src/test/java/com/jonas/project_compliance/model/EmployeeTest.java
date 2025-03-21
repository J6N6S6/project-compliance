package com.jonas.project_compliance.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
    }

    @Test
    void getUUID() {
        employee.setUUID(1L);
        assertEquals(1L, employee.getUUID());
    }

    @Test
    void setUUID() {
        employee.setUUID(2L);
        assertEquals(2L, employee.getUUID());
    }

    @Test
    void getName() {
        employee.setName("John Doe");
        assertEquals("John Doe", employee.getName());
    }

    @Test
    void setName() {
        employee.setName("Jane Doe");
        assertEquals("Jane Doe", employee.getName());
    }

    @Test
    void getAddress() {
        employee.setAddress("123 Main St");
        assertEquals("123 Main St", employee.getAddress());
    }

    @Test
    void setAddress() {
        employee.setAddress("456 Oak St");
        assertEquals("456 Oak St", employee.getAddress());
    }

    @Test
    void getNeighborhood() {
        employee.setNeighborhood("Downtown");
        assertEquals("Downtown", employee.getNeighborhood());
    }

    @Test
    void setNeighborhood() {
        employee.setNeighborhood("Uptown");
        assertEquals("Uptown", employee.getNeighborhood());
    }

    @Test
    void getZipCode() {
        employee.setZipCode("12345");
        assertEquals("12345", employee.getZipCode());
    }

    @Test
    void setZipCode() {
        employee.setZipCode("67890");
        assertEquals("67890", employee.getZipCode());
    }

    @Test
    void getPhoneNumber() {
        employee.setPhoneNumber("9876543210");
        assertEquals("9876543210", employee.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        employee.setPhoneNumber("1234567890");
        assertEquals("1234567890", employee.getPhoneNumber());
    }

    @Test
    void getSalary() {
        employee.setSalary(BigDecimal.valueOf(5000.00));
        assertEquals(BigDecimal.valueOf(5000.00), employee.getSalary());
    }

    @Test
    void setSalary() {
        employee.setSalary(BigDecimal.valueOf(6000.00));
        assertEquals(BigDecimal.valueOf(6000.00), employee.getSalary());
    }

    @Test
    void getContractDate() {
        LocalDateTime contractDate = LocalDateTime.of(2022, 1, 1, 9, 0, 0, 0);
        employee.setContractDate(contractDate);
        assertEquals(contractDate, employee.getContractDate());
    }

    @Test
    void setContractDate() {
        LocalDateTime contractDate = LocalDateTime.of(2023, 1, 1, 9, 0, 0, 0);
        employee.setContractDate(contractDate);
        assertEquals(contractDate, employee.getContractDate());
    }

    @Test
    void getFunction() {
        employee.setFunction("Developer");
        assertEquals("Developer", employee.getFunction());
    }

    @Test
    void setFunction() {
        employee.setFunction("Manager");
        assertEquals("Manager", employee.getFunction());
    }
}
