package com.one.domain.file.dto;

import com.one.global.common.code.ImageFileType;

import java.time.LocalDateTime;

public record ImageFileSaveDto(String path, String name, ImageFileType contentType, LocalDateTime createTime, LocalDateTime updateTime) {
}
