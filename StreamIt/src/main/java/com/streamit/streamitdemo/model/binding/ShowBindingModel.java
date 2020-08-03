package com.streamit.streamitdemo.model.binding;


import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;


public class ShowBindingModel {
    private String showName;
    private LocalDateTime date;
    private String venueAddress;

    public ShowBindingModel() {
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


}
