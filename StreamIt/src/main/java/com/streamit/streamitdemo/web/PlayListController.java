package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.model.binding.PlaylistBindingModel;
import com.streamit.streamitdemo.model.entity.User;
import com.streamit.streamitdemo.model.service.PlaylistServiceModel;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.model.view.PlaylistViewModel;
import com.streamit.streamitdemo.model.view.SongViewModel;
import com.streamit.streamitdemo.model.view.UserViewModel;
import com.streamit.streamitdemo.service.PlaylistService;
import com.streamit.streamitdemo.service.SongService;
import com.streamit.streamitdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/playlists")
public class PlayListController {
    private final PlaylistService playlistService;
    private final SongService songService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public PlayListController(PlaylistService playlistService, SongService songService, UserService userService, ModelMapper modelMapper) {
        this.playlistService = playlistService;
        this.songService = songService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public String createPlayList(Model model) {
        if (!model.containsAttribute("playlistBindingModel")) {
            model.addAttribute("playlistBindingModel", new PlaylistBindingModel());
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
    public ModelAndView listenNow(@RequestParam("playListId") Long playListId, @RequestParam("songId") Long songId, @RequestParam("userId") Long userId, ModelAndView modelAndView, Principal principal) {
        PlaylistServiceModel playlistServiceModel = this.playlistService.findById(playListId);
        SongServiceModel songServiceModel = this.songService.findById(songId);
        this.playlistService.saveSongToPlayList(playlistServiceModel, songServiceModel, principal.getName());
        return new ModelAndView("redirect:/users/listen-now/?id=" +userId );
    }


    @GetMapping("/user-playlist")
    public ModelAndView listenToPlaylist(@RequestParam("playListId") Long id, ModelAndView modelAndView, Authentication authentication ) {
        User user = (User)authentication.getPrincipal();
        UserViewModel userViewModel =this.userService.findById(user.getId());
        List<PlaylistViewModel> playlistViewModels = userViewModel
                .getPlayLists().stream().filter(p-> p.getId().equals(id)).collect(Collectors.toList());
        modelAndView.addObject("songs", playlistViewModels.get(0).getSongs());
        modelAndView.addObject("pListId", playlistViewModels.get(0).getId());
        modelAndView.addObject("playlistFlag", true);
        modelAndView.setViewName("playlist");
        return modelAndView;
    }

    @GetMapping("/remove/{playListId}/{songId}")
    public String removeSong(@PathVariable("playListId") Long playListId, @PathVariable("songId") Long songId, Principal principal) {
        this.playlistService.removeById(playListId, songId);
        return "redirect:/playlists/user-playlist?playListId=" + playListId;
    }

    @GetMapping("/delete/{id}")
    public String deletePlaylist(@PathVariable("id") Long id, Principal principal) {
        this.playlistService.delete(id,principal.getName());
        return "redirect:/home";
    }



}
