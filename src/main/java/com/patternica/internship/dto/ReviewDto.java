package com.patternica.internship.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Objects;

public class ReviewDto {
    @ApiModelProperty(value="The owner of the comment", example="Mr. John", required=true)
    private String ownerFullName;

    @ApiModelProperty(value="AvatarId of the owner of the comment", example="3")
    private Long ownerAvatarId;

    @ApiModelProperty(value="Text of the comment", example="You're cool!", required=true)
    private String comment;

    @ApiModelProperty(value="Creation time of the comment", example="2021-01-17 12:45", required=true)
    private Date creationTime;

    @ApiModelProperty(value="Mentor's mark made by client", example="3.5", required=true)
    private Double rating;

    public ReviewDto() {
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    public Long getOwnerAvatarId() {
        return ownerAvatarId;
    }

    public void setOwnerAvatarId(Long ownerAvatarId) {
        this.ownerAvatarId = ownerAvatarId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewDto reviewDto = (ReviewDto) o;
        return ownerFullName.equals(reviewDto.ownerFullName) &&
                ownerAvatarId.equals(reviewDto.ownerAvatarId) &&
                comment.equals(reviewDto.comment) &&
                creationTime.equals(reviewDto.creationTime) &&
                Objects.equals(rating, reviewDto.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerFullName, ownerAvatarId, comment, creationTime, rating);
    }
}
