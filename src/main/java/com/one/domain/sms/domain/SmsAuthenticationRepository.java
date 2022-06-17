package com.one.domain.sms.domain;

import org.springframework.data.repository.CrudRepository;

public interface SmsAuthenticationRepository extends CrudRepository<SmsAuthentication, String> {

}
