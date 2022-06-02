package com.one.global.sms.application;

import com.one.global.sms.exception.AlreadyAuthenticatedPhoneNumberException;
import com.one.global.sms.exception.AuthenticationNumberMismatchException;
import com.one.global.sms.exception.SmsAuthenticationNotFoundException;
import com.one.global.sms.model.SmsAuthentication;
import com.one.global.sms.repository.SmsAuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GeneralSmsAuthenticationService implements SmsAuthenticationService {

    private final SmsAuthenticationRepository smsAuthenticationRepository;
    private final HttpSession httpSession;

    @Override
    public void authenticate(final String phoneNumber, final String authenticationNumber) {
        final Optional<SmsAuthentication> smsAuthentication = smsAuthenticationRepository.findById(phoneNumber);
        final String storedAuthenticationNumber = smsAuthentication.map(SmsAuthentication::authenticationNumber).orElseThrow(() -> new SmsAuthenticationNotFoundException());
        if (authenticationNumber.equals(storedAuthenticationNumber)) {
            httpSession.setAttribute("authenticatedPhoneNumber", phoneNumber);
        } else {
            throw new AuthenticationNumberMismatchException();
        }
    }

    @Transactional
    @Override
    public void sendSms(final String phoneNumber) {
        //이미 인증된 번호면 예외 처리
        if (phoneNumber.equals(httpSession.getAttribute("authenticatedPhoneNumber"))) {
            throw new AlreadyAuthenticatedPhoneNumberException();
        }
        final String authenticationNumber = generateRandomNumber(4);
        smsAuthenticationRepository.save(new SmsAuthentication(phoneNumber, authenticationNumber));
        final StringBuilder sb = new StringBuilder();
        final String smsMessage = sb.append("인증번호[").append(authenticationNumber).append("]를 입력해주세요.").toString();
        //TODO SMS 전송 로직 작성
    }

    @Override
    public boolean isAuthenticated(final String phoneNumber) {
        //세션에서 인증된 휴대폰번호 확인 후 같으면 true, 다르면 false
        if (phoneNumber.equals(httpSession.getAttribute("authenticatedPhoneNumber"))) {
            return true;
        }
        return false;
    }

    private String generateRandomNumber(final int digit) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
