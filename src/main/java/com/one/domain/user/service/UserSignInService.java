package com.one.domain.user.service;

import com.one.domain.user.domain.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class UserSignInService {

    private final UserDao userDao;
    private final HttpSession httpSession;

    public void signIn(final String userId, final String password) {
        if (userDao.findByUserId(userId).get().password().equals(password)) {
            httpSession.setAttribute("userId", userId);
        }
    }
}
