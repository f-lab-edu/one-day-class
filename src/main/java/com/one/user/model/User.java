package com.one.user.model;

import java.time.LocalDateTime;

public record User(Integer id, Integer imageFileId, String userId, String password, String name, String phoneNumber,
                   String userType, String userStatus, LocalDateTime createTime, LocalDateTime updateTime) {
}