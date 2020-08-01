package com.streamit.streamitdemo.model.binding;

import org.hibernate.validator.constraints.Length;

public class RoleAddBindingModel {
    private String username;
    private String role;

    public RoleAddBindingModel() {
    }
    @Length(min = 2, message = "Username length must be at least two characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
