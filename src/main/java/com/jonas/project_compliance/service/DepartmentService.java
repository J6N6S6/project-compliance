package com.jonas.project_compliance.service;

import com.jonas.project_compliance.DTO.DepartmentDTO;
import com.jonas.project_compliance.DTO.DepartmentWithoutEmployeesNumberDTO;
import com.jonas.project_compliance.mapper.DepartmentMapper;
import com.jonas.project_compliance.mapper.DepartmentWithoutEmployeesNumberMapper;
import com.jonas.project_compliance.model.Department;
import com.jonas.project_compliance.repository.DepartmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DepartmentWithoutEmployeesNumberMapper departmentWithoutEmployeesNumberMapper;

    @Autowired
    private EntityManager entityManager;

    public DepartmentDTO createDepartment(DepartmentWithoutEmployeesNumberDTO departmentDTO) {

        if(departmentDTO.departmentName() == null || departmentDTO.departmentName().isBlank()) {
            throw new RuntimeException("Department name cannot be null or empty");
        }

        Department department = departmentWithoutEmployeesNumberMapper.toEntity(departmentDTO);

        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.toDTO(savedDepartment);
    }

    public DepartmentDTO getDepartment(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        return departmentMapper.toDTO(department);
    }

    public List<DepartmentDTO> getDepartmentByName(String departmentName) {

        if (departmentName == null || departmentName.isBlank()) {
            throw new RuntimeException("Department name cannot be null or empty");
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> query = cb.createQuery(Department.class);
        Root<Department> department = query.from(Department.class);

        query.select(department)
                .where(cb.like(cb.lower(department.get("departmentName")), "%" + departmentName.toLowerCase() + "%"));

        List<Department> departments = entityManager.createQuery(query).getResultList();

        return departments.stream()
                .map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, Long id) {

        if (departmentDTO.departmentName() == null || departmentDTO.departmentName().isBlank()) {
            throw new RuntimeException("Department name cannot be null or empty");
        }
        if (departmentDTO.employeesNumber() < 0) {
            throw new RuntimeException("Employees number cannot be negative");
        }

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        department.setDepartmentName(departmentDTO.departmentName());
        department.setEmployeesNumber(departmentDTO.employeesNumber());

        Department updatedDepartment = departmentRepository.save(department);

        return departmentMapper.toDTO(updatedDepartment);
    }

    public DepartmentDTO patchDepartment(Map<String, Object> updates, Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "departmentName":
                    if (value != null && !value.toString().isBlank()) {
                        department.setDepartmentName(value.toString());
                    }
                    else {
                        throw new RuntimeException("Department name must not be null or blank");
                    }
                    break;

                case "employeesNumber":
                    if (value != null) {
                        int employeesNumber = Integer.parseInt(value.toString());
                        if (employeesNumber >= 0) {
                            department.setEmployeesNumber(employeesNumber);
                        } else {
                            throw new RuntimeException("Employees number cannot be negative");
                        }
                    }
                    else {
                        throw new RuntimeException("Employees number must be informed");
                    }
                    break;
                default:
                    throw new RuntimeException("Informed field was not found: " + key);
            }
        });

        Department updatedDepartment = departmentRepository.save(department);

        return departmentMapper.toDTO(updatedDepartment);
    }

    public Void deleteDepartment(Long id){
        return null;
    }
}
