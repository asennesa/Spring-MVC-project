package com.streamit.streamitdemo.model.view;

import com.streamit.streamitdemo.model.entity.Song;
import com.streamit.streamitdemo.model.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistViewModel {
    private Long id;
    private String name;
    private String description;
    private List<SongViewModel> songs = new ArrayList<>();
    private UserViewModel user;


    public PlaylistViewModel() {
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


    public List<SongViewModel> getSongs() {
        return songs;
    }

    public void setSongs(List<SongViewModel> songs) {
        this.songs = songs;
    }


    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


