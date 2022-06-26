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

    public void authenticatePhoneNumber(String authenticationNumber, String phoneNumber) {
        final Optional<SmsAuthentication> smsAuthentication = smsAuthenticationRepository.findById(phoneNumber);
        final String storedAuthenticationNumber = smsAuthentication.map(SmsAuthentication::authenticationNumber)
                .orElseThrow(() -> new SmsAuthenticationNotFoundException());
        if (!authenticationNumber.equals(storedAuthenticationNumber)) {
            throw new AuthenticationNumberMismatchException();
        }
    }

    public void checkAuthenticatedPhoneNumber(final String phoneNumber) {
        //세션에서 인증된 휴대폰번호 확인 후 다르면 Exception
        if (!phoneNumber.equals(httpSession.getAttribute("authenticatedPhoneNumber"))) {
            throw new NotAuthenticatedPhoneNumberException();
        }
    }
}
