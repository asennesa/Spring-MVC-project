package com.streamit.streamitdemo.service.impl;

import com.streamit.streamitdemo.model.entity.Song;
import com.streamit.streamitdemo.model.entity.User;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.model.view.SongViewModel;
import com.streamit.streamitdemo.repository.SongRepository;
import com.streamit.streamitdemo.service.PlaylistService;
import com.streamit.streamitdemo.service.SongService;
import com.streamit.streamitdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {
    private SongRepository songRepository;
    private UserService userService;
    private PlaylistService playlistService;
    private ModelMapper modelMapper;

    public SongServiceImpl(SongRepository songRepository, UserService userService, PlaylistService playlistService, ModelMapper modelMapper) {
        this.songRepository = songRepository;
        this.userService = userService;
        this.playlistService = playlistService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void saveSong(SongServiceModel songServiceModel, String username) {
        Song song = this.modelMapper.map(songServiceModel, Song.class);
        if (songRepository.findByName(song.getName()).isEmpty()) {
            song.setUsers(new HashSet<User>());
            song.addUser(this.modelMapper.map(userService.findByUsername(username), User.class));
            this.songRepository.saveAndFlush(song);
        } else {
            Song existingSong = songRepository.findByName(song.getName()).orElse(null);
            existingSong.addUser(this.modelMapper.map(userService.findByUsername(username), User.class));
            this.songRepository.saveAndFlush(existingSong);
        }

    }

    @Override
    public List<SongViewModel> getAllSongsByUser(String username) {
        return this.userService.findByUsername(username).getSongs().stream().map(item -> {
            SongViewModel songViewModel = this.modelMapper.map(item, SongViewModel.class);
            return songViewModel;
        }).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public SongServiceModel findById(Long id) {
        return this.songRepository.findById(id).map(item -> {
            SongServiceModel songServiceModel = this.modelMapper.map(item, SongServiceModel.class);
            return songServiceModel;
        }).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id, String username) {
        Song song = this.songRepository.findById(id).orElse(null);

        if (song.getUsers().size() == 1) {
            this.playlistService.removeSongFromAllPlaylists(id);
            this.songRepository.deleteById(id);
        } else {
            song.getUsers().removeIf(user -> user.getUsername().equals(username));
            this.userService.removeSongFromUserById(id, username);
            this.songRepository.saveAndFlush(song);

        }

    }
}
