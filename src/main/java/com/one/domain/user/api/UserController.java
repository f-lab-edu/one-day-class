package com.one.domain.user.api;

import com.one.domain.file.application.FileManagementService;
import com.one.domain.sms.application.SmsAuthenticationService;
import com.one.domain.user.application.UserSignUpService;
import com.one.domain.user.dto.GuestUserSignUpRequestDto;
import com.one.domain.user.dto.HostUserSignUpRequestDto;
import com.one.domain.user.model.User;
import com.one.domain.user.application.UserFindService;
import com.one.global.common.code.ResponseCode;
import com.one.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController //@ResponseBody(View 없이 객체를 반환하고자 할 때 사용)와 @Controller의 합성
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserSignUpService userSignUpService;
    private final UserFindService userFindService;
    private final SmsAuthenticationService smsAuthenticationService;
    private final FileManagementService fileManagementService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable final int id) {
        final User user = userFindService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/signup/guest")
    public ResponseEntity<CommonResponse> signUpGuest(@ModelAttribute final GuestUserSignUpRequestDto guestUserSignUpRequestDto) {
        userSignUpService.signUp(guestUserSignUpRequestDto);
        return CommonResponse.of(ResponseCode.S005);
    }

    @PostMapping("/signup/host")
    public ResponseEntity<CommonResponse> signUpHost(@ModelAttribute final HostUserSignUpRequestDto hostUserSignUpRequestDto) throws IOException {
        userSignUpService.signUp(hostUserSignUpRequestDto);
        return CommonResponse.of(ResponseCode.S006);
    }
}
