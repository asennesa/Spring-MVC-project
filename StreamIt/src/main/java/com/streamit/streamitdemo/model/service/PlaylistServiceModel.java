package com.streamit.streamitdemo.model.service;

import com.streamit.streamitdemo.model.entity.Song;
import com.streamit.streamitdemo.model.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

public class PlaylistServiceModel {
    private Long id;
    private String name;
    private String description;
    private Set<SongServiceModel> songs;
    private UserServiceModel userServiceModel;


    public PlaylistServiceModel() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Length(min = 2, message = "Name length must be at least two characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<SongServiceModel> getSongs() {
        return songs;
    }

    public void setSongs(Set<SongServiceModel> songs) {
        this.songs = songs;
    }

    public UserServiceModel getUserServiceModel() {
        return userServiceModel;
    }

    public void setUserServiceModel(UserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
