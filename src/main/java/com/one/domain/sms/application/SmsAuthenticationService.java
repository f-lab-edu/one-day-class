package com.one.domain.sms.application;

public interface SmsAuthenticationService {
    void authenticate(String phoneNumber, String authenticationNumber);

    String sendSms(String phoneNumber);

    boolean isAuthenticated(String phoneNumber);
}
