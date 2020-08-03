package com.streamit.streamitdemo.service.impl;

import com.streamit.streamitdemo.model.entity.Playlist;
import com.streamit.streamitdemo.model.entity.Show;
import com.streamit.streamitdemo.model.entity.Song;
import com.streamit.streamitdemo.model.entity.User;
import com.streamit.streamitdemo.model.service.ShowServiceModel;
import com.streamit.streamitdemo.model.service.SongServiceModel;
import com.streamit.streamitdemo.model.service.UserServiceModel;
import com.streamit.streamitdemo.model.view.ShowViewModel;
import com.streamit.streamitdemo.model.view.UserViewModel;
import com.streamit.streamitdemo.repository.ShowRepository;
import com.streamit.streamitdemo.service.ShowService;
import com.streamit.streamitdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
    public List<ShowViewModel> findAllShowsByUser(String username) {
        return this.userService.findByUsername(username).getShows().stream().map(item -> {
            ShowViewModel showViewModel = this.modelMapper.map(item, ShowViewModel.class);
            return showViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id, String username) {
        Show show = this.showRepository.findById(id).orElse(null);

        if (show.getUsers().size() == 1) {
            this.showRepository.deleteById(id);
        } else {
            show.getUsers().removeIf(user -> user.getUsername().equals(username));
            this.userService.removeShowFromUserById(id,username);
            this.showRepository.saveAndFlush(show);

        }

    }

    @Override
    @Transactional
    public void deleteAllPastShows() {
        List<Show> pastShows = this.showRepository.findAll()
                .stream().filter(show->show.getDate().isBefore(LocalDateTime.now())).collect(Collectors.toList());
        this.showRepository.deleteInBatch(pastShows);


    }
}
