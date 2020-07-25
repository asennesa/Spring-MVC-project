package com.streamit.streamitdemo.service;

import com.streamit.streamitdemo.model.binding.SongBindingModel;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.model.view.SongViewModel;

import java.util.Set;

public interface SongService {

    void saveSong(SongServiceModel songServiceModel, String username);
    Set<SongViewModel> getAllSongsByUser(String username);
}
