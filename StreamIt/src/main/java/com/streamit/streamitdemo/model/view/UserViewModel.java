package com.streamit.streamitdemo.model.view;

import org.hibernate.validator.constraints.Length;
import java.util.ArrayList;
import java.util.List;


public class UserViewModel {
    private Long id;
    private String username;
    private List<SongViewModel> songs;
    private List<PlaylistViewModel> playLists;
    private List<MessageViewModel> messagesReceived = new ArrayList<>();
    private List<MessageViewModel> messagesSent = new ArrayList<>();



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

    public List<MessageViewModel> getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(List<MessageViewModel> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public List<MessageViewModel> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(List<MessageViewModel> messagesSent) {
        this.messagesSent = messagesSent;
    }
}
