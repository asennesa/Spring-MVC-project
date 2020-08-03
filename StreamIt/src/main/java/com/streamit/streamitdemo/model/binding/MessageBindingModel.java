package com.streamit.streamitdemo.model.binding;

import org.hibernate.validator.constraints.Length;


public class MessageBindingModel {

    private String receiver;
    private String message;

    public MessageBindingModel() {
    }

    @Length(min = 2, message = "Message length must be at least 2 characters")
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Length(min = 2, message = "Message length must be at least 2 characters")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
