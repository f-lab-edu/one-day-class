package com.one.domain.user.controller;

import com.one.domain.user.domain.User;
import com.one.domain.user.dto.SignInDto;
import com.one.domain.user.service.UserSignInService;
import com.one.domain.user.service.UserSignUpService;
import com.one.domain.user.dto.GuestUserSignUpDto;
import com.one.domain.user.dto.HostUserSignUpDto;
import com.one.global.common.ResponseCode;
import com.one.global.common.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
//@RestController: @ResponseBody(View 없이 객체를 반환하고자 할 때 사용)와 @Controller의 합성
public class UserController {

    private final UserSignUpService userSignUpService;
    private final UserSignInService userSignInService;

    public UserController(UserSignUpService userSignUpService, UserSignInService userSignInService) {
        this.userSignUpService = userSignUpService;
        this.userSignInService = userSignInService;
    }

    private final String USER_INFO = "USER_INFO";

    @ResponseBody
    @PostMapping("/signup/guest")
    public ResponseEntity<CommonResponse> signUpGuest(@ModelAttribute final GuestUserSignUpDto guestUserSignUpDto) {
        userSignUpService.checkDuplicateUserId(guestUserSignUpDto.userId());
        userSignUpService.checkPassword(guestUserSignUpDto.password(), guestUserSignUpDto.password2());
        userSignUpService.signUp(guestUserSignUpDto);
        return CommonResponse.of(ResponseCode.S005);
    }

    @ResponseBody
    @PostMapping("/signup/host")
    public ResponseEntity<CommonResponse> signUpHost(@ModelAttribute final HostUserSignUpDto hostUserSignUpDto) {
        userSignUpService.checkDuplicateUserId(hostUserSignUpDto.userId());
        userSignUpService.checkPassword(hostUserSignUpDto.password(), hostUserSignUpDto.password2());
        userSignUpService.signUp(hostUserSignUpDto);
        return CommonResponse.of(ResponseCode.S006);
    }

    @GetMapping("/signin")
    public String signInForm(@ModelAttribute final SignInDto signInDto) {
        return "signInForm";
    }

    @PostMapping("/signin")
    public String signIn(@ModelAttribute final SignInDto signInDto, final HttpServletRequest request) {
        final User user = userSignInService.signIn(signInDto.userId(), signInDto.password());
        request.getSession().setAttribute(USER_INFO, user);
        return "redirect:/users/" + user.id();
    }

    @GetMapping("/{id}")
    public String userInfo(@PathVariable int id, final Model model) {
        final User user = userSignInService.findUserById(id);
        model.addAttribute(user);
        return "userInfo";
    }

    @PostMapping("/signout")
    public String signOut(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(USER_INFO);
            session.invalidate();
        }
        return "redirect:/users/signin";
    }
}
