package com.streamit.streamitdemo.service.impl;

import com.streamit.streamitdemo.model.entity.Show;
import com.streamit.streamitdemo.model.entity.Song;
import com.streamit.streamitdemo.model.entity.User;
import com.streamit.streamitdemo.model.service.ShowServiceModel;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.model.view.ShowViewModel;
import com.streamit.streamitdemo.model.view.UserViewModel;
import com.streamit.streamitdemo.repository.ShowRepository;
import com.streamit.streamitdemo.service.ShowService;
import com.streamit.streamitdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {
    private ShowRepository showRepository;
    private UserService userService;
    private ModelMapper modelMapper;

    public ShowServiceImpl(ShowRepository showRepository, UserService userService, ModelMapper modelMapper) {
        this.showRepository = showRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void saveShow(ShowServiceModel showServiceModel, String username) {
        Show show = this.modelMapper.map(showServiceModel, Show.class);
        if (showRepository.findByShowName(show.getShowName()).isEmpty()) {
            show.setUsers(new HashSet<User>());
            show.addUser(this.modelMapper.map(userService.findByUsername(username), User.class));
            this.showRepository.saveAndFlush(show);
        } else {
            Show existingShow = showRepository.findByShowName(show.getShowName()).orElse(null);
            existingShow.addUser(this.modelMapper.map(userService.findByUsername(username), User.class));
            this.showRepository.saveAndFlush(existingShow);
        }
    }

    @Override
    public List<ShowViewModel> findAllShows() {
        return this.showRepository.findAll().stream().map(item -> {
            ShowViewModel showViewModel = this.modelMapper.map(item, ShowViewModel.class);
            return showViewModel;
        }).collect(Collectors.toList());
    }
}
