CREATE TABLE review (
     review_id MEDIUMINT NOT NULL AUTO_INCREMENT,
     product_id MEDIUMINT NOT NULL,
     title CHAR(255) NOT NULL,
     cutomer CHAR(255) NOT NULL,
     description VARCHAR(1000) NOT NULL,
     score TINYINT NOT NULL DEFAULT 0,
     created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (review_id),
     constraint review_product_fk  foreign key (product_id) references product (product_id)
);