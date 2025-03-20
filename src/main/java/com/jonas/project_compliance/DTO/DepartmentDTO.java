package com.jonas.project_compliance.DTO;

public record DepartmentDTO(
        Long UUID,
        String departmentName,
        int employeesNumber
) {
}
