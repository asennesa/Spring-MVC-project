package com.streamit.streamitdemo.model.view;

import com.streamit.streamitdemo.model.entity.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

public class MessageViewModel {
    private Long id;
    private UserViewModel sender;
    private UserViewModel receiver;
    private String message;
    private LocalDateTime date;

    public MessageViewModel() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public UserViewModel getSender() {
        return sender;
    }

    public void setSender(UserViewModel sender) {
        this.sender = sender;
    }

    public UserViewModel getReceiver() {
        return receiver;
    }

    public void setReceiver(UserViewModel receiver) {
        this.receiver = receiver;
    }

    @Length(min = 2, message = "Message length must be at least 2 characters")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
