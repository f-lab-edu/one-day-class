package com.one.user.controller;

import com.one.user.model.User;
import com.one.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return user.toString();
    }
}
