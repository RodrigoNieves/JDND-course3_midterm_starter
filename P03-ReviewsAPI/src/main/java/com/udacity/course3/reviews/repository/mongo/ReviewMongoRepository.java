package com.udacity.course3.reviews.repository.mongo;

import com.udacity.course3.reviews.model.ReviewMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewMongoRepository extends MongoRepository<ReviewMongo,Integer> {
}
