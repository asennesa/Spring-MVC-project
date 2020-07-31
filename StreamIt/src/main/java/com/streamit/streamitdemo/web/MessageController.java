package com.streamit.streamitdemo.web;

import com.streamit.streamitdemo.model.binding.MessageBindingModel;
import com.streamit.streamitdemo.model.entity.User;
import com.streamit.streamitdemo.model.service.MessageServiceModel;
import com.streamit.streamitdemo.model.view.UserViewModel;
import com.streamit.streamitdemo.service.MessageService;
import com.streamit.streamitdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;
    private UserService userService;
    private ModelMapper modelMapper;

    public MessageController(MessageService messageService, UserService userService, ModelMapper modelMapper) {
        this.messageService = messageService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/send")
    public String sendMessage(Model model) {
        if (!model.containsAttribute("messageBindingModel")) {
            model.addAttribute("messageBindingModel", new MessageBindingModel());
        }
        return "send-message";
    }

    @PostMapping("/send")
    private String sendMessageConfirm(@Valid @ModelAttribute("messageBindingModel")
                                    MessageBindingModel messageBindingModel,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("messageBindingModel", messageBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.messageBindingModel", bindingResult);
            return "redirect:send";
        }

        if (!userService.isReceiverExisting(messageBindingModel.getReceiver())) {
            redirectAttributes.addFlashAttribute("messageBindingModel", messageBindingModel);
            redirectAttributes.addFlashAttribute("message", "Receiver not found.");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.messageBindingModel", bindingResult);
            return "redirect:send";
        }
        redirectAttributes.addFlashAttribute("message", "Message sent!");
        this.messageService.saveMessage(this.modelMapper.map(messageBindingModel, MessageServiceModel.class),principal.getName(),messageBindingModel.getReceiver());
        return "redirect:send";

    }


    @GetMapping("/inbox")
    private String messageBox(Model model, Authentication authentication) {
        User user = (User)authentication.getPrincipal();
        UserViewModel userViewModel = this.userService.findById(user.getId());
        model.addAttribute("sentMessages",userViewModel.getMessagesSent());
        model.addAttribute("receivedMessages",userViewModel.getMessagesReceived());
        return "message-box";
    }

    @GetMapping("/delete-sent/{id}")
    public String deleteSentMessage(@PathVariable("id") Long id, Authentication authentication) {
        this.messageService.deleteSentMessage(id);
        User user = (User)authentication.getPrincipal();
        UserViewModel userViewModel = this.userService.findById(user.getId());
        return "redirect:/messages/inbox";
    }

    @GetMapping("/delete-received/{id}")
    public String deleteReceivedMessage(@PathVariable("id") Long id, Authentication authentication) {
        this.messageService.deleteReceivedMessage(id);
        User user = (User)authentication.getPrincipal();
        UserViewModel userViewModel = this.userService.findById(user.getId());
        return "redirect:/messages/inbox";
    }


}
