package com.one.domain.user.domain;

import java.time.LocalDateTime;

public record User(Integer id, Integer imageFileId, String userId, String password, String name, String phoneNumber,
                   int userType, int userStatus, LocalDateTime createTime, LocalDateTime updateTime) {
}