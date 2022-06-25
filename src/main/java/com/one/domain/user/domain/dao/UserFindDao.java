package com.one.domain.user.domain.dao;

import com.one.domain.user.domain.User;
import com.one.domain.user.infrastructure.UserMapper;
import com.one.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor // final이나 @NotNull이 붙은 필드의 생성자를 생성해주는 롬복 어노테이션
public class UserFindDao {

    private final UserMapper userMapper;

    public User findUserById(final int id) {
        return userMapper.selectUserById(id).orElseThrow(() -> new UserNotFoundException());
    }

    public Optional<User> findUserByUserId(final String userId) {
        return userMapper.selectUserByUserId(userId);
    }
}
