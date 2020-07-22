package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.model.binding.SongBindingModel;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.service.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final ModelMapper modelMapper;

    public SongController(SongService songService, ModelMapper modelMapper) {
        this.songService = songService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/upload")
    private String songs() {
        return "upload-song";
    }

    @PostMapping("/upload")
    public String uploadSong(@Valid @ModelAttribute("songBindingModel") SongBindingModel songBindingModel, final BindingResult binding,
                             RedirectAttributes redirectAttributes, Principal principal,
                             @RequestParam(name = "file") MultipartFile file) {

        try {
            if (file != null && !file.isEmpty() && file.getOriginalFilename().length() > 0) {
                if (Pattern.matches(".+\\.(mp3)", file.getOriginalFilename())) {
                    songBindingModel.setSongFile(file.getBytes());
                    String name = principal.getName();
                    this.songService.saveSong(this.modelMapper.map(songBindingModel, SongServiceModel.class),principal.getName());

                    redirectAttributes.addFlashAttribute("message", "Upload successful!");

                } else {
                    redirectAttributes.addFlashAttribute("errorMessages", "Submit file [.mp3] format.");
                    redirectAttributes.addFlashAttribute("songBindingModel", songBindingModel);
                    redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offer", binding);
                    return "redirect:upload";
                }
            }
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessages", ex.getMessage());
            redirectAttributes.addFlashAttribute("songBindingModel", songBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offer", binding);
            return "redirect:upload";

        }


        return "upload-song";
    }
}
