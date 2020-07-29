package com.streamit.streamitdemo.service.impl;

import com.streamit.streamitdemo.model.entity.Playlist;
import com.streamit.streamitdemo.model.entity.Song;
import com.streamit.streamitdemo.model.entity.User;
import com.streamit.streamitdemo.model.service.PlaylistServiceModel;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.model.view.UserViewModel;
import com.streamit.streamitdemo.repository.PlaylistRepository;
import com.streamit.streamitdemo.service.PlaylistService;
import com.streamit.streamitdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    @Transactional
    public void savePlaylist(PlaylistServiceModel playlistServiceModel, String username) {
        Playlist playlist = this.modelMapper.map(playlistServiceModel, Playlist.class);
        User user = this.modelMapper.map(this.userService.findByUsername(username), User.class);
        user.addPlaylist(playlist);
        this.playlistRepository.saveAndFlush(playlist);
    }

    @Override
    public PlaylistServiceModel findById(Long id) {
        return this.playlistRepository.findById(id).map(playlist -> {
            PlaylistServiceModel playlistServiceModel = this.modelMapper.map(playlist, PlaylistServiceModel.class);
            return playlistServiceModel;
        }).orElse(null);
    }

    @Override
    @Transactional
    public void saveSongToPlayList(PlaylistServiceModel playlistServiceModel, SongServiceModel songServiceModel, String username) {
        Playlist playlist = this.modelMapper.map(playlistServiceModel, Playlist.class);
        Song song = this.modelMapper.map(songServiceModel, Song.class);
        playlist.addSong(song);
        playlist.setUser(this.modelMapper.map(this.userService.findByUsername(username), User.class));
        this.playlistRepository.saveAndFlush(playlist);
    }

    @Override
    @Transactional
    public void removeById(Long playListId, Long songId) {
        Playlist playlist = this.playlistRepository.findById(playListId).orElse(null);
        playlist.getSongs().removeIf(song -> song.getId().equals(songId));
        this.playlistRepository.saveAndFlush(playlist);
    }

    @Override
    public void removeSongFromAllPlaylists(Long id) {
        List<Playlist> playlists = this.playlistRepository.findAll();
        playlists.forEach(p->p.getSongs().removeIf(song -> song.getId().equals(id)));
        this.playlistRepository.saveAll(playlists);
        this.playlistRepository.flush();


    }
}
