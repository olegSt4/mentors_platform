package com.patternica.internship.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="profile")
public class Profile {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="rate")
    private Double rate;

    @Column(name="profession")
    private String profession;

    @Column(name="facebook")
    private String facebook;

    @Column(name="views_count")
    private Long viewsCount;

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
       name="profile_aspect",
       joinColumns = @JoinColumn(name="profile_id"),
       inverseJoinColumns = @JoinColumn(name="aspect_id"))
    private Set<Aspect> profileAspects = new HashSet<>();

    @OneToMany(mappedBy="profile", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Review> reviews;

    public Profile() {
    }

    public Profile(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getFacebook() {
        return this.facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Long getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Long viewsCount) {
        this.viewsCount = viewsCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Aspect> getProfileAspects() {
        return new HashSet<>(profileAspects);
    }

    public void addProfileAspect(Aspect aspect) {
        if (!profileAspects.contains(aspect)) {
            profileAspects.add(aspect);

            aspect.addProfile(this);
        }
    }

    public List<Review> getProfileReviews() {
        return new ArrayList<>(reviews);
    }

    public void addProfileReview(Review review) {
        if (!reviews.contains(review)) {
            reviews.add(review);

            review.setProfile(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        description.append("profile [ id: ");
        description.append(id);
        description.append(" rate: ");
        description.append(rate);
        description.append(" profession: ");
        description.append(profession);
        description.append(" facebook: ");
        description.append(facebook);
        description.append(" user id: ");
        description.append(user.getId());
        description.append(" aspects: ");

        for (Aspect aspect : profileAspects) {
            description.append(aspect);
            description.append(" ");
        }

        return description.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) &&
                description.equals(profile.description) &&
                Objects.equals(rate, profile.rate) &&
                profession.equals(profile.profession) &&
                Objects.equals(facebook, profile.facebook) &&
                viewsCount.equals(profile.viewsCount) &&
                Objects.equals(user, profile.user) &&
                Objects.equals(profileAspects, profile.profileAspects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, rate, profession, facebook, viewsCount, user, profileAspects);
    }
}
