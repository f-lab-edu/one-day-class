package com.one.domain.user.service;

import com.one.domain.user.code.UserStatus;
import com.one.domain.user.code.UserType;
import com.one.domain.user.domain.dao.UserSaveDao;
import com.one.domain.user.dto.UserSaveRequestDto;
import com.one.domain.user.infrastructure.UserMapper;
import com.one.domain.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserSaveDaoTest {

    @Autowired
    private UserSaveDao userSaveDao;

    @Autowired
    private UserMapper userMapper;

    private UserSaveRequestDto userSaveRequestDto;

    @BeforeEach
    public void setUp() {
        userSaveRequestDto = new UserSaveRequestDto(null, "test2", 1, "1234", "호스트", "01099999999", UserType.HOST.getValue(), UserStatus.SIGN_UP_PROCEEDING.getValue());
    }

    @Test
    @DisplayName("유저 정보 저장에 성공한다.")
    public void test() {
        final int id = userSaveDao.save(userSaveRequestDto);
        assertThat(userSaveRequestDto.getId()).isEqualTo(id);
        final Optional<User> user = userMapper.selectUserById(id);
        assertThat(user.get().id()).isEqualTo(id);
        assertThat(user.get().userId()).isEqualTo(userSaveRequestDto.getUserId());
        assertThat(user.get().imageFileId()).isEqualTo(userSaveRequestDto.getImageFileId());
        assertThat(user.get().password()).isEqualTo(userSaveRequestDto.getPassword());
        assertThat(user.get().name()).isEqualTo(userSaveRequestDto.getName());
        assertThat(user.get().phoneNumber()).isEqualTo(userSaveRequestDto.getPhoneNumber());
        assertThat(user.get().userType()).isEqualTo(userSaveRequestDto.getUserType());
        assertThat(user.get().userStatus()).isEqualTo(userSaveRequestDto.getUserStatus());
    }
}