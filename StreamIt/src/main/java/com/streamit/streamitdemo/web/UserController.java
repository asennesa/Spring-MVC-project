package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.model.binding.UserRegisterBindingModel;
import com.streamit.streamitdemo.model.entity.User;
import com.streamit.streamitdemo.model.service.UserServiceModel;
import com.streamit.streamitdemo.model.view.UserViewModel;
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


import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SongService songService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, SongService songService, ModelMapper modelMapper) {
        this.userService = userService;
        this.songService = songService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel")
                                          UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("message", "Passwords do not match.");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        if (userService.isUserAlreadyRegistered(userRegisterBindingModel.getEmail(), userRegisterBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("message", "Username and/or email already in use.");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";

    }


    @GetMapping("/listen-now")
    public ModelAndView listenNow(@RequestParam("id") Long id, ModelAndView modelAndView, Authentication authentication) {
        User user = (User)authentication.getPrincipal();
        UserViewModel targetUser = this.userService.findById(id);
        modelAndView.addObject("selectedUserProfileName", targetUser.getUsername());
        modelAndView.addObject("currentLoggedUserName", authentication.getName());
        modelAndView.addObject("currentLoggedUserId", id);
        UserViewModel loggedUser = this.userService.findById(user.getId());
        modelAndView.addObject("loggedUserPlaylists", loggedUser.getPlayLists());
        modelAndView.addObject("songs", this.songService.getAllSongsByUser(targetUser.getUsername()));
        modelAndView.setViewName("playlist");
        return modelAndView;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/admin")
    public String adminPage() {
        return "admin-page";
    }


}
