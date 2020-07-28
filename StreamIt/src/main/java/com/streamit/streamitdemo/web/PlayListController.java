package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.model.binding.PlaylistBindingModel;
import com.streamit.streamitdemo.model.binding.ShowBindingModel;
import com.streamit.streamitdemo.model.service.PlaylistServiceModel;
import com.streamit.streamitdemo.model.service.ShowServiceModel;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.model.view.SongViewModel;
import com.streamit.streamitdemo.model.view.UserViewModel;
import com.streamit.streamitdemo.service.PlaylistService;
import com.streamit.streamitdemo.service.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

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
    public String createPlayList(Model model) {
        if (!model.containsAttribute("playlistBindingModel")) {
            model.addAttribute("playlistBindingModel", new PlaylistBindingModel());
//            model.addAttribute("allShows",this.showService.findAllShowsByUser(principal.getName()));
        }
        return "create-playlist";
    }

    @PostMapping("/create")
    public ModelAndView createPlaylistConfirm(@Valid @ModelAttribute("showBindingModel")
                                                      PlaylistBindingModel playlistBindingModel,
                                              BindingResult bindingResult,
                                              RedirectAttributes redirectAttributes, ModelAndView modelAndView, Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("playlistBindingModel", playlistBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.playlistBindingModel", bindingResult);
            return new ModelAndView("redirect:create");
        }
        redirectAttributes.addFlashAttribute("message", "Playlist created.");
        this.playlistService.savePlaylist(this.modelMapper.map(playlistBindingModel, PlaylistServiceModel.class), principal.getName());
        return new ModelAndView("redirect:create");

    }

    @GetMapping("/add-song")
    @Transactional
    public ModelAndView listenNow(@RequestParam("playListId") Long playListId, @RequestParam("songId") Long songId, ModelAndView modelAndView, Principal principal) {
        PlaylistServiceModel playlistServiceModel = this.playlistService.findById(playListId);
        SongServiceModel songServiceModel = this.songService.findById(songId);
        this.playlistService.saveSongToPlayList(playlistServiceModel,songServiceModel,principal.getName());
        return new ModelAndView("redirect:/home");
    }



}
