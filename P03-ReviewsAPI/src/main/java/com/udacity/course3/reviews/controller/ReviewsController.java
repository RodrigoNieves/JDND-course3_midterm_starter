package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.model.ReviewMongo;
import com.udacity.course3.reviews.persistance.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    private final PersistenceService persistenceService;

    public ReviewsController(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    /**
     * Creates a review for a product.
     * <p>
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<Review> createReviewForProduct(
            @PathVariable("productId") Integer productId,
            @RequestBody Review review) {
        Optional<Review> optionalReview = persistenceService.createReviewForProduct(productId,review);
        if(!optionalReview.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalReview.get());
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewMongo>> listReviewsForProduct(
            @PathVariable("productId") Integer productId) {
        Optional<List<ReviewMongo>> optionalReviews = persistenceService.listReviewsForProduct(productId);
        if(!optionalReviews.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalReviews.get());
    }
}