package com.one.domain.sms.application;

public interface SmsAuthenticationService {
    void authenticate(String phoneNumber, String authenticationNumber);

    String sendSms(String phoneNumber);

    void checkAuthenticatedPhoneNumber(String phoneNumber);
}
