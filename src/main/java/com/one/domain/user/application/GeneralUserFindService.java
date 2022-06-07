package com.one.domain.user.application;

import com.one.domain.user.exception.UserNotFoundException;
import com.one.domain.user.mapper.UserMapper;
import com.one.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor // final이나 @NotNull이 붙은 필드의 생성자를 생성해주는 롬복 어노테이션
public class GeneralUserFindService implements UserFindService {

    private final UserMapper userMapper;

    @Override
    public User findUserById(final int id) {
        return userMapper.selectUserById(id).orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public Optional<User> findUserByUserId(final String userId) {
        return userMapper.selectUserByUserId(userId);
    }
}
