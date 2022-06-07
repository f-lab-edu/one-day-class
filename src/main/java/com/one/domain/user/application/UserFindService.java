package com.one.domain.user.application;

import com.one.domain.user.model.User;

import java.util.Optional;

public interface UserFindService {

    User findUserById(int id);

    Optional<User> findUserByUserId(String userId);

}
