CREATE TABLE product (
     product_id INT NOT NULL AUTO_INCREMENT,
     title CHAR(255) NOT NULL,
     description VARCHAR(1000) DEFAULT '(Empty)',
     created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     stock INT DEFAULT 0,
     PRIMARY KEY (product_id)
);