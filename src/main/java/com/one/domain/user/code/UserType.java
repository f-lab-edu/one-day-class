package com.one.domain.user.code;

public enum UserType {
    G("게스트"), H("호스트"), A("ADMiN")
    ;

    private final String name;

    UserType(String name) {
        this.name = name;
    }
}
