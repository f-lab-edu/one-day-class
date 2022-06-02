package com.one.global.sms.repository;

import com.one.global.sms.model.SmsAuthentication;
import org.springframework.data.repository.CrudRepository;

public interface SmsAuthenticationRepository extends CrudRepository<SmsAuthentication, String> {

}
