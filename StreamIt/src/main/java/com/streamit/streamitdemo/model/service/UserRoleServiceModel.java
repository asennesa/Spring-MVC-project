package com.streamit.streamitdemo.model.service;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

public class UserRoleServiceModel implements GrantedAuthority {
    private Long id;
    private String name;

    public UserRoleServiceModel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Length(min = 2, message = "Name length must be at least two characters")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.getName();
    }
}
