package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.model.CommentMongo;
import com.udacity.course3.reviews.persistance.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final PersistenceService persistenceService;

    public CommentsController(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    /**
     * Creates a comment for a review.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<Comment> createCommentForReview(
            @PathVariable("reviewId") Integer reviewId,
            @RequestBody Comment comment) {

        Optional<Comment> optionalComment = persistenceService.createCommentForReview(reviewId, comment);
        if(!optionalComment.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalComment.get());
    }

    /**
     * List comments for a review.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<List<CommentMongo>> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        Optional<List<CommentMongo>> optionalComments = persistenceService.listCommentsForReview(reviewId);
        if(!optionalComments.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalComments.get());
    }
}