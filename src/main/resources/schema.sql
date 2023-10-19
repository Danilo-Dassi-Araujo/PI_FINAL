create table IF NOT EXISTS users (
    is_active boolean,
    created_at date,
    updated_at timestamp(6),
    id uuid not null,
    cpf varchar(255),
    email varchar(255),
    name varchar(255),
    password varchar(255),
    role varchar(255) check (role in ('ADMIN','STOCKIST','CLIENT')),
    primary key (id)
);

create table IF NOT EXISTS products (
    created_at date,
    price numeric(38,2),
    rate numeric(2,1),
    updated_at timestamp(6),
    id uuid not null,
    stock_quantity INT,
    is_active boolean,
    description varchar(255),
    name varchar(255),
    primary key (id)
);

create table IF NOT EXISTS clientes (
    id uuid PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255),
    born_date DATE,
    genre VARCHAR(10)
);

create table IF NOT EXISTS enderecos (
    id uuid PRIMARY KEY,
    cep VARCHAR(10),
    logradouro VARCHAR(255),
    numero VARCHAR(10),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    uf CHAR(2),
    id_cliente uuid,
    FOREIGN KEY (id_cliente) REFERENCES clientes (id)
);

