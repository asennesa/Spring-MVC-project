package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.model.binding.ArtistRegisterBindingModel;
import com.streamit.streamitdemo.service.ArtistService;
import com.streamit.streamitdemo.service.FanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final ArtistService artistService;
    private final FanService fanService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(ArtistService artistService, FanService fanService, ModelMapper modelMapper) {
        this.artistService = artistService;
        this.fanService = fanService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register() {
        return "registration-type-choice";
    }

    @GetMapping("/register/artist")
    public String registerArtist() {
        return "register-artist";
    }

    @GetMapping("/register/fan")
    public String registerFan() {
        return "register-fan";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @PostMapping("/register")
//    public String registerPost(@Valid @ModelAttribute("userRegisterBindingModel")
//                                       Ar userRegisterBindingModel,
//                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
//            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",bindingResult);
//
//            return "redirect:register";
//        }
//        this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
//        return "redirect:login";
//
//    }

}
