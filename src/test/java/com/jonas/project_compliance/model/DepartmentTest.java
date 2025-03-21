package com.jonas.project_compliance.model;

import com.jonas.project_compliance.functional.DepartmentOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department();
    }

    @Test
    void getUUID() {
        department.setUUID(1L);
        assertEquals(1L, department.getUUID());
    }

    @Test
    void setUUID() {
        department.setUUID(2L);
        assertEquals(2L, department.getUUID());
    }

    @Test
    void getDepartmentName() {
        department.setDepartmentName("IT");
        assertEquals("IT", department.getDepartmentName());
    }

    @Test
    void setDepartmentName() {
        department.setDepartmentName("HR");
        assertEquals("HR", department.getDepartmentName());
    }

    @Test
    void getEmployeesNumber() {
        department.setEmployeesNumber(50);
        assertEquals(50, department.getEmployeesNumber());
    }

    @Test
    void setEmployeesNumber() {
        department.setEmployeesNumber(100);
        assertEquals(100, department.getEmployeesNumber());
    }

    @Test
    void performOperation() {
        // Criando um mock de DepartmentOperation
        DepartmentOperation operation = mock(DepartmentOperation.class);

        // Definindo o comportamento do mock
        doNothing().when(operation).execute(anyString());

        // Chamando o método performOperation
        department.performOperation(operation);

        // Verificando se o método execute foi chamado com o nome do departamento
        verify(operation).execute(department.getDepartmentName());
    }
}
