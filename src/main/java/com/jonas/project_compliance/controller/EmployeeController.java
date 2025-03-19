package com.jonas.project_compliance.controller;

import com.jonas.project_compliance.DTO.EmployeeDTO;
import com.jonas.project_compliance.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
        EmployeeDTO response = employeeService.getEmployee(id);
        return ResponseEntity.ok(response);
    }
}
