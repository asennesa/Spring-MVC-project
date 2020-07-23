package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.model.binding.SongBindingModel;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.service.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
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
    private String songs(Model model) {
        if (!model.containsAttribute("songBindingModel")) {
            model.addAttribute("songBindingModel", new SongBindingModel());
        }
        return "upload-song";
    }

    @PostMapping("/upload")
    public String uploadSong(@Valid @ModelAttribute("songBindingModel") SongBindingModel songBindingModel, final BindingResult binding,
                             RedirectAttributes redirectAttributes, Principal principal,
                             @RequestParam(name = "file") MultipartFile file) {

        try {
            if (file != null && !file.isEmpty() && file.getOriginalFilename().length() > 0) {
                if (Pattern.matches(".+\\.(mp3)", file.getOriginalFilename())) {
                    if (file.getSize() > 31457280) {
                        redirectAttributes.addFlashAttribute("message", "File too large!\r\nMaximum file size = 30 Megabytes.");
                    } else {
                        songBindingModel.setSongFile(file.getBytes());
                        String name = principal.getName();
                        this.songService.saveSong(this.modelMapper.map(songBindingModel, SongServiceModel.class), principal.getName());
                        redirectAttributes.addFlashAttribute("message", "Upload successful!");
                    }
                } else {
                    redirectAttributes.addFlashAttribute("message", "Submit file [.mp3] format.");
                }
                redirectAttributes.addFlashAttribute("songBindingModel", songBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.songBindingModel", binding);
                return "redirect:upload";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "upload-song";
    }


    @ExceptionHandler(value = MultipartException.class)
    public ModelAndView handleFileUploadException(MultipartException ex) {

        ModelAndView modelAndVew = new ModelAndView("error");
        modelAndVew.addObject("errorMsg", ex.getMessage());

        return modelAndVew;
    }


}