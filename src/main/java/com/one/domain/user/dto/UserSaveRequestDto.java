package com.one.domain.user.dto;

import com.one.domain.user.code.UserStatus;
import com.one.domain.user.code.UserType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class UserSaveRequestDto {
    private final Integer id;
    private final String userId;
    private final Integer imageFileId;
    private final String password;
    private final String name;
    private final String phoneNumber;
    private final UserType userType;
    private final UserStatus userStatus;

    public static UserSaveRequestDto of(HostUserSignUpRequestDto hostUserSignUpRequestDto, int imageFileId) {
        return new UserSaveRequestDto(null, hostUserSignUpRequestDto.userId(), imageFileId, hostUserSignUpRequestDto.password(), hostUserSignUpRequestDto.name(), hostUserSignUpRequestDto.phoneNumber(), UserType.H, UserStatus.B);
    }

    public static UserSaveRequestDto of(GuestUserSignUpRequestDto guestUserSignUpRequestDto) {
        return new UserSaveRequestDto(null, guestUserSignUpRequestDto.userId(), null, guestUserSignUpRequestDto.password(), guestUserSignUpRequestDto.name(), guestUserSignUpRequestDto.phoneNumber(), UserType.G, UserStatus.A);
    }

}
