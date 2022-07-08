package com.one.domain.user.domain;


public enum UserType {
    GUEST(0, "게스트"), HOST(1, "호스트"), ADMIN(2, "관리자");

    private final int value;
    private final String name;

    UserType(final int value, final String name) {
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
