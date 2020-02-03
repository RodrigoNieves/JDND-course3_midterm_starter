package com.udacity.course3.reviews.persistance;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.jpa.CommentRepository;
import com.udacity.course3.reviews.repository.jpa.ReviewRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class PersistanceService {

    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;

    public PersistanceService(
            CommentRepository commentRepository,
            ReviewRepository reviewRepository) {
        this.commentRepository = commentRepository;
        this.reviewRepository = reviewRepository;
    }

    public Optional<Comment> createCommentForReview(Integer reviewId, Comment comment) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if(!optionalReview.isPresent()) {
            return Optional.empty();
        }

        comment.setReview(optionalReview.get());
        return Optional.of(commentRepository.save(comment));
    }

    public Optional<List<Comment>> listCommentsForReview(Integer reviewId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if(!optionalReview.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(optionalReview.get().getComments());
    }
}
