package com.one.domain.sms.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "sms", timeToLive = 60 * 3)
public record SmsAuthentication(@Id String phoneNumber, String authenticationNumber) {

}
