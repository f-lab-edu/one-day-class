package com.one.user.service;

import com.one.user.mapper.UserMapper;
import com.one.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUserById(int id) {
        User user = userMapper.selectUserById(id);
        return user;
    }
}
