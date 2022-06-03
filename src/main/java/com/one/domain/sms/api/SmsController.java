package com.one.domain.sms.api;

import com.one.domain.sms.application.SmsAuthenticationService;
import com.one.domain.sms.dto.AuthenticateRequestDto;
import com.one.global.common.response.CommonResponse;
import com.one.domain.sms.dto.SendSmsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.one.global.common.code.ResponseCode.*;

@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsAuthenticationService smsAuthenticationService;

    @PostMapping("/send")
    public ResponseEntity<CommonResponse> sendSms(@RequestBody @Valid final SendSmsRequestDto sendSmsRequestDto) {
        final Map<String, Object> data = new HashMap<>();
        data.put("authenticationNumber", smsAuthenticationService.sendSms(sendSmsRequestDto.phoneNumber()));
        return CommonResponse.of(S001, data);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<CommonResponse> authenticate(@RequestBody @Valid final AuthenticateRequestDto authenticateRequestDto) {
        smsAuthenticationService.authenticate(authenticateRequestDto.phoneNumber(), authenticateRequestDto.authenticationNumber());
        return CommonResponse.of(S002);
    }
}
