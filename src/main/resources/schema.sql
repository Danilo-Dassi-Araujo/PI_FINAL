create table IF NOT EXISTS users (
    active boolean,
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
    description varchar(255),
    name varchar(255),
    primary key (id)
);