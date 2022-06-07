package com.one.domain.user.dto;

import com.one.domain.user.code.UserStatus;
import com.one.domain.user.code.UserType;

public record UserSaveRequestDto(String userId, Integer imageFileId, String password, String name, String phoneNumber, UserType userType, UserStatus userStatus) {

    public static UserSaveRequestDto of(HostUserSignUpRequestDto hostUserSignUpRequestDto, int imageFileId) {
        return new UserSaveRequestDto(hostUserSignUpRequestDto.userId(), imageFileId, hostUserSignUpRequestDto.password(), hostUserSignUpRequestDto.name(), hostUserSignUpRequestDto.phoneNumber(), UserType.H, UserStatus.B);
    }

    public static UserSaveRequestDto of(GuestUserSignUpRequestDto guestUserSignUpRequestDto) {
        return new UserSaveRequestDto(guestUserSignUpRequestDto.userId(), null, guestUserSignUpRequestDto.password(), guestUserSignUpRequestDto.name(), guestUserSignUpRequestDto.phoneNumber(), UserType.G, UserStatus.A);
    }
}
