package com.one.domain.user.domain;

public enum UserStatus {
    SIGN_UP_SUCCESS(0,"가입 완료"), SIGN_UP_PROCEEDING(1, "가입 심사 중"), RESIGN_PROCEEDING(2, "가입 탈퇴 중"),
    ;

    private final int value;
    private final String name;

    UserStatus(final int value, final String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
}
