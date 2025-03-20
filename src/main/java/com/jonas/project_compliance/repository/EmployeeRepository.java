package com.jonas.project_compliance.repository;

import com.jonas.project_compliance.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findByNameContainingIgnoreCaseAndFunctionContainingIgnoreCase(String name, String function);
}