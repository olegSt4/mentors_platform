package com.stefura.mentorsplatform.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

public class ExtendedProfileDto {
    @ApiModelProperty(value="Profile's id", required=true, example="3")
    private Long id;

    @ApiModelProperty(value="Avatar's id, that corresponds to profile owner", example="2")
    private Long avatarId;

    @ApiModelProperty(value="User's full name", required=true, example="Olezhka S.")
    private String fullName;

    @ApiModelProperty(value="User's profession", required=true, example="Software Engineer (almost)")
    private String profession;

    @ApiModelProperty(value="User's personal rating", example="1.0", allowableValues="range[1,5]")
    private Double rating;

    @ApiModelProperty(value="User's personal rate (price)", example="1.0", allowableValues="range[1, infinity]")
    private Double rate;

    @ApiModelProperty(value="The description of user's profile", required=true, example="I'm mentor! (joke)")
    private String description;

    @ApiModelProperty(value="User's phone number", example="+380-982-3232-98")
    private String phone;

    @ApiModelProperty(value="User's email", example="john2010@gmail.com", required=true)
    private String email;

    @ApiModelProperty(value="A link to user's facebook account")
    private String facebook;

    @ApiModelProperty(value="List of user's life aspects")
    private List<String> aspects;

    private List<ReviewDto> reviews;

    public ExtendedProfileDto() {
    }
    public ExtendedProfileDto(Long id, String fullName, String profession, Double rating, Double rate,
                              String description, String phone, String email) {
        this.id = id;
        this.fullName = fullName;
        this.profession = profession;
        this.rating = rating;
        this.rate = rate;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.aspects = aspects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public List<String> getAspects() {
        return aspects;
    }

    public void setAspects(List<String> aspects) {
        this.aspects = aspects;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtendedProfileDto that = (ExtendedProfileDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(avatarId, that.avatarId) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(profession, that.profession) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(rate, that.rate) &&
                Objects.equals(description, that.description) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(facebook, that.facebook) &&
                Objects.equals(aspects, that.aspects) &&
                Objects.equals(reviews, that.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatarId, fullName, profession, rating, rate, description,
                phone, email, facebook, aspects, reviews);
    }

    @Override
    public String toString() {
        return "ExtendedProfileDto{" +
                "id=" + id +
                ", avatarId=" + avatarId +
                ", fullName='" + fullName + '\'' +
                ", profession='" + profession + '\'' +
                ", rating=" + rating +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", aspects=" + aspects +
                '}';
    }
}
