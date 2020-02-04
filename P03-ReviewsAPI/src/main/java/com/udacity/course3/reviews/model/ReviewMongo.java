package com.udacity.course3.reviews.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document("reviews")
public class ReviewMongo {

    @Id
    private Integer reviewId;

    @NotEmpty(message = "Please provide a review's title")
    private String title;

    private String customer;

    @NotEmpty(message = "Please provide a review's description")
    private String description;

    private Integer score;

    @CreatedDate
    private Date createdTime;

    private List<CommentMongo> comments = new ArrayList<>();

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public List<CommentMongo> getComments() {
        return comments;
    }

    public void setComments(List<CommentMongo> comments) {
        this.comments = comments;
    }
}
