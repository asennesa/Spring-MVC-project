package com.streamit.streamitdemo.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist extends BaseUser {

    private List<Song> songs;
    private List<Playlist> playlists;
    private List<Show> shows;
    private Long followers;
    private List<Venue>venues;

    public Artist() {
    }
}
