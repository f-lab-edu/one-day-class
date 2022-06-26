package com.one.domain.user.service;

import com.one.domain.user.domain.User;
import com.one.domain.user.domain.UserDao;
import com.one.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignInService {

    private final UserDao userDao;

    public User signIn(final String userId, final String password) {
        return userDao.findByUserId(userId).filter(u -> u.password().equals(password)).orElseThrow(() -> new UserNotFoundException());
    }
}
