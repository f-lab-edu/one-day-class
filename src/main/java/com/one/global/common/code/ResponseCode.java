package com.one.global.common.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
    //SUCCESS
    S001(HttpStatus.CREATED, "인증번호 전송을 완료했습니다."),
    S002(HttpStatus.OK, "휴대폰번호가 인증되었습니다."),

    //EXCEPTION
    E001(HttpStatus.BAD_REQUEST, "유효값 검증에 실패했습니다."),
    E002(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."),
    E003(HttpStatus.CONFLICT, "이미 인증 완료된 휴대폰번호입니다."),
    E004(HttpStatus.NOT_FOUND, "인증번호가 일치하지 않습니다."),
    E005(HttpStatus.NOT_FOUND, "인증번호 발송 내역이 존재하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    ResponseCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
