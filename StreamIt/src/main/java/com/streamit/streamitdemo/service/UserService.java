package com.streamit.streamitdemo.service;

import com.streamit.streamitdemo.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(UserServiceModel user);
}
