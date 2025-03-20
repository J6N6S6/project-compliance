CREATE TABLE tb_department (
    UUID SERIAL PRIMARY KEY,
    department_name VARCHAR(255) NOT NULL,
    employees_number INTEGER NOT NULL
)