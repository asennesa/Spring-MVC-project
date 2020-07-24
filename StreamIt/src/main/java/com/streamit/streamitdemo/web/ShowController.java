package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.model.binding.ShowBindingModel;
import com.streamit.streamitdemo.model.service.ShowServiceModel;
import com.streamit.streamitdemo.service.ShowService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/shows")
public class ShowController {
    private final ShowService showService;
    private final ModelMapper modelMapper;

    public ShowController(ShowService showService, ModelMapper modelMapper) {
        this.showService = showService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model) {
        if(!model.containsAttribute("showBindingModel")){
            model.addAttribute("showBindingModel", new ShowBindingModel());
            model.addAttribute("allShows",this.showService.findAllShows());
        }

        return "add-show";
    }

    @PostMapping("/add")
    public ModelAndView addPost(@Valid @ModelAttribute("showBindingModel")
                                  ShowBindingModel showBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,ModelAndView modelAndView, Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("showBindingModel", showBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.showBindingModel", bindingResult);
            return new ModelAndView("redirect:add");
        }
        this.showService.saveShow(this.modelMapper.map(showBindingModel, ShowServiceModel.class),principal.getName());
        return new ModelAndView("redirect:add");

    }

}
