package com.jonas.project_compliance.service;

import com.jonas.project_compliance.DTO.EmployeeDTO;
import com.jonas.project_compliance.mapper.EmployeeMapper;
import com.jonas.project_compliance.model.Employee;
import com.jonas.project_compliance.repository.EmployeeRepository;
import com.jonas.project_compliance.service.validation.EmployeeValidationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private EmployeeValidationContext employeeValidationContext;

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<Employee> criteriaQuery;

    @Mock
    private Root<Employee> root;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        employee = new Employee(
                1L,
                "John Doe",
                "123 Main St",
                "Downtown",
                "12345",
                "555-1234",
                new BigDecimal("5000.00"),
                LocalDateTime.now(),
                "Developer"
        );

        employeeDTO = new EmployeeDTO(
                1L, // Adicione o campo Long (id) aqui
                "John Doe",
                "123 Main St",
                "Downtown",
                "12345",
                "555-1234",
                new BigDecimal("5000.00"),
                LocalDateTime.now(),
                "Developer"
        );
    }

    @Test
    void deleteEmployee_ShouldThrowException_WhenEmployeeDoesNotExist() {
        // Configuração do mock do CriteriaBuilder
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Employee.class)).thenReturn(criteriaQuery);

        // Configuração do mock do CriteriaQuery
        when(criteriaQuery.from(Employee.class)).thenReturn(root);
        when(criteriaQuery.select(any(Root.class))).thenReturn(criteriaQuery); // Retorna o próprio CriteriaQuery
        when(criteriaQuery.where(any(Predicate.class))).thenReturn(criteriaQuery); // Retorna o próprio CriteriaQuery

        // Configuração do mock do EmployeeRepository para retornar Optional.empty()
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        // Execução do método e verificação da exceção
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeService.deleteEmployee(1L);
        });

        // Verificação da mensagem de exceção
        assertEquals("Employee not found with id: 1", exception.getMessage());

        // Verificação de que o EntityManager.remove nunca foi chamado
        verify(entityManager, never()).remove(any(Employee.class));
    }
}