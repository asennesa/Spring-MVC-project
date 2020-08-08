package com.streamit.streamitdemo.service;

import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.model.view.SongViewModel;

import java.util.List;


public interface SongService {

    void saveSong(SongServiceModel songServiceModel, String username);

    List<SongViewModel> getAllSongsByUser(String username);

    SongServiceModel findById(Long id);

    void delete(Long id, String username);

    boolean isNewUpload(String name);
}
