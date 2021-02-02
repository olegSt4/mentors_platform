package com.patternica.internship.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class UserDto {
    @ApiModelProperty(value="User's id. Generated automatically", example="2")
    private Long id;

    @NotNull
    @ApiModelProperty(value="User's full name", example="Olezhka S.", required=true)
    private String fullName;

    @NotNull
    @ApiModelProperty(value="User's email", example="olezhka2010@gmail.com", required=true)
    private String email;

    @ApiModelProperty(value="User's birth date")
    private Date birthDate;

    @ApiModelProperty(value="User's phone number", example="+380-982-3232-98")
    private String phone;

    @ApiModelProperty(value="User's personal rating", example="4.5", allowableValues = "range[1,5]")
    private Double rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        UserDto userDto = (UserDto) o;
        return id.equals(userDto.id) &&
                fullName.equals(userDto.fullName) &&
                Objects.equals(birthDate, userDto.birthDate) &&
                email.equals(userDto.email) &&
                Objects.equals(phone, userDto.phone) &&
                Objects.equals(rating, userDto.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, birthDate, email, phone, rating);
    }
}
