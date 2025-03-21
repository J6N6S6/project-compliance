package com.jonas.project_compliance.service;

import com.jonas.project_compliance.DTO.DepartmentWithoutEmployeesNumberDTO;
import com.jonas.project_compliance.mapper.DepartmentWithoutEmployeesNumberMapper;
import com.jonas.project_compliance.model.Department;
import com.jonas.project_compliance.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentWithoutEmployeesNumberMapper departmentWithoutEmployeesNumberMapper;

    @InjectMocks
    private DepartmentService departmentService;

    private Department department;
    private DepartmentWithoutEmployeesNumberDTO departmentDTO;

    @BeforeEach
    void setUp() {
        // Configuração do objeto Department que será usado nos testes
        department = new Department();
        department.setId(1L);
        department.setName("HR");

        // Configuração do DTO que será usado nos testes
        departmentDTO = new DepartmentWithoutEmployeesNumberDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("HR");
    }

    @Test
    void testCreateDepartment() {
        // Simulação do comportamento do repository para criação
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        Department result = departmentService.createDepartment(departmentDTO);

        // Verificação se o departamento foi criado corretamente
        assertNotNull(result);
        assertEquals(department.getId(), result.getId());
        assertEquals(department.getName(), result.getName());

        // Verificação da interação com o repository
        verify(departmentRepository).save(any(Department.class));
    }

    @Test
    void testDeleteDepartment() {
        // Simulação do comportamento do repository para encontrar o departamento
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));

        // Simulação da exclusão do departamento
        departmentService.deleteDepartment(department.getId());

        // Verificação da interação com o repository
        verify(departmentRepository).findById(department.getId());
        verify(departmentRepository).delete(department);
    }
}
