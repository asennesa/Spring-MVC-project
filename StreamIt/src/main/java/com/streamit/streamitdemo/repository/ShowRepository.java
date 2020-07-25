package com.streamit.streamitdemo.repository;

import com.streamit.streamitdemo.model.entity.Show;
import com.streamit.streamitdemo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {
    Optional<Show> findByShowName(String showName);




}
