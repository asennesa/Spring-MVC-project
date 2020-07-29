package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.model.view.UserViewModel;
import com.streamit.streamitdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public HomeController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("allUsers",this.userService.findAllUsers());
        return "index";
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView, Principal principal) {
        modelAndView.setViewName("home");
        modelAndView.addObject("allUsers",this.userService.findAllUsers());
        UserViewModel userViewModel = this.modelMapper
                .map(this.userService.findByUsername(principal.getName()),UserViewModel.class);
        modelAndView.addObject("loggedUser",userViewModel);


        modelAndView.addObject("");
        return modelAndView;
    }
}
