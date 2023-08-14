create table if not exists role
(
    id serial not null
    constraint role_pkey
    primary key,
    name varchar(10) not null
    );

create unique index if not exists role_name_uindex
    on role (name);

create table if not exists department
(
    id serial not null
    constraint department_pkey
    primary key,
    name varchar(10) not null
    );

create unique index if not exists department_name_uindex
    on department (name);

create table if not exists status
(
    id serial not null
    constraint status_pkey
    primary key,
    name varchar(10) not null
    );

create unique index if not exists status_name_uindex
    on status (name);

create table if not exists salary
(
    id serial not null
    constraint salary_pkey
    primary key,
    quantity integer not null
);

create table if not exists employee
(
    id serial not null
    constraint employee_pkey
    primary key,
    name varchar(50) not null,
    birthday date not null,
    role_id integer not null
    constraint employee_role_id_fk
    references role,
    department_id integer not null
    constraint employee_department_id_fk
    references department,
    salary_id integer not null
    constraint employee_salary_id_fk
    references salary
    );

create table if not exists account
(
    id serial not null
    constraint account_pkey
    primary key,
    login varchar(20) not null,
    pass varchar not null,
    employee_id integer not null
    constraint account_employee_id_fk
    references employee
    );

create unique index if not exists account_login_uindex
    on account (login);

create unique index if not exists account_employee_id_uindex
    on account (employee_id);

create unique index if not exists employee_salary_id_uindex
    on employee (salary_id);

create table if not exists holiday
(
    id serial not null
    constraint holiday_pkey
    primary key,
    date_from date not null,
    date_to date not null,
    status_id integer not null
    constraint holiday_status_id_fk
    references status,
    employee_id integer not null
    constraint holiday_employee_id_fk
    references employee
);