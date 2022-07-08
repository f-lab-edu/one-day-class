package com.one.domain.user.domain;

import com.one.domain.user.dto.UserSaveDto;

import java.util.Optional;

public interface UserDao {

    User findById(int id);

    Optional<User> findByUserId(String userId);

    Optional<User> save(UserSaveDto userSaveDto);
}
