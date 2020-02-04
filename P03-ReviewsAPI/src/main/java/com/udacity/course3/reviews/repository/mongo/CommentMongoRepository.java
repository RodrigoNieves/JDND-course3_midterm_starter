package com.udacity.course3.reviews.repository.mongo;

import com.udacity.course3.reviews.model.CommentMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentMongoRepository extends MongoRepository<CommentMongo,Integer> {
}
