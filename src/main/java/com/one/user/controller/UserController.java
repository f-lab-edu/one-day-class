package com.one.user.controller;

import com.one.user.model.User;
import com.one.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController //@ResponseBody(View 없이 객체를 반환하고자 할 때 사용)와 @Controller의 합성
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> optionalUser = userService.getUserById(id);
        return new ResponseEntity<>(optionalUser.orElse(null), HttpStatus.OK);
    }
}
