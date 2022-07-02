package com.one.domain.user.service;

import com.one.domain.user.domain.User;
import com.one.domain.user.domain.UserDao;
import com.one.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignInService {

    private final UserDao userDao;

    @Transactional(readOnly = true)
    public User signIn(final String userId, final String password) {
        return userDao.findByUserId(userId).filter(u -> u.password().equals(password)).orElseThrow(() -> new UserNotFoundException());
    }

    @Transactional(readOnly = true)
    public User findUserById(final int id) {
        return userDao.findById(id);
    }
}
