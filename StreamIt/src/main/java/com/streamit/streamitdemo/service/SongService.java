package com.streamit.streamitdemo.service;

import com.streamit.streamitdemo.model.binding.SongBindingModel;
import com.streamit.streamitdemo.model.service.SongServiceModel;

public interface SongService {

    void saveSong(SongServiceModel songServiceModel, String username);
}
