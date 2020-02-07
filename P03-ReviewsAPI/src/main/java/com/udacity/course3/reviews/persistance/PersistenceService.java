package com.udacity.course3.reviews.persistance;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.model.CommentMongo;
import com.udacity.course3.reviews.model.ReviewMongo;
import com.udacity.course3.reviews.repository.jpa.CommentRepository;
import com.udacity.course3.reviews.repository.jpa.ProductRepository;
import com.udacity.course3.reviews.repository.jpa.ReviewRepository;
import com.udacity.course3.reviews.repository.mongo.CommentMongoRepository;
import com.udacity.course3.reviews.repository.mongo.ReviewMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersistenceService {


    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final CommentMongoRepository commentMongoRepository;
    private final ReviewMongoRepository reviewMongoRepository;


    public PersistenceService(CommentRepository commentRepository,
                              ReviewRepository reviewRepository,
                              ProductRepository productRepository,
                              CommentMongoRepository commentMongoRepository,
                              ReviewMongoRepository reviewMongoRepository){
        this.commentRepository = commentRepository;
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.commentMongoRepository = commentMongoRepository;
        this.reviewMongoRepository = reviewMongoRepository;
    }

    public Optional<Comment> createCommentForReview(Integer reviewId, Comment comment) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        Optional<ReviewMongo> optionalReviewMongo = reviewMongoRepository.findByReviewId(reviewId);
        if(!optionalReview.isPresent() || !optionalReviewMongo.isPresent()) {
            return Optional.empty();
        }

        comment.setReview(optionalReview.get());
        Comment savedComment = commentRepository.save(comment);
        CommentMongo commentMongo = new CommentMongo();
        commentMongo.setCommentId(comment.getCommentId());
        commentMongo.setCustomer(comment.getCustomer());
        commentMongo.setDescription(comment.getDescription());
        commentMongo.setCreatedTime(comment.getCreatedTime());
        optionalReviewMongo.get().getComments().add(commentMongo);
        reviewMongoRepository.save(optionalReviewMongo.get());
        return Optional.of(savedComment);
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
