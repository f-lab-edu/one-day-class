package com.one.domain.user.infrastructure;

import com.one.domain.user.domain.User;
import com.one.domain.user.dto.UserSaveDto;
import com.one.domain.user.domain.UserDao;
import com.one.domain.user.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final UserMapper userMapper;

    public UserDaoImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findById(final int id) {
        return userMapper.findById(id).orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public Optional<User> findByUserId(final String userId) {
        return userMapper.findByUserId(userId);
    }

    @Override
    public Optional<User> save(final UserSaveDto userSaveDto) {
        userMapper.save(userSaveDto);
        return userMapper.findByUserId(userSaveDto.userId());
    }
}
