package com.udacity.course3.reviews.repository.mongo;

import com.udacity.course3.reviews.model.CommentMongo;
import com.udacity.course3.reviews.model.ReviewMongo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReviewMongoRepositoryTest {
    @Autowired
    private ReviewMongoRepository reviewMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void checkInitialization() {
        assertNotNull(reviewMongoRepository);
        assertNotNull(mongoTemplate);
    }

    @Test
    public void testAddReview() {

        ReviewMongo review = new ReviewMongo();
        review.setReviewId(100);
        review.setCustomer("Rodrigo");
        review.setTitle("Good Product");
        review.setDescription("I love it!!!");
        review.setScore(5);


        CommentMongo comment = new CommentMongo();
        comment.setCustomer("Seller");
        comment.setDescription("Thanks");
        review.getComments().add(comment);

        reviewMongoRepository.save(review);


        assertThat(reviewMongoRepository.findAll().size(), is(1));
        Optional<ReviewMongo> optionalReview = reviewMongoRepository.findByReviewId(review.getReviewId());
        assertTrue(optionalReview.isPresent());
        ReviewMongo retrieveReview = optionalReview.get();
        assertThat(retrieveReview.getTitle(), is(review.getTitle()));

        assertThat(retrieveReview.getComments().size(), is(1));
        CommentMongo retrieveComment = retrieveReview.getComments().get(0);
        assertThat(retrieveComment.getDescription(), is(comment.getDescription()));
    }
}
