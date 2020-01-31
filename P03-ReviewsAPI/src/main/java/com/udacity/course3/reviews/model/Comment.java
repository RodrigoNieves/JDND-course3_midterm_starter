package com.udacity.course3.reviews.model;

import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Document("comments")
public class Comment {

    @Id
    private Integer commentId;

    private Review review;

    private String custmer;

    private String description;

    private Date createdTime;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getCustmer() {
        return custmer;
    }

    public void setCustmer(String custmer) {
        this.custmer = custmer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
