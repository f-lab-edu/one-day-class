package com.one.domain.sms.infrastructure;

import com.one.domain.sms.domain.SmsAuthentication;
import org.springframework.data.repository.CrudRepository;

public interface SmsAuthenticationRepository extends CrudRepository<SmsAuthentication, String> {

}
