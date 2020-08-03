package com.streamit.streamitdemo.model.binding;

import org.hibernate.validator.constraints.Length;


public class SongBindingModel {
    private String name;
    private String songUrl;

    public SongBindingModel() {
    }

    @Length(min = 2, message = "Song name length must be at least 2 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }
}
