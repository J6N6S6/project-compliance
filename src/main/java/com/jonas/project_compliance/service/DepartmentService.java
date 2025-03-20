package com.jonas.project_compliance.service;

import com.jonas.project_compliance.repository.DepartmentRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    EntityManager entityManager;

}
