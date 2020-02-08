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

import java.util.ArrayList;
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

        // Save comment in MySQL
        comment.setReview(optionalReview.get());
        Comment savedComment = commentRepository.save(comment);

        // Back up comment on MongoDB
        ReviewMongo reviewMongo = optionalReviewMongo.get();
        CommentMongo commentMongo = new CommentMongo();
        commentMongo.setCommentId(savedComment.getCommentId());
        commentMongo.setCustomer(savedComment.getCustomer());
        commentMongo.setDescription(savedComment.getDescription());
        commentMongo.setCreatedTime(savedComment.getCreatedTime());
        reviewMongo.getComments().add(commentMongo);
        commentMongoRepository.save(commentMongo);
        reviewMongoRepository.save(reviewMongo);
        return Optional.of(savedComment);
    }

    public Optional<List<CommentMongo>> listCommentsForReview(Integer reviewId) {
        Optional<ReviewMongo> optionalReview = reviewMongoRepository.findByReviewId(reviewId);
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

        // Save review in MySQLt
        review.setProduct(optionalProduct.get());
        Review savedReview = reviewRepository.save(review);

        // Backup review in MongoDB
        ReviewMongo reviewMongo = new ReviewMongo();
        reviewMongo.setReviewId(savedReview.getReviewId());
        reviewMongo.setCustomer(savedReview.getCustomer());
        reviewMongo.setDescription(savedReview.getDescription());
        reviewMongo.setScore(savedReview.getScore());
        reviewMongo.setTitle(savedReview.getTitle());
        reviewMongo.setCreatedTime(savedReview.getCreatedTime());
        reviewMongoRepository.save(reviewMongo);
        return Optional.of(savedReview);
    }

    public Optional<List<ReviewMongo>> listReviewsForProduct(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()) {
            return Optional.empty();
        }

        List<ReviewMongo> reviews = new ArrayList<>();
        for (Review r: optionalProduct.get().getReviews()) {
            Optional<ReviewMongo> reviewMongo = reviewMongoRepository.findByReviewId(r.getReviewId());
            if (reviewMongo.isPresent()) {
                reviews.add(reviewMongo.get());
            }
        }
        return Optional.of(reviews);
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
