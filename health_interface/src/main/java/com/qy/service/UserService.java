package com.qy.service;

import com.qy.pojo.User;

public interface UserService {

    User findByUsername(String username);
}
