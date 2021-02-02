package com.patternica.internship.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="avatar")
public class Avatar {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;

    public Avatar() {
    }

    public Avatar(String name) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return id.equals(avatar.id) &&
                name.equals(avatar.name) &&
                user.equals(avatar.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user);
    }
}
