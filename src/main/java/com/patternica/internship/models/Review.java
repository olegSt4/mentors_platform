package com.patternica.internship.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="comment")
    private String comment;

    @Column(name="creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Column(name="rating")
    private Double rating;

    @ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
    @JoinColumn(name="profile_id", referencedColumnName="id")
    private Profile profile;

    @ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
    @JoinColumn(name="owner_id", referencedColumnName="id")
    private User user;

    public Review() {
    }

    public Review(String comment, Date creationTime, Double rating) {
        this.comment = comment;
        this.creationTime = creationTime;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id.equals(review.id) &&
                comment.equals(review.comment) &&
                creationTime.equals(review.creationTime) &&
                Objects.equals(rating, review.rating) &&
                profile.equals(review.profile) &&
                user.equals(review.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, creationTime, rating, profile, user);
    }
}
