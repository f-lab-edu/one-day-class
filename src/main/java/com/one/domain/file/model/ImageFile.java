package com.one.domain.file.model;

import com.one.global.common.code.ImageFileType;

import java.time.LocalDateTime;

public record ImageFile(Integer id, String path, String name, ImageFileType contentType, LocalDateTime createTime, LocalDateTime updateTime) {
}
