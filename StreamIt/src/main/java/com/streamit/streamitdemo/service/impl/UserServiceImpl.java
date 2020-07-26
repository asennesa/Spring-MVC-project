package com.streamit.streamitdemo.service.impl;

import com.streamit.streamitdemo.model.entity.Show;
import com.streamit.streamitdemo.model.entity.Song;
import com.streamit.streamitdemo.model.entity.User;
import com.streamit.streamitdemo.model.entity.UserRole;
import com.streamit.streamitdemo.model.service.UserServiceModel;
import com.streamit.streamitdemo.model.view.UserViewModel;
import com.streamit.streamitdemo.repository.UserRepository;
import com.streamit.streamitdemo.service.UserRoleService;
import com.streamit.streamitdemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserRoleService userRoleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, UserRoleService userRoleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userRoleService = userRoleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails result = this.userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
        return result;
    }


    @Override
    public void register(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(getRolesForRegistration());
//        user.setSongs(new ArrayList<>());
        this.userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public UserServiceModel findByUsername(String username) {
        User user = userRepository.findUserByUsername(username).orElse(null);
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public Boolean isUserAlreadyRegistered(String email, String username) {
        return userRepository.findByEmail(email).isPresent() || userRepository.findUserByUsername(username).isPresent();
    }

    private Set<UserRole> getRolesForRegistration() {
        Set<UserRole> roles = new HashSet<UserRole>();

        if (this.userRepository.count() == 0) {
            roles.add(this.modelMapper.map(this.userRoleService.findRoleByName("ADMIN"), UserRole.class));
        } else {
            roles.add(this.modelMapper.map(this.userRoleService.findRoleByName("USER"), UserRole.class));
        }

        return roles;
    }

    @Override
    @Transactional
    public List<UserViewModel> findAllUsers() {
        return this.userRepository.findAll().stream().map(item -> {
            UserViewModel userViewModel = this.modelMapper.map(item, UserViewModel.class);
            return userViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public void removeShowFromUserById(Long showId, String username) {
        User user = this.userRepository.findUserByUsername(username).orElse(null);
        user.getShows().removeIf(show -> show.getId().equals(showId));
        this.userRepository.saveAndFlush(user);


    }

    @Override
    public UserViewModel findById(Long id) {
        return this.userRepository.findById(id).map(item->{
            UserViewModel userViewModel = this.modelMapper.map(item,UserViewModel.class);
            return userViewModel;
        }).orElse(null);
    }
}
