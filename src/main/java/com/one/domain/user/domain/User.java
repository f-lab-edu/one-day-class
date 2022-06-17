package com.one.domain.user.domain;

import com.one.domain.user.code.UserStatus;
import com.one.domain.user.code.UserType;

import java.time.LocalDateTime;

public record User(Integer id, Integer imageFileId, String userId, String password, String name, String phoneNumber,
                   UserType userType, UserStatus userStatus, LocalDateTime createTime, LocalDateTime updateTime) {
}