CREATE TABLE comment (
     comment_id INT NOT NULL AUTO_INCREMENT,
     review_id INT NOT NULL,
     customer CHAR(255) DEFAULT '(Anonymous)',
     description VARCHAR(1000) NOT NULL,
     created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (comment_id),
     constraint comment_review_fk  foreign key (comment_id) references review (review_id)
);