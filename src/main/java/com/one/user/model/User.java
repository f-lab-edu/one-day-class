package com.one.user.model;

import java.time.LocalDateTime;

public class User {
    private int id;
    private int imageFileId;
    private String userId;
    private String password;
    private String name;
    private String phoneNumber;
    private String userType;
    private String userStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", imageFileId=" + imageFileId +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userType='" + userType + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}