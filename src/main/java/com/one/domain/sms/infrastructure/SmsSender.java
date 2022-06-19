package com.one.domain.sms.infrastructure;

import com.one.domain.sms.exception.SmsSendFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsSender {

    private final Environment environment;

    public void send(final String phoneNumber, final String authenticationNumber) {
        final Message message = new Message(environment.getProperty("sms.api-key"), environment.getProperty("sms.api-secret"));
        try {
            if ("local".equals(environment.getProperty("spring.config.activate.on-profile"))) {
                return;
            }
            message.send(generateSmsInfo(phoneNumber, generateSmsContent(authenticationNumber)));
        } catch (CoolsmsException e) {
            throw new SmsSendFailedException();
        }
    }

    public String generateSmsContent(final String authenticationNumber) {
        final StringBuilder sb = new StringBuilder();
        return sb.append("인증번호[").append(authenticationNumber).append("]를 입력해주세요.").toString();
    }

    public HashMap<String, String> generateSmsInfo(final String phoneNumber, final String content) {
        final HashMap<String, String> smsInfo = new HashMap<>();
        smsInfo.put("to", phoneNumber);
        smsInfo.put("from", environment.getProperty("sms.from"));
        smsInfo.put("type", "SMS");
        smsInfo.put("text", content);
        return smsInfo;
    }


}
