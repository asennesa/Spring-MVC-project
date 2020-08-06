package com.streamit.streamitdemo.model.responsemodels;

import java.time.LocalDateTime;

public class ShowResponseModel {
    private Long id;
    private String showName;
    private LocalDateTime date;
    private String venueAddress;

    public ShowResponseModel() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }
}
