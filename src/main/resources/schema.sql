CREATE TABLE springboot_user_tbl (
    id int primary key,
    name varchar(30),
    email varchar(30),
    phone varchar(15),
    password varchar
);

CREATE TABLE springboot_product_tbl (
    id int primary key,
    name varchar,
    description varchar,
    stock int,
    price float
);