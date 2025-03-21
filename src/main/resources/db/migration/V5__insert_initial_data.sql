-- Data for tables tb_department, tb_employee and tb_employee_department

INSERT INTO tb_department (department_name, employees_number) VALUES
('TI', 3),
('RH', 2),
('Financeiro', 2),
('Marketing', 3);

INSERT INTO tb_employee (name, address, neighborhood, zip_code, phone_number, salary, contract_date, function) VALUES
('João Silva', 'Rua A, 123', 'Centro', '12345-678', '1198765432', 5000.00, '2023-01-01 00:00:00', 'Desenvolvedor'),
('Maria Oliveira', 'Rua B, 456', 'Jardins', '54321-876', '1191234567', 6000.00, '2023-02-01 00:00:00', 'Analista'),
('Carlos Souza', 'Rua C, 789', 'Vila Madalena', '98765-432', '1195555666', 7500.00, '2023-03-01 00:00:00', 'Gerente'),
('Ana Costa', 'Rua D, 1010', 'Bela Vista', '01311-000', '1199999888', 9000.00, '2023-04-01 00:00:00', 'Designer'),
('Pedro Almeida', 'Rua E, 2020', 'Consolação', '01305-000', '1197777666', 12000.00, '2023-05-01 00:00:00', 'Arquiteto de Software'),
('Fernanda Lima', 'Rua F, 3030', 'Jardim Paulista', '01426-000', '1196666555', 15000.00, '2023-06-01 00:00:00', 'Product Manager'),
('Ricardo Santos', 'Rua G, 4040', 'Cerqueira César', '01414-000', '1194444333', 8000.00, '2023-07-01 00:00:00', 'Engenheiro de Dados'),
('Juliana Pereira', 'Rua H, 5050', 'República', '01302-000', '1193333222', 10000.00, '2023-08-01 00:00:00', 'Scrum Master'),
('Lucas Mendes', 'Rua I, 6060', 'Consolação', '01307-000', '1192222111', 7000.00, '2023-09-01 00:00:00', 'QA Engineer'),
('Patrícia Rocha', 'Rua J, 7070', 'Jardim Paulista', '01415-000', '1191111000', 11000.00, '2023-10-01 00:00:00', 'UX Designer');

INSERT INTO tb_employee_department (UUID_employee, UUID_department) VALUES
(1, 1), -- João Silva -> TI
(2, 1), -- Maria Oliveira -> TI
(3, 1), -- Carlos Souza -> TI
(4, 2), -- Ana Costa -> RH
(5, 2), -- Pedro Almeida -> RH
(6, 3), -- Fernanda Lima -> Financeiro
(7, 3), -- Ricardo Santos -> Financeiro
(8, 4), -- Juliana Pereira -> Marketing
(9, 4), -- Lucas Mendes -> Marketing
(10, 4); -- Patrícia Rocha -> Marketing