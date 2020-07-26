package com.streamit.streamitdemo.service;

import com.streamit.streamitdemo.model.service.PlaylistServiceModel;

public interface PlaylistService {
    void savePlaylist(PlaylistServiceModel playlistServiceModel, String username);
}
