package com.streamit.streamitdemo.model.service;

import com.streamit.streamitdemo.model.entity.Playlist;
import com.streamit.streamitdemo.model.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class SongServiceModel {
    private Long id;
    private String name;
    private byte[] songFile;
    private Set<PlaylistServiceModel> playlists;
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

    @NotNull
    public byte[] getSongFile() {
        return songFile;
    }

    public void setSongFile(byte[] songFile) {
        this.songFile = songFile;
    }

    public Set<PlaylistServiceModel> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<PlaylistServiceModel> playlists) {
        this.playlists = playlists;
    }

    public Set<UserServiceModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserServiceModel> users) {
        this.users = users;
    }
}
