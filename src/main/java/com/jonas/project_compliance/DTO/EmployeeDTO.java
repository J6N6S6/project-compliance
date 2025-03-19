package com.jonas.project_compliance.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EmployeeDTO(
    Long UUID,
    String name,
    String address,
    String neighborhood,
    String zipCode,
    String phoneNumber,
    BigDecimal salary,
    LocalDateTime contractDate,
    String function
) {
}
