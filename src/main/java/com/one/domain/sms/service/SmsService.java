package com.one.domain.sms.service;

import com.one.domain.sms.infrastructure.SmsAuthenticationRepository;
import com.one.domain.sms.domain.SmsAuthenticationManager;
import com.one.domain.sms.domain.SmsAuthentication;
import com.one.domain.sms.exception.AlreadyAuthenticatedPhoneNumberException;
import com.one.domain.sms.infrastructure.SmsSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class SmsService {

    private final SmsAuthenticationManager smsAuthenticationManager;
    private final SmsAuthenticationRepository smsAuthenticationRepository;
    private final SmsSender smsSender;
    private final HttpSession httpSession;

    public SmsService(final SmsAuthenticationManager smsAuthenticationManager, final SmsAuthenticationRepository smsAuthenticationRepository, final SmsSender smsSender, final HttpSession httpSession) {
        this.smsAuthenticationManager = smsAuthenticationManager;
        this.smsAuthenticationRepository = smsAuthenticationRepository;
        this.smsSender = smsSender;
        this.httpSession = httpSession;
    }

    public void authenticate(final String phoneNumber, final String authenticationNumber) {
        smsAuthenticationManager.authenticatePhoneNumber(authenticationNumber, phoneNumber);
        httpSession.setAttribute("authenticatedPhoneNumber", phoneNumber);
    }

    @Transactional
    public String sendSms(final String phoneNumber) {
        //이미 인증된 번호면 예외 처리
        if (phoneNumber.equals(httpSession.getAttribute("authenticatedPhoneNumber"))) {
            throw new AlreadyAuthenticatedPhoneNumberException();
        }
        final String authenticationNumber = generateRandomNumber(4);
        smsAuthenticationRepository.save(new SmsAuthentication(phoneNumber, authenticationNumber));
        smsSender.send(phoneNumber, authenticationNumber);
        return authenticationNumber;
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
