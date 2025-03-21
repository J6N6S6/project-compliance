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
        return null;
    }

    public DepartmentDTO patchDepartment(Map<String, Object> updates, Long id) {
        return null;
    }

    public Void deleteDepartment(Long id){
        return null;
    }
}
