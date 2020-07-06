package com.streamit.streamitdemo.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;
import java.util.Set;

@Table
@Entity(name = "shows")
public class Show {
    private Long id;
    private LocalDateTime date;
    private String venueAdress;
    private Set<Artist> artists;

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


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past!")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Column(name = "venue_adress", nullable = false)
    public String getVenueAdress() {
        return venueAdress;
    }

    public void setVenueAdress(String venueAdress) {
        this.venueAdress = venueAdress;
    }

    @ManyToMany
    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

}
