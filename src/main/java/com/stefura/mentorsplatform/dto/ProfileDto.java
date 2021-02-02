package com.stefura.mentorsplatform.dto;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ProfileDto {
    @NotNull
    private String description;

    private Double rate;
    private String profession;
    private String facebook;
    private Set<AspectDto> profileAspects = new HashSet<>();

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
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Set<AspectDto> getProfileAspects() {
        return profileAspects;
    }

    public void setProfileAspects(Set<AspectDto> profileAspects) {
        this.profileAspects = profileAspects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDto that = (ProfileDto) o;
        return description.equals(that.description) &&
                Objects.equals(rate, that.rate) &&
                Objects.equals(profession, that.profession) &&
                Objects.equals(facebook, that.facebook) &&
                Objects.equals(profileAspects, that.profileAspects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, rate, profession, facebook, profileAspects);
    }
}
