package com.patternica.internship.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="aspect")
public class Aspect {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @ManyToMany(mappedBy = "profileAspects", fetch = FetchType.LAZY)
    private Set<Profile> profilesWithAspect = new HashSet<>();

    public Aspect() {
    }

    public Aspect(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Profile> getProfilesWithAspect() {
        return new HashSet<>(profilesWithAspect);
    }

    public void addProfile(Profile profile) {
        if (!profilesWithAspect.contains(profile)) {
            profilesWithAspect.add(profile);

            profile.addProfileAspect(this);
        }
    }

    @Override
    public String toString() {
        return "aspect [ id: " + id +
                "name: " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aspect aspect = (Aspect) o;
        return id.equals(aspect.id) &&
                name.equals(aspect.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
