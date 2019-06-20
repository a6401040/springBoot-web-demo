DROP DATABASE IF EXISTS product_master;
CREATE DATABASE product_master;
CREATE TABLE product_master.t_product(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  price DOUBLE(10,2) NOT NULL DEFAULT 0
);
INSERT INTO product_master.t_product (name, price) VALUES('master', '1');


DROP DATABASE IF EXISTS product_slave;
CREATE DATABASE product_slave;
CREATE TABLE product_slave.t_product(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  price DOUBLE(10,2) NOT NULL DEFAULT 0
);
INSERT INTO product_slave.t_product (name, price) VALUES('slave', '1');
