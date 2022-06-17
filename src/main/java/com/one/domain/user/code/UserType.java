package com.one.domain.user.code;

import lombok.Getter;

@Getter
public enum UserType {
    GUEST(0, "게스트"), HOST(1, "호스트"), ADMIN(2, "관리자");

    private final int value;
    private final String name;

    UserType(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
