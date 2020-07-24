package com.streamit.streamitdemo.service.impl;

import com.streamit.streamitdemo.model.entity.Song;
import com.streamit.streamitdemo.model.entity.User;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.model.service.UserServiceModel;
import com.streamit.streamitdemo.repository.SongRepository;
import com.streamit.streamitdemo.repository.UserRepository;
import com.streamit.streamitdemo.service.SongService;
import com.streamit.streamitdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;

@Service
public class SongServiceImpl implements SongService {
    private SongRepository songRepository;
    private UserService userService;
    private ModelMapper modelMapper;

    public SongServiceImpl(SongRepository songRepository, UserService userService, ModelMapper modelMapper) {
        this.songRepository = songRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void saveSong(SongServiceModel songServiceModel, String username) {
        Song song = this.modelMapper.map(songServiceModel, Song.class);
        song.setUsers(new HashSet<User>());
        song.addUser(this.modelMapper.map(userService.findByUsername(username),User.class));
        this.songRepository.saveAndFlush(song);
    }
}
