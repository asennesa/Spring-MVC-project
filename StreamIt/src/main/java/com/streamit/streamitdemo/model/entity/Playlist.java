package com.streamit.streamitdemo.model.entity;

import java.util.List;

public class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
