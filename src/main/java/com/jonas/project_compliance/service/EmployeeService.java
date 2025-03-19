package com.jonas.project_compliance.service;

import com.jonas.project_compliance.DTO.EmployeeDTO;
import com.jonas.project_compliance.mapper.EmployeeMapper;
import com.jonas.project_compliance.model.Employee;
import com.jonas.project_compliance.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO){
        return null;
    }

    public EmployeeDTO getEmployee(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Id field is required"));
        return employeeMapper.toDTO(employee);
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

    public Void deleteEmployee(Long id){
        return null;
    }

    //TODO: Create methods to associate and dissociate employees and departments

}
