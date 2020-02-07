package com.udacity.course3.reviews.repository.mongo;

import com.udacity.course3.reviews.model.ReviewMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReviewMongoRepository extends MongoRepository<ReviewMongo,Integer> {
    Optional<ReviewMongo> findByReviewId(Integer reviewId);
}
