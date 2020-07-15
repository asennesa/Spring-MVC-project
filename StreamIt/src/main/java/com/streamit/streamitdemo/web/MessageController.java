package com.streamit.streamitdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messages")
public class MessageController {
    @GetMapping
    private String messages(){
        return "send-message";

    }

    @GetMapping("/sent")
    private String messageSent(){
        return "send-message-success";

    }

    @GetMapping("/inbox")
    private String messageBox(){
        return "message-box";
    }

    @GetMapping("/read-message")
    private String readMessage(){
        return "read-message";
    }


}
