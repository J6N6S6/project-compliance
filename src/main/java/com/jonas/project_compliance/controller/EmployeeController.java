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
        EmployeeDTO response = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
        EmployeeDTO response = employeeService.getEmployee(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByName(@PathVariable String name) {
        List<EmployeeDTO> employees= employeeService.getEmployeeByName(name);
        return ResponseEntity.ok(employees);
    }

}
