package com.streamit.streamitdemo.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song {
    private Long id;
    private String name;
    private String songUrl;
    private List<Playlist> playlists = new ArrayList<>();
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

    @ManyToMany(mappedBy = "songs")
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    @ManyToMany
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
