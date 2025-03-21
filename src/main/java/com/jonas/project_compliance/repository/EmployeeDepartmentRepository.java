package com.jonas.project_compliance.repository;

import com.jonas.project_compliance.model.EmployeeDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDepartmentRepository extends JpaRepository<EmployeeDepartment, Long> {
}
