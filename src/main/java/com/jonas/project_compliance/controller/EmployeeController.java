package com.jonas.project_compliance.controller;

import com.jonas.project_compliance.DTO.EmployeeDTO;
import com.jonas.project_compliance.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO employee = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {

        EmployeeDTO employee = employeeService.getEmployee(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByName(@PathVariable String name) {

        List<EmployeeDTO> employees = employeeService.getEmployeeByName(name);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/name-function")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByNameAndFunction(
            @RequestParam String name,
            @RequestParam String function
    ) {

        List<EmployeeDTO> employees = employeeService.getEmployeeByNameAndFunction(name, function);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/salary/{salary}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeBySalary(@PathVariable double salary) {

        List<EmployeeDTO> employeeDTOS = employeeService.getEmployeeBySalary(salary);
        return ResponseEntity.ok(employeeDTOS);
    }

    @GetMapping("/name-department")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByNameAndDepartment(
            @RequestParam String name,
            @RequestParam String departmentName
    ) {

        List<EmployeeDTO> employeeDTOS = employeeService.getEmployeeByNameAndDepartment(name, departmentName);
        return ResponseEntity.ok(employeeDTOS);
    }

    @GetMapping("/department")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByDepartment(
            @RequestParam String departmentName
    ) {
        List<EmployeeDTO> employeeDTOS = employeeService.getEmployeeByDepartment(departmentName);
        return ResponseEntity.ok(employeeDTOS);
    }

    @GetMapping("/ranked-by-salary")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeRankedBySalary() {
        List<EmployeeDTO> employeeDTOS = employeeService.getEmployeeRankedBySalary();
        return ResponseEntity.ok(employeeDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDTO employeeDTO
    ) {

        EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(employeeDTO, id);
        return ResponseEntity.ok(updatedEmployeeDTO);
    }
}
