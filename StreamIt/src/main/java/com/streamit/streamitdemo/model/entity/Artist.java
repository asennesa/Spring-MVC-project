package com.streamit.streamitdemo.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Blob;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "artists")
public class Artist extends BaseUser {
    private Set<Song> songs;
    private Set<Show> shows;
    private Set<Fan> followers;
    private String musicLabels;


    public Artist() {
    }


    @ManyToMany(mappedBy = "artists")
    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    @ManyToMany(mappedBy = "artists")
    public Set<Show> getShows() {
        return shows;
    }

    public void setShows(Set<Show> shows) {
        this.shows = shows;
    }

    @ManyToMany(mappedBy = "followedArtists")
    public Set<Fan> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Fan> followers) {
        this.followers = followers;
    }
    @Column(name = "music_labels")
    @Length(min=2,message = "Label name length must be at least two characters")
    public String getMusicLabels() {
        return musicLabels;
    }

    public void setMusicLabels(String musicLabels) {
        this.musicLabels = musicLabels;
    }
}
