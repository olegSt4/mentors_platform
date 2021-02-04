package com.stefura.mentorsplatform.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class NewReviewDto {
    @ApiModelProperty(value="Text of the comment", example="You're cool!", required=true)
    @NotNull
    private String comment;

    @ApiModelProperty(value="Mentor's mark made by client", example="3.5", required=true)
    private Double rating;

    public NewReviewDto() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        NewReviewDto that = (NewReviewDto) o;
        return  comment.equals(that.comment) &&
                Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, rating);
    }
}
