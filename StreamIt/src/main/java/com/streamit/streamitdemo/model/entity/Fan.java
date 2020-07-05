package com.streamit.streamitdemo.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
@Entity
@Table(name = "fans")
public class Fan extends BaseUser {

    private List<Playlist> playlists;

    public Fan() {
    }
}
