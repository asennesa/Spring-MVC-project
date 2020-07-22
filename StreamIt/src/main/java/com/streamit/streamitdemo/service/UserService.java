package com.streamit.streamitdemo.service;

import com.streamit.streamitdemo.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    void register(UserServiceModel user);
    UserServiceModel findByUsername(String username);

}
