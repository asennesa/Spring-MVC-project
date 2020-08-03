package com.streamit.streamitdemo.service;

import com.streamit.streamitdemo.model.service.PlaylistServiceModel;
import com.streamit.streamitdemo.model.service.SongServiceModel;

public interface PlaylistService {
    void savePlaylist(PlaylistServiceModel playlistServiceModel, String username);

    PlaylistServiceModel findById(Long id);

    void saveSongToPlayList(PlaylistServiceModel playlistServiceModel, SongServiceModel songServiceModel, String username);

    void removeById(Long playListId, Long songId);

    void removeSongFromAllPlaylists(Long id);

    void delete(Long id, String username);
}
