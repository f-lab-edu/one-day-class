package com.one.domain.sms.application;

import com.one.domain.sms.exception.*;
import com.one.domain.sms.repository.SmsAuthenticationRepository;
import com.one.domain.sms.model.SmsAuthentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeneralSmsAuthenticationService implements SmsAuthenticationService {

    private final SmsAuthenticationRepository smsAuthenticationRepository;
    private final HttpSession httpSession;
    private final Environment environment;

    @Override
    public void authenticate(final String phoneNumber, final String authenticationNumber) {
        final Optional<SmsAuthentication> smsAuthentication = smsAuthenticationRepository.findById(phoneNumber);
        final String storedAuthenticationNumber = smsAuthentication.map(SmsAuthentication::authenticationNumber)
                .orElseThrow(() -> new SmsAuthenticationNotFoundException());
        if (authenticationNumber.equals(storedAuthenticationNumber)) {
            httpSession.setAttribute("authenticatedPhoneNumber", phoneNumber);
        } else {
            throw new AuthenticationNumberMismatchException();
        }
    }

    @Transactional
    @Override
    public String sendSms(final String phoneNumber) {
        //이미 인증된 번호면 예외 처리
        if (phoneNumber.equals(httpSession.getAttribute("authenticatedPhoneNumber"))) {
            throw new AlreadyAuthenticatedPhoneNumberException();
        }
        final String authenticationNumber = generateRandomNumber(4);
        smsAuthenticationRepository.save(new SmsAuthentication(phoneNumber, authenticationNumber));
        send(phoneNumber, authenticationNumber);
        return authenticationNumber;
    }

    private void send(final String phoneNumber, final String authenticationNumber) {
        final Message message = new Message(environment.getProperty("sms.api-key"), environment.getProperty("sms.api-secret"));
        final JSONObject result;
        try {
            if ("local".equals(environment.getProperty("spring.config.activate.on-profile"))) {
                return;
            }
            result = message.send(generateSmsInfo(phoneNumber, generateSmsContent(authenticationNumber)));
            log.debug(result.toString());
        } catch (CoolsmsException e) {
            throw new SmsSendFailedException();
        }
    }

    private String generateSmsContent(final String authenticationNumber) {
        final StringBuilder sb = new StringBuilder();
        return sb.append("인증번호[").append(authenticationNumber).append("]를 입력해주세요.").toString();
    }

    private HashMap<String, String> generateSmsInfo(final String phoneNumber, final String content) {
        final HashMap<String, String> smsInfo = new HashMap<>();
        smsInfo.put("to", phoneNumber);
        smsInfo.put("from", environment.getProperty("sms.from"));
        smsInfo.put("type", "SMS");
        smsInfo.put("text", content);
        log.debug(smsInfo.toString());
        return smsInfo;
    }

    @Override
    public void checkAuthenticatedPhoneNumber(final String phoneNumber) {
        //세션에서 인증된 휴대폰번호 확인 후 다르면 Exception
        if (!phoneNumber.equals(httpSession.getAttribute("authenticatedPhoneNumber"))) {
            throw new NotAuthenticatedPhoneNumberException();
        }
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
