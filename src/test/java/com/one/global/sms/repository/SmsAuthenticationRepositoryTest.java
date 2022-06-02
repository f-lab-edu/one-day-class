package com.one.global.sms.repository;

import com.one.global.sms.model.SmsAuthentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SmsAuthenticationRepositoryTest {

    @Autowired
    private SmsAuthenticationRepository smsAuthenticationRepository;

    private SmsAuthentication smsAuthentication;

    @BeforeEach
    void setUp() {
        smsAuthenticationRepository.deleteAll();
        smsAuthentication = new SmsAuthentication("01012345678", "1234");
        smsAuthenticationRepository.save(smsAuthentication);
    }

    @Test
    void 인증번호_저장() {
        Optional<SmsAuthentication> byId = smsAuthenticationRepository.findById(smsAuthentication.phoneNumber());
        assertThat(byId.get().phoneNumber()).isEqualTo(smsAuthentication.phoneNumber());
        assertThat(byId.get().authenticationNumber()).isEqualTo(smsAuthentication.authenticationNumber());
    }

    @Test
    void 인증번호_만료() throws InterruptedException {
        Thread.sleep(1000 * 60 * 3); //인증번호 저장 후 3분 뒤
        assertThat(smsAuthenticationRepository.findById(smsAuthentication.phoneNumber())).isEmpty();
    }

    @Test
    void 인증번호_덮어쓰기() {
        SmsAuthentication newSmsAuthentication = new SmsAuthentication("01012345678", "4321");
        smsAuthenticationRepository.save(newSmsAuthentication);
        Optional<SmsAuthentication> byId = smsAuthenticationRepository.findById(this.smsAuthentication.phoneNumber());
        assertThat(byId.get().phoneNumber()).isEqualTo(smsAuthentication.phoneNumber());
        assertThat(byId.get().authenticationNumber()).isEqualTo(newSmsAuthentication.authenticationNumber());
    }
}