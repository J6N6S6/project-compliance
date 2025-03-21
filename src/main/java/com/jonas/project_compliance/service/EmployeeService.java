package com.jonas.project_compliance.service;

import com.jonas.project_compliance.DTO.EmployeeDTO;
import com.jonas.project_compliance.mapper.EmployeeMapper;
import com.jonas.project_compliance.model.Employee;
import com.jonas.project_compliance.model.EmployeeDepartment;
import com.jonas.project_compliance.model.Department;
import com.jonas.project_compliance.repository.DepartmentRepository;
import com.jonas.project_compliance.repository.EmployeeDepartmentRepository;
import com.jonas.project_compliance.repository.EmployeeRepository;
import com.jonas.project_compliance.service.validation.EmployeeValidationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeDepartmentRepository employeeDepartmentRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    EmployeeValidationContext employeeValidationContext;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        if(!employeeValidationContext.executeValidation(employeeDTO)) {

            throw new RuntimeException("Employee data must be complete!");
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

    public List<EmployeeDTO> getEmployeeByNameAndFunction(String name, String function) {

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

    public List<EmployeeDTO> getEmployeeByNameAndDepartment(String name, String departmentName) {

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

    public List<EmployeeDTO> getEmployeeByDepartment(String departmentName) {

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

    public List<EmployeeDTO> getEmployeeRankedBySalary() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> employee = query.from(Employee.class);

        query.select(employee)
                .orderBy(cb.asc(employee.get("salary")));

        List<Employee> employees = entityManager.createQuery(query).getResultList();

        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }


    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        employee.setName(employeeDTO.name());
        employee.setAddress(employeeDTO.address());
        employee.setNeighborhood(employeeDTO.neighborhood());
        employee.setZipCode(employeeDTO.zipCode());
        employee.setPhoneNumber(employeeDTO.phoneNumber());
        employee.setSalary(employeeDTO.salary());
        employee.setContractDate(employeeDTO.contractDate());
        employee.setFunction(employeeDTO.function());

        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toDTO(updatedEmployee);
    }

    public EmployeeDTO patchEmployee(Map<String, Object> updates, Long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    if (value != null && !value.toString().isBlank()) {
                        employee.setName(value.toString());
                    }
                    break;
                case "address":
                    if (value != null && !value.toString().isBlank()) {
                        employee.setAddress(value.toString());
                    }
                    break;
                case "neighborhood":
                    if (value != null && !value.toString().isBlank()) {
                        employee.setNeighborhood(value.toString());
                    }
                    break;
                case "zipCode":
                    if (value != null && !value.toString().isBlank()) {
                        employee.setZipCode(value.toString());
                    }
                    break;
                case "phoneNumber":
                    if (value != null && !value.toString().isBlank()) {
                        employee.setPhoneNumber(value.toString());
                    }
                    break;
                case "salary":
                    if (value != null) {
                        employee.setSalary(new BigDecimal(value.toString()));
                    }
                    break;
                case "contractDate":
                    if (value != null) {
                        employee.setContractDate(LocalDateTime.parse(value.toString()));
                    }
                    break;
                case "function":
                    if (value != null && !value.toString().isBlank()) {
                        employee.setFunction(value.toString());
                    }
                    break;
                default:
                    throw new RuntimeException("Invalid field: " + key);
            }
        });

        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toDTO(updatedEmployee);
    }

    @Transactional
    public void deleteEmployee(Long id){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> employeeQuery = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = employeeQuery.from(Employee.class);

        employeeQuery.select(employeeRoot)
                .where(cb.equal(employeeRoot.get("UUID"), id));

        Employee employee = entityManager.createQuery(employeeQuery)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        CriteriaQuery<EmployeeDepartment> employeeDepartmentQuery = cb.createQuery(EmployeeDepartment.class);
        Root<EmployeeDepartment> employeeDepartmentRoot = employeeDepartmentQuery.from(EmployeeDepartment.class);

        employeeDepartmentQuery.select(employeeDepartmentRoot)
                .where(cb.equal(employeeDepartmentRoot.get("employee"), employee));

        List<EmployeeDepartment> employeeDepartments = entityManager.createQuery(employeeDepartmentQuery)
                .getResultList();

        if (!employeeDepartments.isEmpty()) {
            for (EmployeeDepartment employeeDepartment : employeeDepartments) {
                Department department = employeeDepartment.getDepartment();
                int employeesNumber = department.getEmployeesNumber();

                if (employeesNumber > 0) {
                    department.setEmployeesNumber(employeesNumber - 1);
                    entityManager.merge(department);
                }
            }
        }

        entityManager.remove(employee);

    }

    public EmployeeDTO associateEmployeeWithDepartment(Long employeeId, Long departmentId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));

        EmployeeDepartment employeeDepartment = new EmployeeDepartment();
        employeeDepartment.setEmployee(employee);
        employeeDepartment.setDepartment(department);

        employeeDepartmentRepository.save(employeeDepartment);

        department.setEmployeesNumber(department.getEmployeesNumber() + 1);
        departmentRepository.save(department);

        return employeeMapper.toDTO(employee);
    }

    @Transactional
    public EmployeeDTO dissociateEmployeeFromDepartment(Long employeeId, Long departmentId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> employeeQuery = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = employeeQuery.from(Employee.class);

        employeeQuery.select(employeeRoot)
                .where(cb.equal(employeeRoot.get("UUID"), employeeId));

        Employee employee = entityManager.createQuery(employeeQuery)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        CriteriaQuery<Department> departmentQuery = cb.createQuery(Department.class);
        Root<Department> departmentRoot = departmentQuery.from(Department.class);

        departmentQuery.select(departmentRoot)
                .where(cb.equal(departmentRoot.get("UUID"), departmentId));

        Department department = entityManager.createQuery(departmentQuery)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));

        CriteriaQuery<EmployeeDepartment> employeeDepartmentQuery = cb.createQuery(EmployeeDepartment.class);
        Root<EmployeeDepartment> employeeDepartmentRoot = employeeDepartmentQuery.from(EmployeeDepartment.class);

        employeeDepartmentQuery.select(employeeDepartmentRoot)
                .where(cb.and(
                        cb.equal(employeeDepartmentRoot.get("employee"), employee),
                        cb.equal(employeeDepartmentRoot.get("department"), department)
                ));

        List<EmployeeDepartment> employeeDepartments = entityManager.createQuery(employeeDepartmentQuery)
                .getResultList();

        for (EmployeeDepartment employeeDepartment : employeeDepartments) {
            entityManager.remove(employeeDepartment);
        }

        int employeesNumber = department.getEmployeesNumber();
        if (employeesNumber > 0) {
            department.setEmployeesNumber(employeesNumber - employeeDepartments.size());
            entityManager.merge(department);
        }

        return employeeMapper.toDTO(employee);
    }

}
