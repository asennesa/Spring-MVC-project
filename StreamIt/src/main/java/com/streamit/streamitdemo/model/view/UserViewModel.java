package com.streamit.streamitdemo.model.view;

import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.model.service.UserRoleServiceModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Set;

public class UserViewModel {
    private Long id;
    private String username;
    private Set<SongServiceModel> songs;


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

    public Set<SongServiceModel> getSongs() {
        return songs;
    }

    public void setSongs(Set<SongServiceModel> songs) {
        this.songs = songs;
    }

}
