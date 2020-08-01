package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.model.binding.RoleAddBindingModel;
import com.streamit.streamitdemo.service.UserRoleService;
import com.streamit.streamitdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class RoleController {

    private final UserRoleService userRoleService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public RoleController(UserRoleService userRoleService, UserService userService, ModelMapper modelMapper) {
        this.userRoleService = userRoleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/roles")
    public ModelAndView add(ModelAndView modelAndView) {
        modelAndView.addObject("usernames",this.userService.findAllUsernames());
        modelAndView.setViewName("admin-page");
        return modelAndView;
    }

    @PostMapping("/roles")
    public String addConfirm(@Valid @ModelAttribute("roleAddBindingModel") RoleAddBindingModel roleAddBindingModel){
        this.userService.addRoleToUser(roleAddBindingModel.getUsername(),roleAddBindingModel.getRole());

        return "redirect:/home";

    }




}
