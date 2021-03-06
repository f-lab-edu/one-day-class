package com.one.domain.sms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record SendSmsRequestDto(@NotBlank(message = "공백은 입력할 수 없습니다.")
                                @Pattern(regexp = "\\d{11}", message = "휴대폰번호는 '-' 없이 11자리 숫자로 입력해주세요.")
                                String phoneNumber)
{}
