package com.example.secure.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String role;
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;
    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return role;
    }

    public void setRoleName(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

}