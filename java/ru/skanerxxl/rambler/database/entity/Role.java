package ru.skanerxxl.rambler.database.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private long id;

    private String role;

    @ManyToMany(mappedBy = "role", cascade = CascadeType.ALL)
    Set<User> user = new HashSet<>();

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public void addUser(User user) {
        this.user.add(user);
        user.getRole().add(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }

}
