package com.streamit.streamitdemo.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "playlists")
public class Playlist {
    private Long id;
    private String name;
    private String description;
    private List<Song> songs = new ArrayList<>();
    private User user;


    public Playlist() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    @Length(min = 2, message = "Name length must be at least two characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addSong(Song song) {
        this.songs.add(song);
        song.getPlaylists().add(this);
    }


}
