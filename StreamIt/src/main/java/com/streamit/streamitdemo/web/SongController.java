package com.streamit.streamitdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/songs")
public class SongController {

    @GetMapping("/upload")
    public String chosenPlaylist() {
        return "upload-song";
    }
}
