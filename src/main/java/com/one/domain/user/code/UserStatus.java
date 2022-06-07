package com.one.domain.user.code;

public enum UserStatus {
    A("가입 완료"), B("가입 심사 중"), C("가입 탈퇴 중"),
    ;
    private final String name;

    UserStatus(String name) {
        this.name = name;
    }
}
