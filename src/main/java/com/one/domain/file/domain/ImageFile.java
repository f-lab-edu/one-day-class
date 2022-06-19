package com.one.domain.file.domain;

import java.time.LocalDateTime;

public record ImageFile(Integer id, String path, String name, int contentType, LocalDateTime createTime, LocalDateTime updateTime) {
}
