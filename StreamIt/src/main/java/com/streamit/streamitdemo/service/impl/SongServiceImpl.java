package com.streamit.streamitdemo.service.impl;

import com.streamit.streamitdemo.model.entity.Song;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.repository.SongRepository;
import com.streamit.streamitdemo.service.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {
    private SongRepository songRepository;
    private ModelMapper modelMapper;

    public SongServiceImpl(SongRepository songRepository, ModelMapper modelMapper) {
        this.songRepository = songRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveSong(SongServiceModel songServiceModel) {
        this.songRepository.saveAndFlush(this.modelMapper.map(songServiceModel, Song.class));
    }
}
