package com.one.user.service;

import com.one.user.mapper.UserMapper;
import com.one.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor // final이나 @NotNull이 붙은 필드의 생성자를 생성해주는 롬복 어노테이션
public class UserService {

    private final UserMapper userMapper;

    public Optional<User> getUserById(int id) {
        return userMapper.selectUserById(id);
    }
}
