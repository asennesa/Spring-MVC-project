package com.streamit.streamitdemo.service;

import com.streamit.streamitdemo.model.service.MessageServiceModel;

import javax.transaction.Transactional;


public interface MessageService {
    void saveMessage(MessageServiceModel messageServiceModel,String username,String receiverName);

    void deleteSentMessage(Long id);

    @Transactional
    void deleteReceivedMessage(Long id);

    MessageServiceModel findMessageById(Long id);
}
