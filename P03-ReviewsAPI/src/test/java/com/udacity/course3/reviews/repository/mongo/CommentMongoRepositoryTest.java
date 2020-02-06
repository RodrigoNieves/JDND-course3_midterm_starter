package com.udacity.course3.reviews.repository.mongo;

import com.udacity.course3.reviews.model.CommentMongo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CommentMongoRepositoryTest {
    @Autowired
    private CommentMongoRepository commentMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void checkMongoTemplate() {
        assertNotNull(mongoTemplate);
        assertNotNull(commentMongoRepository);
    }

    @Test
    public void testAddComment() {
        CommentMongo comment = new CommentMongo(
                10,
                "Seller",
                "Thanks");

        comment = commentMongoRepository.save(comment);

        Optional<CommentMongo> retrive = commentMongoRepository.findById(comment.getCommentId());
        assertThat(retrive.isPresent());
        assertThat(retrive.get().getCommentId())
                .isEqualTo(comment.getCommentId());
        assertThat(retrive.get().getDescription())
                .isEqualTo(comment.getDescription());
        assertThat(retrive.get().getCustomer())
                .isEqualTo(comment.getCustomer());
    }
}
