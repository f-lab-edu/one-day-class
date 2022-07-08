package com.one.domain.user.dto;

import com.one.domain.user.domain.UserStatus;
import com.one.domain.user.domain.UserType;

public record UserSaveDto(Integer imageFileId, String userId, String password, String name, String phoneNumber,
                          int userType, int userStatus) {

    public static UserSaveDto of(Integer imageFileId, HostUserSignUpDto hostUserSignUpDto) {
        return new UserSaveDto(imageFileId, hostUserSignUpDto.userId(), hostUserSignUpDto.password(), hostUserSignUpDto.name(), hostUserSignUpDto.phoneNumber(), UserType.HOST.getValue(), UserStatus.RESIGN_PROCEEDING.getValue());
    }

    public static UserSaveDto from(GuestUserSignUpDto guestUserSignUpDto) {
        return new UserSaveDto(null, guestUserSignUpDto.userId(), guestUserSignUpDto.password(), guestUserSignUpDto.name(), guestUserSignUpDto.phoneNumber(), UserType.GUEST.getValue(), UserStatus.SIGN_UP_SUCCESS.getValue());
    }
}