package com.streamit.streamitdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shows")
public class ShowController {

    @GetMapping("/add")
    public String chosenPlaylist() {
        return "add-show";
    }
}
