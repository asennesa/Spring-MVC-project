package com.streamit.streamitdemo.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "fans")
public class Fan extends BaseUser {

    private Set<Artist> followedArtists;

    public Fan() {
    }

    @ManyToMany
    public Set<Artist> getFollowedArtists() {
        return followedArtists;
    }

    public void setFollowedArtists(Set<Artist> followedArtists) {
        this.followedArtists = followedArtists;
    }
}
