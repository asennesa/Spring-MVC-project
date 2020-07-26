package com.streamit.streamitdemo.model.view;

import com.streamit.streamitdemo.model.entity.Playlist;
import com.streamit.streamitdemo.model.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

public class SongViewModel {
    private Long id;
    private String name;
    private String songUrl;


    public SongViewModel() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Length(min = 2, message = "Song name length must be at least 2 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }
}
