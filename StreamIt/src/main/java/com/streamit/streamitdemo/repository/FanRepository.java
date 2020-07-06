package com.streamit.streamitdemo.repository;

import com.streamit.streamitdemo.model.entity.Fan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanRepository extends JpaRepository<Fan,Long> {
}
