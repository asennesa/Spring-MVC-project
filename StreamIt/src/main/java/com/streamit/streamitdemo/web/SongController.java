package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.model.binding.SongBindingModel;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.service.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/songs")
public class SongController {
    public static final String UPLOAD_DIR = "src/main/resources/static/uploads";
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
                        handleMultipartFile(file);
                        songBindingModel.setSongUrl("/uploads" + "/" + file.getOriginalFilename());
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

    @GetMapping("/uploads")
    public String userUploads(Model model, Principal principal) {
//        List<SongViewModel> userUploads = this.songService.getAllSongsByUser(principal.getName());
//        model.addAttribute("firstSong", userUploads.iterator().next());
//        userUploads.remove(userUploads.iterator().next());
        model.addAttribute("allUserUploads", this.songService.getAllSongsByUser(principal.getName()));
        return "playlist";
    }

    @GetMapping("/delete/{id}")
    public String removeSong(@PathVariable("id") Long id, Principal principal) {
        this.songService.delete(id,principal.getName());
        return "redirect:/songs/uploads";
    }


    @ExceptionHandler(value = MultipartException.class)
    public ModelAndView handleFileUploadException(MultipartException ex) {

        ModelAndView modelAndVew = new ModelAndView("error");
        modelAndVew.addObject("errorMsg", ex.getMessage());

        return modelAndVew;
    }


    private void handleMultipartFile(MultipartFile file) {
        String name = file.getOriginalFilename();
        long size = file.getSize();

        try {
            File currentDir = new File(UPLOAD_DIR);
            if (!currentDir.exists()) {
                currentDir.mkdirs();
            }
            String path = currentDir.getAbsolutePath() + "/" + file.getOriginalFilename();
            path = new File(path).getAbsolutePath();

            File f = new File(path);
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(f));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}