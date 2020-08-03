package com.streamit.streamitdemo.service.impl;

import com.streamit.streamitdemo.model.service.UserRoleServiceModel;
import com.streamit.streamitdemo.repository.UserRoleRepository;
import com.streamit.streamitdemo.service.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;


    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserRoleServiceModel findRoleByName(String name) {
        return this.modelMapper.map(userRoleRepository.findByName(name), UserRoleServiceModel.class);
    }
}
