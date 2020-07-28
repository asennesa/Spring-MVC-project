package com.streamit.streamitdemo.repository;

import com.streamit.streamitdemo.model.entity.Show;
import com.streamit.streamitdemo.model.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song,Long> {
    Optional<Song> findByName(String songName);
}
