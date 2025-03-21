CREATE TABLE tb_employee_department (
    UUID SERIAL PRIMARY KEY,
    UUID_employee BIGINT NOT NULL,
    UUID_department BIGINT NOT NULL,
    CONSTRAINT fk_employee FOREIGN KEY (UUID_employee) REFERENCES tb_employee(UUID),
    CONSTRAINT fk_department FOREIGN KEY (UUID_department) REFERENCES tb_department(UUID)
);