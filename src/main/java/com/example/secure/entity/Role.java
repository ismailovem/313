package com.example.secure.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;

    public Role() {
    }

    @Override
    public String getAuthority() {
        return getName();
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return " " + name;
    }
}