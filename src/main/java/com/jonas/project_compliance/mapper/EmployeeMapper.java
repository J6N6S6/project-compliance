package com.jonas.project_compliance.mapper;

import com.jonas.project_compliance.DTO.EmployeeDTO;
import com.jonas.project_compliance.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeDTO dto) {
        return new Employee(
                dto.UUID(),
                dto.name(),
                dto.address(),
                dto.neighborhood(),
                dto.zipCode(),
                dto.phoneNumber(),
                dto.salary(),
                dto.contractDate(),
                dto.function()
        );
    }

    public EmployeeDTO toDTO(Employee entity) {
        return new EmployeeDTO(
                entity.getUUID(),
                entity.getName(),
                entity.getAddress(),
                entity.getNeighborhood(),
                entity.getZipCode(),
                entity.getPhoneNumber(),
                entity.getSalary(),
                entity.getContractDate(),
                entity.getFunction()
        );
    }
}
