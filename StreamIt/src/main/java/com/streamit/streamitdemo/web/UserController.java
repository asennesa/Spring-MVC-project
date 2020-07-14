package com.streamit.streamitdemo.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {


    @GetMapping("/register")
    public String register() {
        return "register";
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
