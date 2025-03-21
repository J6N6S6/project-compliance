package com.jonas.project_compliance.mapper;

import com.jonas.project_compliance.DTO.DepartmentDTO;
import com.jonas.project_compliance.DTO.DepartmentWithoutEmployeesNumberDTO;
import com.jonas.project_compliance.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentWithoutEmployeesNumberMapper {

    public Department toEntity(DepartmentWithoutEmployeesNumberDTO dto) {
        return new Department(
                dto.UUID(),
                dto.departmentName()
        );
    }

    public DepartmentWithoutEmployeesNumberDTO toDTO(Department entity) {
        return new DepartmentWithoutEmployeesNumberDTO(
                entity.getUUID(),
                entity.getDepartmentName()
        );
    }
}
