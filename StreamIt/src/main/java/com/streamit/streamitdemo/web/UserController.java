package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.model.binding.UserRegisterBindingModel;
import com.streamit.streamitdemo.model.service.UserServiceModel;
import com.streamit.streamitdemo.model.view.UserViewModel;
import com.streamit.streamitdemo.service.SongService;
import com.streamit.streamitdemo.service.UserService;
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
            redirectAttributes.addFlashAttribute("message","Passwords do not match.");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        if(userService.isUserAlreadyRegistered(userRegisterBindingModel.getEmail(),userRegisterBindingModel.getUsername())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("message","Username and/or email already in use.");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";

    }
    @Transactional
    @GetMapping("/listen-now")
    public ModelAndView listenNow(@RequestParam("id") Long id,ModelAndView modelAndView,Principal principal) {
        UserViewModel userViewModel =this.userService.findById(id);
        modelAndView.addObject("selectedUserProfileName",userViewModel.getUsername());
        modelAndView.addObject("currentLoggedUserName",principal.getName());
        UserViewModel loggedUser =this.modelMapper.map(this.userService.findByUsername(principal.getName()),UserViewModel.class);
        modelAndView.addObject("loggedUserPlaylists",loggedUser.getPlayLists());

        modelAndView.addObject("allUserUploads", this.songService.getAllSongsByUser(userViewModel.getUsername()));
        modelAndView.setViewName("playlist");
        return modelAndView;
    }
//    @GetMapping("/user-profile/{id}")
//    public String chosenPlaylist(@PathVariable("id") Long id, Model model) {
//        UserViewModel userViewModel =this.userService.findById(id);
//        List<SongViewModel> userUploads = this.songService.getAllSongsByUser(userViewModel.getUsername());
//        model.addAttribute("firstSong", userUploads.iterator().next());
//        userUploads.remove(userUploads.iterator().next());
//        model.addAttribute("allUserUploads", userUploads);
//        model.addAttribute("uploads",true);
//        return "playlist";
//
//
//    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/admin")
    public String adminPage() {
        return "admin-page";
    }


}
