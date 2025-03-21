package com.jonas.project_compliance.DTO;

import com.jonas.project_compliance.model.Employee;
import com.jonas.project_compliance.model.Department;

public record EmployeeDepartmentDTO(
        Long UUID,
        Employee employee,
        Department department
) {

}