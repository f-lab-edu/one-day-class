package com.one.domain.user.application;

import com.one.domain.user.exception.UserNotFoundException;
import com.one.domain.user.mapper.UserMapper;
import com.one.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final이나 @NotNull이 붙은 필드의 생성자를 생성해주는 롬복 어노테이션
public class UserService {

    private final UserMapper userMapper;

    public User getUserById(final int id) {
        return userMapper.selectUserById(id).orElseThrow(() -> new UserNotFoundException());
    }
}
