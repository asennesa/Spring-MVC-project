package com.streamit.streamitdemo.service;

import com.streamit.streamitdemo.model.service.ShowServiceModel;
import com.streamit.streamitdemo.model.view.ShowViewModel;
import com.streamit.streamitdemo.model.view.UserViewModel;

import java.util.List;

public interface ShowService {

    void saveShow(ShowServiceModel showServiceModel, String username);
    List<ShowViewModel> findAllShowsByUser(String username);
    void delete(Long id,String username);

}
