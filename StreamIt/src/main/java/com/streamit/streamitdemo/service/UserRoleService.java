package com.streamit.streamitdemo.service;

import com.streamit.streamitdemo.model.service.UserRoleServiceModel;

public interface UserRoleService {
    UserRoleServiceModel findRoleByName(String name);
}
