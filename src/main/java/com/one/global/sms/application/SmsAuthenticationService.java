package com.one.global.sms.application;

public interface SmsAuthenticationService {
    void authenticate(String phoneNumber, String authenticationNumber);

    void sendSms(String phoneNumber);

    boolean isAuthenticated(String phoneNumber);
}
