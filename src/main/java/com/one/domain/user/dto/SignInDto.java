package com.one.domain.user.dto;

import javax.validation.constraints.NotBlank;

public record SignInDto(@NotBlank String userId,
                        @NotBlank String password) {
}
