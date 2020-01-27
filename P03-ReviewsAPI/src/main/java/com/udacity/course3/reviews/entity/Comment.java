package com.udacity.course3.reviews.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Integer commentId;

    @ManyToOne
    private Review review;

    @Column(name="customer")
    private String custmer;

    @Column(name="description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_time")
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
