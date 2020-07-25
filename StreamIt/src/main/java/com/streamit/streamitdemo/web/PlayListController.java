package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.service.PlaylistService;
import com.streamit.streamitdemo.service.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/playlists")
public class PlayListController {
    private final PlaylistService playlistService;
    private final SongService songService;
    private final ModelMapper modelMapper;

    public PlayListController(PlaylistService playlistService, SongService songService, ModelMapper modelMapper) {
        this.playlistService = playlistService;
        this.songService = songService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public String createPlayList() {
        return "create-playlist";
    }

    @GetMapping("/playlist")
    public String chosenPlaylist() {
        return "playlist";
    }

    @GetMapping("/uploads")
    public String userUploads(Model model, Principal principal) {
        model.addAttribute("allUserUploads", this.songService.getAllSongsByUser(principal.getName()));
        return "playlist";
    }


}
