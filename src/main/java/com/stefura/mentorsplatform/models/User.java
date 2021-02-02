package com.stefura.mentorsplatform.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="full_name", nullable=false)
    private String fullName;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="password", nullable=false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable=false)
    private UserType type;

    @Column(name="birth_date")
    private Date birthDate;

    @Column(name="phone")
    private String phone;

    @Column(name="rating")
    private Double rating;

    @Column(name="ratings_count")
    private Long ratingsCount;

    @Column(name="registration_date")
    private Date registrationDate;

    @Column(name="last_visit_date")
    private Date lastVisitDate;

    public User() {
    }

    public User(String fullName, String email, String password, UserType type) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.type = type;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
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

    public Long getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Long ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(Date lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", rating=" + rating +
                ", ratingsCount=" + ratingsCount +
                ", type=" + type +
                ", registrationDate=" + registrationDate +
                ", lastVisitDate=" + lastVisitDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                fullName.equals(user.fullName) &&
                Objects.equals(birthDate, user.birthDate) &&
                email.equals(user.email) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(rating, user.rating) &&
                Objects.equals(ratingsCount, user.ratingsCount) &&
                type == user.type &&
                password.equals(user.password) &&
                registrationDate.equals(user.registrationDate) &&
                lastVisitDate.equals(user.lastVisitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, birthDate, email, phone, rating, ratingsCount, type, password, registrationDate, lastVisitDate);
    }
}
