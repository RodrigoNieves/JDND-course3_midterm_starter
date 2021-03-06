CREATE TABLE review (
     review_id INT NOT NULL AUTO_INCREMENT,
     product_id INT NOT NULL,
     title CHAR(255) NOT NULL,
     customer CHAR(255) DEFAULT '(Anonymous)',
     description VARCHAR(1000) NOT NULL,
     score TINYINT DEFAULT 0,
     created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (review_id),
     constraint review_product_fk  foreign key (product_id) references product (product_id)
);