package com.one.domain.sms.repository;

import com.one.domain.sms.model.SmsAuthentication;
import org.springframework.data.repository.CrudRepository;

public interface SmsAuthenticationRepository extends CrudRepository<SmsAuthentication, String> {

}
