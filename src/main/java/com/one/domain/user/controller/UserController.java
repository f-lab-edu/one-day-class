package com.one.domain.user.controller;

import com.one.domain.user.dto.SignInDto;
import com.one.domain.user.service.UserSignInService;
import com.one.domain.user.service.UserSignUpService;
import com.one.domain.user.dto.GuestUserSignUpDto;
import com.one.domain.user.dto.HostUserSignUpDto;
import com.one.global.common.ResponseCode;
import com.one.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController //@ResponseBody(View 없이 객체를 반환하고자 할 때 사용)와 @Controller의 합성
@RequiredArgsConstructor
public class UserController {

    private final UserSignUpService userSignUpService;
    private final UserSignInService userSignInService;

    @PostMapping("/signup/guest")
    public ResponseEntity<CommonResponse> signUpGuest(@ModelAttribute final GuestUserSignUpDto guestUserSignUpDto) {
        userSignUpService.checkDuplicateUserId(guestUserSignUpDto.userId());
        userSignUpService.checkPassword(guestUserSignUpDto.password(), guestUserSignUpDto.password2());
        userSignUpService.signUp(guestUserSignUpDto);
        return CommonResponse.of(ResponseCode.S005);
    }

    @PostMapping("/signup/host")
    public ResponseEntity<CommonResponse> signUpHost(@ModelAttribute final HostUserSignUpDto hostUserSignUpDto) {
        userSignUpService.checkDuplicateUserId(hostUserSignUpDto.userId());
        userSignUpService.checkPassword(hostUserSignUpDto.password(), hostUserSignUpDto.password2());
        userSignUpService.signUp(hostUserSignUpDto);
        return CommonResponse.of(ResponseCode.S006);
    }

    @PostMapping("signin")
    public ResponseEntity<CommonResponse> signIn(@RequestBody @Valid final SignInDto signInDto) {
        userSignInService.signIn(signInDto.userId(), signInDto.password());
        return null;
    }
}