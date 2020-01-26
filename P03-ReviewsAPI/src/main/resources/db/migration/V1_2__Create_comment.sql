CREATE TABLE comment (
     comment_id MEDIUMINT NOT NULL AUTO_INCREMENT,
     review_id MEDIUMINT NOT NULL,
     cutomer CHAR(255) NOT NULL,
     description VARCHAR(1000) NOT NULL,
     created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (comment_id),
     constraint comment_review_fk  foreign key (comment_id) references review (review_id)
);