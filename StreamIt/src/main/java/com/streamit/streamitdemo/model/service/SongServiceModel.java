package com.streamit.streamitdemo.model.service;

import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

public class SongServiceModel {
    private Long id;
    private String name;
    private String songUrl;
    private List<PlaylistServiceModel> playlists;
    private Set<UserServiceModel> users;

    public SongServiceModel() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<PlaylistServiceModel> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistServiceModel> playlists) {
        this.playlists = playlists;
    }

    public Set<UserServiceModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserServiceModel> users) {
        this.users = users;
    }
}
