package com.stefura.mentorsplatform.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

public class MentorDto {
    @ApiModelProperty(value="User's id. Generated automatically", example="1", required=true)
    private Long id;

    @ApiModelProperty(value="Avatar's id, that corresponds to profile owner", example="12")
    private Long avatarId;

    @ApiModelProperty(value="User's full name", example="Olezhka S", required=true)
    private String fullName;

    @ApiModelProperty(value="User's profession", example="Software Engineer (almost)", required=true)
    private String profession;

    @ApiModelProperty(value="User's personal rating", example="1.0", allowableValues = "range[1,5]")
    private Double rating;

    public MentorDto() {
    }

    public MentorDto(Long id, String fullName, String profession, Double rating) {
        this.id = id;
        this.fullName = fullName;
        this.profession = profession;
        this.rating = rating;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MentorDto mentorDto = (MentorDto) o;
        return id == mentorDto.id &&
                avatarId == mentorDto.avatarId &&
                Double.compare(mentorDto.rating, rating) == 0 &&
                fullName.equals(mentorDto.fullName) &&
                profession.equals(mentorDto.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatarId, fullName, profession, rating);
    }

    @Override
    public String toString() {
        return "MentorDto{" +
                "id=" + id +
                ", avatarId=" + avatarId +
                ", fullName='" + fullName + '\'' +
                ", profession='" + profession + '\'' +
                ", rating=" + rating +
                '}';
    }
}
