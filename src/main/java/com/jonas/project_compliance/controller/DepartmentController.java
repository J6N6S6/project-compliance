package com.jonas.project_compliance.controller;

import com.jonas.project_compliance.DTO.DepartmentDTO;
import com.jonas.project_compliance.DTO.DepartmentWithoutEmployeesNumberDTO;
import com.jonas.project_compliance.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentWithoutEmployeesNumberDTO departmentDTO) {

        DepartmentDTO department = departmentService.createDepartment(departmentDTO);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long id) {

        DepartmentDTO departmentDTO = departmentService.getDepartment(id);
        return ResponseEntity.ok(departmentDTO);
    }

    @GetMapping("/name/{departmentName}")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentByName(@PathVariable String departmentName) {

        List<DepartmentDTO> departments = departmentService.getDepartmentByName(departmentName);

        return ResponseEntity.ok(departments);
    }


}

