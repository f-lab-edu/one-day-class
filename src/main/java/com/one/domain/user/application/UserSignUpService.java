package com.one.domain.user.application;

import com.one.domain.user.dto.GuestUserSignUpRequestDto;
import com.one.domain.user.dto.HostUserSignUpRequestDto;

import java.io.IOException;

public interface UserSignUpService {

    void signUp(GuestUserSignUpRequestDto guestUserSignUpRequestDto);

    void signUp(HostUserSignUpRequestDto hostUserSignUpRequestDto) throws IOException;
}
