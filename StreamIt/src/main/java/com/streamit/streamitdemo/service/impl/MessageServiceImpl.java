package com.streamit.streamitdemo.service.impl;

import com.streamit.streamitdemo.model.entity.Message;
import com.streamit.streamitdemo.model.entity.User;
import com.streamit.streamitdemo.model.service.MessageServiceModel;
import com.streamit.streamitdemo.repository.MessageRepository;
import com.streamit.streamitdemo.service.MessageService;
import com.streamit.streamitdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public MessageServiceImpl(MessageRepository messageRepository, UserService userService, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void saveMessage(MessageServiceModel messageServiceModel, String username, String receiverName) {
        Message message = this.modelMapper.map(messageServiceModel, Message.class);
        message.setDate(LocalDateTime.now());
        User receiver = this.modelMapper.map(this.userService.findByUsername(receiverName), User.class);
        receiver.getMessagesReceived().add(message);
        message.setReceiver(receiver);
        message.setReceiverName(receiverName);
        User sender = this.modelMapper.map(this.userService.findByUsername(username), User.class);
        sender.getMessagesSent().add(message);
        message.setSender(sender);
        message.setSenderName(sender.getUsername());
        this.messageRepository.saveAndFlush(message);

    }

    @Override
    @Transactional
    public void deleteSentMessage(Long id) {
        Message message = this.messageRepository.findById(id).orElse(null);
        message.setSender(null);
        this.messageRepository.saveAndFlush(message);
        if (message.getReceiver() == null && message.getSender() == null) {
            this.messageRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void deleteReceivedMessage(Long id) {
        Message message = this.messageRepository.findById(id).orElse(null);
        message.setReceiver(null);
        this.messageRepository.saveAndFlush(message);
        if (message.getReceiver() == null && message.getSender() == null) {
            this.messageRepository.deleteById(id);
        }
    }

    @Override
    public MessageServiceModel findMessageById(Long id) {
        return this.modelMapper.map(this.messageRepository.findById(id), MessageServiceModel.class);
    }
}
