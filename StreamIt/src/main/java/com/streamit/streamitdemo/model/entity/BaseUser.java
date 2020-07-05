
package com.streamit.streamitdemo.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;


@MappedSuperclass
public abstract class BaseUser {

    private Long id;
    private String email;
    private String password;
    private Role role;
    private byte[] profilePicture;
    private String displayName;


    public BaseUser() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Email
    @Column(name = "email",nullable = false,unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "password",nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    @Lob
    @Column(name = "profile_picture", columnDefinition="BLOB")
    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
    @Column(name = "display_name",nullable = false)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}