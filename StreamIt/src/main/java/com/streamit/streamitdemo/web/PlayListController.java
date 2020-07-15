package com.streamit.streamitdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/playlists")
public class PlayListController {

    @GetMapping("/create")
    public String register() {
        return "create-playlist";
    }

    @GetMapping("/playlist")
    public String chosenPlaylist() {
        return "playlist";
    }



}
