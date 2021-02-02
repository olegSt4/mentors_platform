package com.patternica.internship.models;

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

    @Column(name="birth_date")
    private Date birthDate;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="rating")
    private Double rating;

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable=false)
    private UserType type;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="registration_date")
    private Date registrationDate;

    @Column(name="last_visit_date")
    private Date lastVisitDate;

    public User() {
    }

    public User(String fullName, String email, String login, String password, UserType type) {
        this.fullName = fullName;
        this.email = email;
        this.login = login;
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
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
        return "user[ id: " + id +
                "type: " + type +
                "rating: " + rating +
                "registered: " + registrationDate +
                "last visit: " + lastVisitDate;
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
                type.equals(user.type) &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                Objects.equals(registrationDate, user.registrationDate) &&
                Objects.equals(lastVisitDate, user.lastVisitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, birthDate, email, phone, rating, type,
                login, password, registrationDate, lastVisitDate);
    }
}
