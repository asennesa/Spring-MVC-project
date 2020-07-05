package com.streamit.streamitdemo.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;
@Table
@Entity(name = "shows")
public class Show extends BaseEntity {
    private LocalDateTime date;
    private Venue venue;

    public Show() {
    }
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past!")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    @ManyToOne
    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
