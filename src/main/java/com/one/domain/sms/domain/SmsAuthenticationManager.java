package com.one.domain.sms.domain;

import com.one.domain.sms.infrastructure.SmsAuthenticationRepository;
import com.one.domain.sms.exception.AuthenticationNumberMismatchException;
import com.one.domain.sms.exception.NotAuthenticatedPhoneNumberException;
import com.one.domain.sms.exception.SmsAuthenticationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SmsAuthenticationManager {

    private final HttpSession httpSession;
    private final SmsAuthenticationRepository smsAuthenticationRepository;
    private final String AUTHENTICATED_PHONE_NUMBER = "authenticatedPhoneNumber";

    public void authenticatePhoneNumber(String authenticationNumber, String phoneNumber) {
        final String storedAuthenticationNumber = smsAuthenticationRepository.findById(phoneNumber).map(SmsAuthentication::authenticationNumber).orElseThrow(SmsAuthenticationNotFoundException::new);
        Optional.ofNullable(authenticationNumber).filter(a -> !a.equals(storedAuthenticationNumber)).orElseThrow(AuthenticationNumberMismatchException::new);
    }

    public void checkAuthenticatedPhoneNumber(final String phoneNumber) {
        //세션에서 인증된 휴대폰번호 확인 후 다르면 Exception
        Optional.ofNullable(phoneNumber).filter(p -> p.equals(httpSession.getAttribute(AUTHENTICATED_PHONE_NUMBER))).orElseThrow(NotAuthenticatedPhoneNumberException::new);
    }
}
