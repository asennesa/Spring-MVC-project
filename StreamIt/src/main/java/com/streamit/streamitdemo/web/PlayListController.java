package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.model.binding.PlaylistBindingModel;
import com.streamit.streamitdemo.model.binding.ShowBindingModel;
import com.streamit.streamitdemo.model.service.PlaylistServiceModel;
import com.streamit.streamitdemo.model.service.ShowServiceModel;
import com.streamit.streamitdemo.model.view.SongViewModel;
import com.streamit.streamitdemo.service.PlaylistService;
import com.streamit.streamitdemo.service.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if(!model.containsAttribute("playlistBindingModel")){
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
        this.playlistService.savePlaylist(this.modelMapper.map(playlistBindingModel, PlaylistServiceModel.class),principal.getName());
        return new ModelAndView("redirect:create");

    }



    @GetMapping("/uploads")
    public String userUploads(Model model, Principal principal) {
        List<SongViewModel> userUploads = this.songService.getAllSongsByUser(principal.getName());
        model.addAttribute("firstSong", userUploads.iterator().next());
        userUploads.remove(userUploads.iterator().next());
        model.addAttribute("allUserUploads", userUploads);
        model.addAttribute("uploads",true);
        return "playlist";
    }


    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("flag",true);
        return "test";
    }


}
