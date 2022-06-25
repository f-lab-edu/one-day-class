package com.one.domain.sms.application;

import com.one.domain.sms.service.SmsService;
import com.one.domain.sms.domain.SmsAuthenticationService;
import com.one.domain.sms.exception.AuthenticationNumberMismatchException;
import com.one.domain.sms.exception.NotAuthenticatedPhoneNumberException;
import com.one.domain.sms.domain.SmsAuthentication;
import com.one.domain.sms.dao.SmsAuthenticationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SmsServiceTest {

    @Autowired
    private SmsService smsService;

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
        smsService.authenticate("01012345678", "1234");
        assertThat(httpSession.getAttribute("authenticatedPhoneNumber")).isEqualTo("01012345678");
    }

    @Test
    @DisplayName("휴대폰번호 인증 실패 시 예외 발생")
    void test2() {
        assertThrows(AuthenticationNumberMismatchException.class, () -> smsService.authenticate("01012345678", "0000"));
    }

    @Test
    @DisplayName("인증 완료 여부 확인(인증 완료 시 정상 리턴)")
    void test3() {
        smsService.authenticate("01012345678", "1234");
        smsAuthenticationService.checkAuthenticatedPhoneNumber("01012345678");
    }

    @Test
    @DisplayName("인증 완료 여부 확인(인증 미완료 시 Exception)")
    void test4() {
        assertThrows(NotAuthenticatedPhoneNumberException.class, () -> smsAuthenticationService.checkAuthenticatedPhoneNumber("01012345678"));
    }
}