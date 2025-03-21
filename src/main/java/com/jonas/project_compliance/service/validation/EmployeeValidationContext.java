package com.jonas.project_compliance.service.validation;

import com.jonas.project_compliance.DTO.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EmployeeValidationContext {

    @Autowired
    @Qualifier("advancedValidationStrategy")
    private EmployeeValidationStrategy employeeValidationStrategy;

    public boolean executeValidation(EmployeeDTO employeeDTO) {
        return employeeValidationStrategy.validate(employeeDTO);
    }
}