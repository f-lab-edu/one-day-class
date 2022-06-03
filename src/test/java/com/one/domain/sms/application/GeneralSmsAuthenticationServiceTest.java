package com.one.domain.sms.application;

import com.one.domain.sms.application.SmsAuthenticationService;
import com.one.domain.sms.exception.AuthenticationNumberMismatchException;
import com.one.domain.sms.model.SmsAuthentication;
import com.one.domain.sms.repository.SmsAuthenticationRepository;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GeneralSmsAuthenticationServiceTest {

    @Autowired
    private SmsAuthenticationService smsAuthenticationService;

    @Autowired
    private SmsAuthenticationRepository smsAuthenticationRepository;

    @Autowired
    private HttpSession httpSession;

    @BeforeEach
    void setUp() {
        smsAuthenticationRepository.save(new SmsAuthentication("01012345678", "1234"));
    }

    @Test
    @DisplayName("휴대폰번호 인증 성공 시 세션 저장")
    void test() {
        smsAuthenticationService.authenticate("01012345678", "1234");
        assertThat(httpSession.getAttribute("authenticatedPhoneNumber")).isEqualTo("01012345678");
    }

    @Test
    @DisplayName("휴대폰번호 인증 실패 시 예외 발생")
    void test2() {
        assertThrows(AuthenticationNumberMismatchException.class, () -> smsAuthenticationService.authenticate("01012345678", "0000"));
    }

    @Test
    @DisplayName("인증 완료 여부 확인(인증 완료 시 true)")
    void test3() {
        smsAuthenticationService.authenticate("01012345678", "1234");
        assertThat(smsAuthenticationService.isAuthenticated("01012345678")).isTrue();
    }

    @Test
    @DisplayName("인증 완료 여부 확인(인증 미완료 시 false)")
    void test4() {
        assertThat(smsAuthenticationService.isAuthenticated("01012345678")).isFalse();
    }
}