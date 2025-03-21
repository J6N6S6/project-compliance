package com.jonas.project_compliance.service.validation;

import com.jonas.project_compliance.DTO.EmployeeDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Qualifier("basicValidationStrategy")
public class BasicEmployeeValidationStrategy implements EmployeeValidationStrategy {
    @Override
    public boolean validate(EmployeeDTO employeeDTO) {

        if (employeeDTO.name() == null || employeeDTO.name().isBlank()) {
            return false;
        }
        if (employeeDTO.address() == null || employeeDTO.address().isBlank()) {
            return false;
        }
        if (employeeDTO.salary() == null || employeeDTO.salary().compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        if (employeeDTO.contractDate() == null) {
            return false;
        }
        if(employeeDTO.function() == null || employeeDTO.function().isBlank()) {
            return false;
        }

        return true;
    }
}