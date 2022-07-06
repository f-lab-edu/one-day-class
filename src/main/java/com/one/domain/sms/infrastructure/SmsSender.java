package com.one.domain.sms.infrastructure;

import com.one.domain.sms.exception.SmsSendFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsSender {

    @Value("${sms.api-key}")
    private final String apiKey;

    @Value("${sms.api-secret}")
    private final String apiSecret;

    @Value("${spring.config.activate.on-profile}")
    private final String env;

    @Value("${sms.from}")
    private final String fromNumber;

    private final String TYPE = "SMS";

    public void send(final String phoneNumber, final String authenticationNumber) {
        final Message message = new Message(this.apiKey, this.apiSecret);
        try {
            if ("local".equals(env)) {
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
        smsInfo.put("from", this.fromNumber);
        smsInfo.put("type", this.TYPE);
        smsInfo.put("text", content);
        return smsInfo;
    }


}
