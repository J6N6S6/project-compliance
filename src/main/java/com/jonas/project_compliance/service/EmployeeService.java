package com.jonas.project_compliance.service;

import com.jonas.project_compliance.DTO.EmployeeDTO;
import com.jonas.project_compliance.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO){
        return null;
    }

    public EmployeeDTO getEmployee(Long id){
        return null;
    }

    public List<EmployeeDTO> getEmployeeByName(String name){
        return null;
    }

    public List<EmployeeDTO> getEmployeeByNameAndFunction(String name, String function){
        return null;
    }

    //TODO: Create entity before finishing method
    public List<EmployeeDTO> getEmployeeByNameAndDepartment(String name){
        return null;
    }

    public List<EmployeeDTO> getEmployeeBySalary(double salary){
        return null;
    }

    //TODO: Update method after creating Department entity
    public List<EmployeeDTO> getEmployeeByDepartment(){
        return null;
    }

    //TODO: Return in a crescent order
    public List<EmployeeDTO> getEmployeeRankedBySalary(){
        return null;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id){
        return null;
    }

    public EmployeeDTO patchEmployee(Map<String, Object> updates, Long id){
        return null;
    }

    public EmployeeDTO deleteEmployee(Long id){
        return null;
    }

    //TODO: Create methods to associate and dissociate employees and departments

}
