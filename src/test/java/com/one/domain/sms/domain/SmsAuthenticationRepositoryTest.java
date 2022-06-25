package com.one.domain.sms.domain;

import com.one.domain.sms.dao.SmsAuthenticationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("인증번호 생성 후 저장")
    void test() {
        Optional<SmsAuthentication> byId = smsAuthenticationRepository.findById(smsAuthentication.phoneNumber());
        assertThat(byId.get().phoneNumber()).isEqualTo(smsAuthentication.phoneNumber());
        assertThat(byId.get().authenticationNumber()).isEqualTo(smsAuthentication.authenticationNumber());
    }

    @Test
    @DisplayName("인증번호 생성 후 3분 뒤 만료")
    void test2() throws InterruptedException {
        Thread.sleep(1000 * 60 * 3); //인증번호 저장 후 3분 뒤
        assertThat(smsAuthenticationRepository.findById(smsAuthentication.phoneNumber())).isEmpty();
    }

    @Test
    @DisplayName("같은 휴대폰번호로 인증번호 생성 시 덮어쓰기")
    void test3() {
        final SmsAuthentication newSmsAuthentication = new SmsAuthentication("01012345678", "4321");
        smsAuthenticationRepository.save(newSmsAuthentication);
        final Optional<SmsAuthentication> byId = smsAuthenticationRepository.findById(this.smsAuthentication.phoneNumber());
        assertThat(byId.get().phoneNumber()).isEqualTo(smsAuthentication.phoneNumber());
        assertThat(byId.get().authenticationNumber()).isEqualTo(newSmsAuthentication.authenticationNumber());
    }
}