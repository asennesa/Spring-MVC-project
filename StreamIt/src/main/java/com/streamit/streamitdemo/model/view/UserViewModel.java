package com.streamit.streamitdemo.model.view;

import com.streamit.streamitdemo.model.entity.Playlist;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.model.service.UserRoleServiceModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserViewModel {
    private Long id;
    private String username;
    private List<SongViewModel> songs;
    private List<PlaylistViewModel> playLists;



    public UserViewModel() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Length(min = 2, message = "Username length must be at least two characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<SongViewModel> getSongs() {
        return songs;
    }

    public void setSongs(List<SongViewModel> songs) {
        this.songs = songs;
    }

    public List<PlaylistViewModel> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(List<PlaylistViewModel> playLists) {
        this.playLists = playLists;
    }
}
