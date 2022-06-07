package com.one.domain.user.dto;

public record GuestUserSignUpRequestDto(String userId, String password, String password2, String name, String phoneNumber) {

}
