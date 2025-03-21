package com.jonas.project_compliance.service;

import com.jonas.project_compliance.DTO.DepartmentDTO;
import com.jonas.project_compliance.mapper.DepartmentMapper;
import com.jonas.project_compliance.model.Department;
import com.jonas.project_compliance.repository.DepartmentRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EntityManager entityManager;

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {

        if(departmentDTO.departmentName() == null || departmentDTO.departmentName().isBlank()) {
            throw new RuntimeException("Department name cannot be null or empty");
        }

        Department department = departmentMapper.toEntity(departmentDTO);

        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.toDTO(savedDepartment);
    }

    public DepartmentDTO getDepartment(Long id) {
        return null;
    }

    public List<DepartmentDTO> getDepartmentByName(String departmentName) {
        return null;
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
