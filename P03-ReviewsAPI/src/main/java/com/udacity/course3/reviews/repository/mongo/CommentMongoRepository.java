package com.udacity.course3.reviews.repository.mongo;

import com.udacity.course3.reviews.model.CommentMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CommentMongoRepository extends MongoRepository<CommentMongo,Integer> {
    Optional<CommentMongo> findByCommentId(Integer commentId);
}
