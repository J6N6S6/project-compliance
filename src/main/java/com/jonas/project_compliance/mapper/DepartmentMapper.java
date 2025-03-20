package com.jonas.project_compliance.mapper;

import com.jonas.project_compliance.DTO.DepartmentDTO;
import com.jonas.project_compliance.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentDTO dto) {
        return new Department(
                dto.UUID(),
                dto.departmentName(),
                dto.employeesNumber()
        );
    }

    public DepartmentDTO toDTO(Department entity) {
        return new DepartmentDTO(
                entity.getUUID(),
                entity.getDepartmentName(),
                entity.getEmployeesNumber()
        );
    }
}
