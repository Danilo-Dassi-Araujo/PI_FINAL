-- Users Password: 123456@store


INSERT INTO USERS(id, name, email, cpf, password, role, is_active, created_at, updated_at) VALUES
('9c13d130-e5d8-4b35-9b5b-9460ab2acde0', 'Davi', 'davi@gmail.com', '98357967000', '$2a$10$81mbr9gP2JXx6mESAA/eT.z5/c2g3E.lFZqPz7gZqLlPBCBvtJYVq', 'CLIENT', true, now(), now()),
('67f95829-b0a4-4693-9c5b-85a389a8220e', 'Gustavo', 'gustavo@gmail.com', '18469211048', '$2a$10$81mbr9gP2JXx6mESAA/eT.z5/c2g3E.lFZqPz7gZqLlPBCBvtJYVq', 'STOCKIST', true, now(), now()),
('53868c6a-691f-42b8-86d1-092ed8ed2301', 'Pedro', 'pedro@gmail.com', '92579611096', '$2a$10$81mbr9gP2JXx6mESAA/eT.z5/c2g3E.lFZqPz7gZqLlPBCBvtJYVq', 'ADMIN', true, now(), now());

INSERT INTO PRODUCTS(id, name, description, price, rate, created_at, updated_at, is_active) VALUES
('7f69ead0-dfdb-4829-b6ef-f55365502243', 'Iphone 15', 'Smartphone da marca Apple', 10000, 4.0, now(), now(),true),
('d6bb38cc-bd6d-4cf8-9db5-240c75363d60', 'Galaxy S23', 'Smartphone da marca Samsung', 4599, 4.5, now(), now(),true);