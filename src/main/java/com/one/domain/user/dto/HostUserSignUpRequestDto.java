package com.one.domain.user.dto;

import org.springframework.web.multipart.MultipartFile;

public record HostUserSignUpRequestDto(String userId, String password, String password2, String name, String phoneNumber, int bigCategoryId, MultipartFile multipartFile) {

}
