# Relationships-between-entities
In this project I show examples of how to map the following relationships between entities: 

- One to one
- One to many 
- Many to many

In order to run the code, you have to execute the following sql statement:

```
CREATE TABLE hibernaterelationshipstutorial.address (
	id int NOT NULL AUTO_INCREMENT,
    type_road varchar(20) NOT NULL,
    name_street varchar(80) NOT NULL,
    postal_code int NOT NULL,
    city varchar(80) NOT NULL,
    country varchar(80) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE hibernaterelationshipstutorial.email (
	id int NOT NULL AUTO_INCREMENT,
    email varchar(80) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE hibernaterelationshipstutorial.customer (
	id int NOT NULL AUTO_INCREMENT,
    customer_name varchar(20) NOT NULL,
    customer_surname varchar(80) NOT NULL,
    address_id int UNIQUE,
    email_id int UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (address_id) REFERENCES hibernaterelationshipstutorial.address(id),
    FOREIGN KEY (email_id) REFERENCES hibernaterelationshipstutorial.email(id)
) ENGINE = INNODB;

CREATE TABLE hibernaterelationshipstutorial.orders (
	id int NOT NULL AUTO_INCREMENT,
    date_order date,
    customer_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES hibernaterelationshipstutorial.customer(id)
) ENGINE = INNODB;

CREATE TABLE hibernaterelationshipstutorial.product (
	id int NOT NULL AUTO_INCREMENT,
    product_name varchar(20),
    price decimal(10, 2),
    PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE hibernaterelationshipstutorial.orders_product (
	id int NOT NULL AUTO_INCREMENT,
    order_id int,
    product_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES hibernaterelationshipstutorial.orders(id),
    FOREIGN KEY (product_id) REFERENCES hibernaterelationshipstutorial.product(id)
) ENGINE = INNODB;
```
