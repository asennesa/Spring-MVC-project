package com.streamit.streamitdemo.model.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song {
    private Long id;
    private String name;
    private String songUrl;
    private Set<Playlist> playlists = new HashSet<>();
    private Set<User> users = new HashSet<>();

    public Song() {
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
    @Length(min = 2, message = "Song name length must be at least 2 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "song_url")
    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    @ManyToMany
    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "songs_users",
            joinColumns = {@JoinColumn(name = "fk_songs")},
            inverseJoinColumns = {@JoinColumn(name = "fk_users")})
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
        user.getSongs().add(this);
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getSongs().remove(this);
    }
}
