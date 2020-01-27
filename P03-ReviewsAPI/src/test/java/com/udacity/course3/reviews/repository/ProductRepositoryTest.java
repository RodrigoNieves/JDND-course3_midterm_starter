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


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private TestEntityManager testEntityManager;
    @Autowired private ProductRepository productRepository;

    @Test
    public void injectedComponetsAreNotNull() {
        assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
        assertNotNull(entityManager);
        assertNotNull(testEntityManager);
        assertNotNull(productRepository);
    }

    @Test
    public void testFindByProductId() {
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

        assertThat(productRepository.findAll().size(), is(1));
        Optional<Product> optionalProduct = productRepository.findById(product.getProductId());
        assertTrue(optionalProduct.isPresent());
        Product retrieveProduct = optionalProduct.get();

        assertThat(retrieveProduct.getTitle(), is(product.getTitle()));

        assertThat(product.getReviews().size(), is(1));
        Review retrieveReview = product.getReviews().get(0);
        assertThat(retrieveReview.getTitle(), is(review.getTitle()));

        assertThat(retrieveReview.getComments().size(), is(1));
        Comment retrieveComment = retrieveReview.getComments().get(0);
        assertThat(retrieveComment.getDescription(), is(comment.getDescription()));
    }
}
