package com.jonas.project_compliance.mapper;

import com.jonas.project_compliance.DTO.EmployeeDepartmentDTO;
import com.jonas.project_compliance.model.EmployeeDepartment;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDepartmentMapper {

    public EmployeeDepartment toEntity(EmployeeDepartmentDTO dto) {
        return new EmployeeDepartment(
                dto.UUID(),
                dto.employee(),
                dto.department()
        );
    }

    public EmployeeDepartmentDTO toDTO(EmployeeDepartment entity) {
        return new EmployeeDepartmentDTO(
                entity.getUUID(),
                entity.getEmployee(),
                entity.getDepartment()
        );
    }
}