CREATE TABLE product (
     product_id INT NOT NULL AUTO_INCREMENT,
     title CHAR(255) NOT NULL,
     description VARCHAR(1000) NOT NULL,
     created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     stock INT NOT NULL DEFAULT 0,
     PRIMARY KEY (product_id)
);