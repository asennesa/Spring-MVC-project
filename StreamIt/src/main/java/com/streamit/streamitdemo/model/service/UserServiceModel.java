package com.streamit.streamitdemo.model.service;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserServiceModel {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<UserRoleServiceModel> roles;
    private List<SongServiceModel> songs;
    private Set<ShowServiceModel> shows;
    private List<PlaylistServiceModel> playlists;
    private List<MessageServiceModel> messagesReceived = new ArrayList<>();
    private List<MessageServiceModel> messagesSent = new ArrayList<>();


    public UserServiceModel() {
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

    @Length(min = 2, message = "Password length must be at least two characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email(message = "Enter valid email!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<UserRoleServiceModel> getAuthorities() {
        return this.roles;
    }


    public Set<UserRoleServiceModel> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<UserRoleServiceModel> roles) {
        this.roles = roles;
    }

    public List<SongServiceModel> getSongs() {
        return songs;
    }

    public void setSongs(List<SongServiceModel> songs) {
        this.songs = songs;
    }

    public Set<ShowServiceModel> getShows() {
        return shows;
    }

    public void setShows(Set<ShowServiceModel> shows) {
        this.shows = shows;
    }

    public List<PlaylistServiceModel> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistServiceModel> playlists) {
        this.playlists = playlists;
    }

    public List<MessageServiceModel> getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(List<MessageServiceModel> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public List<MessageServiceModel> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(List<MessageServiceModel> messagesSent) {
        this.messagesSent = messagesSent;
    }
}
