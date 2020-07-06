package com.streamit.streamitdemo.repository;

import com.streamit.streamitdemo.model.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
}
