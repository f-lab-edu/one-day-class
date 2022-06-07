package com.one.global.common.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
    //SUCCESS
    S001(HttpStatus.CREATED, "인증번호 전송을 완료했습니다."),
    S002(HttpStatus.OK, "휴대폰번호가 인증되었습니다."),
    S003(HttpStatus.CREATED, "파일이 정상적으로 업로드되었습니다."),
    S004(HttpStatus.OK, "파일이 정상적으로 다운로드되었습니다."),
    S005(HttpStatus.CREATED, "게스트 회원가입이 완료되었습니다."),
    S006(HttpStatus.ACCEPTED, "호스트 회원가입 신청이 완료되었습니다"),

    //EXCEPTION
    E001(HttpStatus.BAD_REQUEST, "유효값 검증에 실패했습니다."),
    E002(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."),
    E003(HttpStatus.CONFLICT, "이미 인증 완료된 휴대폰번호입니다."),
    E004(HttpStatus.NOT_FOUND, "인증번호가 일치하지 않습니다."),
    E005(HttpStatus.NOT_FOUND, "인증번호 발송 내역이 존재하지 않습니다."),
    E006(HttpStatus.CONFLICT, "인증번호 발송에 실패했습니다."),
    E007(HttpStatus.CONFLICT, "파일 업로드에 실패했습니다."),
    E008(HttpStatus.CONFLICT, "파일 다운로드에 실패했습니다."),
    E009(HttpStatus.FORBIDDEN, "휴대폰번호 인증이 필요합니다."), //403 FORBIDDEN: 클라이언트가 콘텐츠에 대한 접근 권한이 없을 때 발생한다. (401 UNAUTHORIZED와 다름)
    E010(HttpStatus.CONFLICT, "중복된 아이디입니다."),
    E011(HttpStatus.BAD_REQUEST, "패스워드와 검증 패스워드가 일치하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    ResponseCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
