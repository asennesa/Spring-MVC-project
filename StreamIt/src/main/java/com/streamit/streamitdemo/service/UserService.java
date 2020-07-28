package com.streamit.streamitdemo.service;

import com.streamit.streamitdemo.model.service.UserServiceModel;
import com.streamit.streamitdemo.model.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void register(UserServiceModel user);

    UserServiceModel findByUsername(String username);

    Boolean isUserAlreadyRegistered(String email, String username);

    List<UserViewModel> findAllUsers();

    void removeShowFromUserById(Long showId, String username);

    void removeSongFromUserById(Long songId, String username);

    UserViewModel findById(Long id);


}
