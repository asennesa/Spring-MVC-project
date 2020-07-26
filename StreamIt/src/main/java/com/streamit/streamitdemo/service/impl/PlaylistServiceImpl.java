package com.streamit.streamitdemo.service.impl;

import com.streamit.streamitdemo.model.entity.Playlist;
import com.streamit.streamitdemo.model.entity.User;
import com.streamit.streamitdemo.model.service.PlaylistServiceModel;
import com.streamit.streamitdemo.repository.PlaylistRepository;
import com.streamit.streamitdemo.service.PlaylistService;
import com.streamit.streamitdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository, UserService userService, ModelMapper modelMapper) {
        this.playlistRepository = playlistRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override

    public void savePlaylist(PlaylistServiceModel playlistServiceModel, String username) {
        Playlist playlist = this.modelMapper.map(playlistServiceModel,Playlist.class);
        playlist.setUser(this.modelMapper.map(this.userService.findByUsername(username), User.class));
        this.playlistRepository.saveAndFlush(playlist);
    }
}
