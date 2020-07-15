package com.streamit.streamitdemo.model.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "shows")
public class Show {
    private Long id;
    private String showName;
    private LocalDateTime date;
    private String venueAddress;
    private Set<User> users;


    public Show() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "show_name", nullable = false)
    @Length(min = 2, message = "Show name length must be at least 2 characters")
    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past!")
    @Column(name = "date")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Column(name = "venue_adress", nullable = false)
    @Length(min = 6, message = "Venue address length must be at least six characters")
    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAdress) {
        this.venueAddress = venueAdress;
    }

    @ManyToMany
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
