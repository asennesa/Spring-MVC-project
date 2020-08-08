package com.streamit.streamitdemo.model.service;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;
import java.util.Set;

public class ShowServiceModel {
    private Long id;
    private String showName;
    private LocalDateTime date;
    private String venueAddress;
    private Set<UserServiceModel> users;


    public ShowServiceModel() {
    }

    public ShowServiceModel(Long id, String showName, LocalDateTime date, String venueAddress, Set<UserServiceModel> users) {
        this.id = id;
        this.showName = showName;
        this.date = date;
        this.venueAddress = venueAddress;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Length(min = 2, message = "Show name length must be at least 2 characters")
    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past!")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    @Length(min = 6, message = "Venue address length must be at least six characters")
    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public Set<UserServiceModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserServiceModel> users) {
        this.users = users;
    }
}
