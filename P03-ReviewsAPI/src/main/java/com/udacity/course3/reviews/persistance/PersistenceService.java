package com.udacity.course3.reviews.persistance;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.jpa.CommentRepository;
import com.udacity.course3.reviews.repository.jpa.ProductRepository;
import com.udacity.course3.reviews.repository.jpa.ReviewRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PersistenceService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;

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

    public Optional<Review> createReviewForProduct(Integer productId, Review review) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()) {
            return Optional.empty();
        }

        review.setProduct(optionalProduct.get());
        return Optional.of(reviewRepository.save(review));
    }

    public Optional<List<Review>> listReviewsForProduct(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(optionalProduct.get().getReviews());
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public Optional<Product> findProductById(Integer id) {
        return  productRepository.findById(id);
    }

    public List<Product> listProducts() {
        return productRepository.findAll();
    }
}
