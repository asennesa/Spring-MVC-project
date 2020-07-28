package com.streamit.streamitdemo.service;

import com.streamit.streamitdemo.model.service.PlaylistServiceModel;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.model.view.SongViewModel;

public interface PlaylistService {
    void savePlaylist(PlaylistServiceModel playlistServiceModel, String username);
    PlaylistServiceModel findById(Long id);
    void saveSongToPlayList(PlaylistServiceModel playlistServiceModel, SongServiceModel songServiceModel, String username);
}
