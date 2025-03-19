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

    public EmployeeDTO createUser(EmployeeDTO employeeDTO){
        return null;
    }

    public EmployeeDTO getUser(Long id){
        return null;
    }

    public List<EmployeeDTO> getUserByName(String name){
        return null;
    }

    public List<EmployeeDTO> getUserByNameAndFunction(String name, String function){
        return null;
    }

    //TODO: Create entity before finishing method
    public List<EmployeeDTO> getUserByNameAndDepartment(String name){
        return null;
    }

    public List<EmployeeDTO> getUserBySalary(double salary){
        return null;
    }

    //TODO: Update method after creating Department entity
    public List<EmployeeDTO> getUserByDepartment(){
        return null;
    }

    //TODO: Return in a crescent order
    public List<EmployeeDTO> getUserRankedBySalary(){
        return null;
    }

    public EmployeeDTO updateUser(EmployeeDTO employeeDTO, Long id){
        return null;
    }

    public EmployeeDTO patchUser(Map<String, Object> updates, Long id){
        return null;
    }

    public EmployeeDTO deleteUser(Long id){
        return null;
    }

    //TODO: Create methods to associate and dissociate employees and departments

}
