package com.one.domain.file.code;

public enum ImageFileType {
    A("심사", "user"),
    B("후기", "review"),
    C("카테고리", "category"),
    D("클래스", "class")
    ;

    private final String detail;
    private final String path;

    ImageFileType(String detail, String path) {
        this.detail = detail;
        this.path = path;
    }
}
