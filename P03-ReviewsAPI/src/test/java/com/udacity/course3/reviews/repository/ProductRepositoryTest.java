package com.udacity.course3.reviews.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private TestEntityManager testEntityManager;
    @Autowired private ReviewRepository reviewRepository;

    @Test
    public void injectedComponetsAreNotNull() {
        assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
        assertNotNull(entityManager);
        assertNotNull(testEntityManager);
        assertNotNull(reviewRepository);
    }
}
