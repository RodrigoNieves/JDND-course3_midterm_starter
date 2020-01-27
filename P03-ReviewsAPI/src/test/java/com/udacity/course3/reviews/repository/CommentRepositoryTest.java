package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private TestEntityManager testEntityManager;
    @Autowired private CommentRepository commentRepository;

    @Test
    public void injectedComponetsAreNotNull() {
        assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
        assertNotNull(entityManager);
        assertNotNull(testEntityManager);
        assertNotNull(commentRepository);
    }dd .

    @Test
    public void testFindByCommentId() {
        Product product = new Product();
        product.setTitle("Product1");
        product.setDescription("Amazing new product");

        Review review = new Review();
        review.setCustomer("Rodrigo");
        review.setTitle("Good Product");
        review.setDescription("I love it!!!");
        review.setScore(5);
        review.setProduct(product);
        product.getReviews().add(review);

        Comment comment = new Comment();
        comment.setCustmer("Seller");
        comment.setDescription("Thanks");
        comment.setReview(review);
        review.getComments().add(comment);

        entityManager.persist(product);

        assertThat(commentRepository.findAll().size(), is(1));
        Optional<Comment> optionalComment = commentRepository.findById(comment.getCommentId());
        assertTrue(optionalComment.isPresent());
        Comment retrieveComment = optionalComment.get();

        assertThat(retrieveComment.getDescription(), is(comment.getDescription()));

        Review retrieveReview = retrieveComment.getReview();
        assertThat(retrieveReview.getTitle(), is(review.getTitle()));

        Product retrieveProduct = retrieveReview.getProduct();
        assertThat(retrieveProduct.getTitle(), is(product.getTitle()));
    }
}
