package com.streamit.streamitdemo.repository;

import com.streamit.streamitdemo.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MessageRepository extends JpaRepository<Message,Long> {
}
