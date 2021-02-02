package com.patternica.internship.dto;

import com.patternica.internship.models.UserType;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class NewUserDto {
    @NotNull
    @ApiModelProperty(value="User's email. Used as a login", example="olezhka2010@gmail.com", required=true)
    private String email;

    @NotNull
    @ApiModelProperty(value="User's login. Used for authentication", example="user5541", required=true)
    private String login;

    @NotNull
    @ApiModelProperty(value="User's password. Used for authentication", example="qwerty123", required=true)
    private String password;

    @NotNull
    @ApiModelProperty(value="User's full name", example="Olezhka S.", required=true)
    private String fullName;

    @ApiModelProperty(value="User's birth date")
    private Date birthDate;

    @ApiModelProperty(value="User's phone number", example="+380-982-3232-98")
    private String phone;

    @ApiModelProperty(value="User's personal rating", example="4.5", allowableValues = "range[1,5]")
    private Double rating;

    @NotNull
    @ApiModelProperty(value="User's type", example="MENTOR", allowableValues="[MENTOR, CLIENT]", required=true)
    private UserType type;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewUserDto that = (NewUserDto) o;
        return email.equals(that.email) &&
                login.equals(that.login) &&
                password.equals(that.password) &&
                fullName.equals(that.fullName) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(rating, that.rating) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, login, password, fullName, birthDate, phone, rating, type);
    }
}
