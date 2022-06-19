package com.one.domain.sms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * jdk16부터 정식 릴리즈된 record는 '얕은 불변성'만 보장합니다.
 * final 필드로 선언했더라도 변수 자체가 (List, Date 등의) 참조 변수이면 이들의 value가 변하지 않는것까지 보장하지는 않습니다.
 */
public record AuthenticateRequestDto(@NotBlank(message = "공백은 입력할 수 업습니다.")
                                     @Pattern(regexp = "\\d{11}", message = "휴대폰번호는 '-' 없이 11자리 숫자로 입력해주세요.")
                                     String phoneNumber,

                                     @NotBlank(message = "공백은 입력할 수 없습니다.")
                                     @Pattern(regexp = "\\d{4}", message = "인증번호는 4자리 숫자로 입력해주세요.")
                                     String authenticationNumber)
        /**
         * Compact Constructor는 실제 필드가 초기화되기 전에 호출됩니다.
         * Compact Constructor는 Canonical Constructor에 주입되는 코드입니다.
         */
{}
