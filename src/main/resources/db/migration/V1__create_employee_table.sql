CREATE TABLE tb_employee (
    UUID SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    neighborhood VARCHAR(255),
    zip_code VARCHAR(255),
    phone_number VARCHAR(10) UNIQUE,
    salary DECIMAL NOT NULL,
    contract_date TIMESTAMP NOT NULL,
    function VARCHAR(255) NOT NULL
)