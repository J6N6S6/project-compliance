package com.jonas.project_compliance.service.validation;

import com.jonas.project_compliance.DTO.EmployeeDTO;

public interface EmployeeValidationStrategy {
    boolean validate(EmployeeDTO employeeDTO);
}