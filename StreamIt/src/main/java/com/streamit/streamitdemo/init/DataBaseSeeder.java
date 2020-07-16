package com.streamit.streamitdemo.init;

import com.streamit.streamitdemo.model.entity.UserRole;
import com.streamit.streamitdemo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataBaseSeeder {
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public DataBaseSeeder(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @PostConstruct
    public void seed() {
        if (this.userRoleRepository.findAll().isEmpty()) {
            UserRole userRole = new UserRole();
            userRole.setName("USER");

            UserRole adminRole = new UserRole();
            adminRole.setName("ADMIN");

            this.userRoleRepository.save(adminRole);
            this.userRoleRepository.save(userRole);
        }
    }
}
