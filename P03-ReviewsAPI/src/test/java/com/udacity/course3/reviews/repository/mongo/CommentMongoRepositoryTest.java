package com.udacity.course3.reviews.repository.mongo;

import com.mongodb.DBObject;
import com.udacity.course3.reviews.model.CommentMongo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
@ExtendWith(SpringExtension.class)
public class CommentMongoRepositoryTest {
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void testAddComment() {
        CommentMongo comment = new CommentMongo();
        comment.setCommentId(10);
        comment.setCustomer("Seller");
        comment.setDescription("Thanks");

        mongoTemplate.save(comment, "comments");

        assertThat(mongoTemplate.findAll(CommentMongo.class, "comments").size())
                .isEqualTo(1);
        assertThat(mongoTemplate.findAll(CommentMongo.class, "comments").get(0).getCommentId())
                .isEqualTo(comment.getCommentId());
        assertThat(mongoTemplate.findAll(CommentMongo.class, "comments").get(0).getDescription())
                .isEqualTo(comment.getDescription());
        assertThat(mongoTemplate.findAll(CommentMongo.class, "comments").get(0).getCustomer())
                .isEqualTo(comment.getCustomer());
    }
}
