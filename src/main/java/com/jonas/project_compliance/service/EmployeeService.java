package com.jonas.project_compliance.service;

import com.jonas.project_compliance.DTO.EmployeeDTO;
import com.jonas.project_compliance.mapper.EmployeeMapper;
import com.jonas.project_compliance.model.Employee;
import com.jonas.project_compliance.model.EmployeeDepartment;
import com.jonas.project_compliance.model.Department;
import com.jonas.project_compliance.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EntityManager entityManager;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        if(employeeDTO.name() == null || employeeDTO.name().isBlank()) {
            throw new RuntimeException("Employee name cannot be null or empty");
        }

        if(employeeDTO.address() == null || employeeDTO.address().isBlank()) {
            throw new RuntimeException("Employee address cannot be null or empty");
        }

        if(employeeDTO.salary() == null || employeeDTO.salary().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Employee salary cannot be zero or minor then 0");
        }

        if(employeeDTO.contractDate() == null) {
            throw new RuntimeException("Employee contract date must have a value");
        }

        if(employeeDTO.function() == null || employeeDTO.function().isBlank()) {
            throw new RuntimeException("Employee function cannot be null or empty");
        }

        Employee employee = employeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDTO(savedEmployee);
    }

    public EmployeeDTO getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Id field is required"));
        return employeeMapper.toDTO(employee);
    }

    public List<EmployeeDTO> getEmployeeByName(String name) {

        if(name == null || name.isBlank()) {
            throw new RuntimeException("Inform a name to search");
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = query.from(Employee.class);

        query.select(employeeRoot)
                .where(cb.like(cb.lower(employeeRoot.get("name")), "%" + name.toLowerCase() + "%"));

        List<Employee> employees = entityManager.createQuery(query).getResultList();

        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeeByNameAndFunction(String name, String function){
        if (name == null || name.isBlank()) {
            throw new RuntimeException("Name is required");
        }
        if (function == null || function.isBlank()) {
            throw new RuntimeException("Function is required");
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = query.from(Employee.class);

        Predicate namePredicate = cb.like(cb.lower(employeeRoot.get("name")), "%" + name.toLowerCase() + "%");
        Predicate functionPredicate = cb.like(cb.lower(employeeRoot.get("function")), "%" + function.toLowerCase() + "%");

        query.select(employeeRoot)
                .where(cb.and(namePredicate, functionPredicate));

        List<Employee> employees = entityManager.createQuery(query).getResultList();

        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeeBySalary(double salary) {
        if (salary <= 0){
            throw new RuntimeException("Salary must be positive");
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = query.from(Employee.class);

        query.select(employeeRoot)
                .where(cb.greaterThan(employeeRoot.get("salary"), salary));

        List<Employee> employees = entityManager.createQuery(query).getResultList();

        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeeByNameAndDepartment(String name, String departmentName){
        if (name == null || name.isBlank()) {
            throw new RuntimeException("Name is required");
        }
        if (departmentName == null || departmentName.isBlank()) {
            throw new RuntimeException("Department name is required");
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<EmployeeDepartment> employeeDepartment = query.from(EmployeeDepartment.class);

        Join<EmployeeDepartment, Employee> employee = employeeDepartment.join("employee");
        Join<EmployeeDepartment, Department> department = employeeDepartment.join("department");

        Predicate namePredicate = cb.like(cb.lower(employee.get("name")), "%" + name.toLowerCase() + "%");
        Predicate departmentPredicate = cb.like(cb.lower(department.get("departmentName")), "%" + departmentName.toLowerCase() + "%");

        query.select(employee)
                .where(cb.and(namePredicate, departmentPredicate));

        List<Employee> employees = entityManager.createQuery(query).getResultList();

        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeeByDepartment(String departmentName){
        if (departmentName == null || departmentName.isBlank()) {
            throw new RuntimeException("Department name is required");
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<EmployeeDepartment> employeeDepartment = query.from(EmployeeDepartment.class);

        Join<EmployeeDepartment, Employee> employee = employeeDepartment.join("employee");
        Join<EmployeeDepartment, Department> department = employeeDepartment.join("department");

        Predicate departmentPredicate = cb.like(cb.lower(department.get("departmentName")), "%" + departmentName.toLowerCase() + "%");

        query.select(employee)
                .where(departmentPredicate);

        List<Employee> employees = entityManager.createQuery(query).getResultList();

        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    //TODO: Return in a crescent order
    public List<EmployeeDTO> getEmployeeRankedBySalary(){
        return null;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id){
        return null;
    }

    public EmployeeDTO patchEmployee(Map<String, Object> updates, Long id){
        return null;
    }

    public Void deleteEmployee(Long id){
        return null;
    }

    //TODO: Create methods to associate and dissociate employees and departments

}
