package com.one.domain.user.domain;

import com.one.domain.user.code.UserStatus;
import com.one.domain.user.code.UserType;
import com.one.domain.user.dto.UserSaveRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private UserSaveRequestDto userSaveRequestDto;

    @BeforeEach
    public void setUp() {
        userSaveRequestDto = new UserSaveRequestDto(null, "testId", 1, "1234", "테스트", "01000000000", UserType.HOST.getValue(), UserStatus.SIGN_UP_PROCEEDING.getValue());
        userMapper.saveUser(userSaveRequestDto);
    }

    @Test
    @DisplayName("유저 식별자로 조회에 성공한다.")
    public void test2() {
        int id = userSaveRequestDto.getId();
        Optional<User> user = userMapper.selectUserById(id);
        assertThat(user.get().id()).isEqualTo(id);
        assertThat(user.get().userId()).isEqualTo(userSaveRequestDto.getUserId());
        assertThat(user.get().imageFileId()).isEqualTo(userSaveRequestDto.getImageFileId());
        assertThat(user.get().password()).isEqualTo(userSaveRequestDto.getPassword());
        assertThat(user.get().name()).isEqualTo(userSaveRequestDto.getName());
        assertThat(user.get().phoneNumber()).isEqualTo(userSaveRequestDto.getPhoneNumber());
        assertThat(user.get().userType()).isEqualTo(userSaveRequestDto.getUserType());
        assertThat(user.get().userStatus()).isEqualTo(userSaveRequestDto.getUserStatus());
    }

    @Test
    @DisplayName("유저 아이디로 조회에 성공한다.")
    public void test3() {
        String userId = userSaveRequestDto.getUserId();
        Optional<User> user = userMapper.selectUserByUserId(userId);
        assertThat(user.get().id()).isEqualTo(userSaveRequestDto.getId());
        assertThat(user.get().userId()).isEqualTo(userId);
        assertThat(user.get().imageFileId()).isEqualTo(userSaveRequestDto.getImageFileId());
        assertThat(user.get().password()).isEqualTo(userSaveRequestDto.getPassword());
        assertThat(user.get().name()).isEqualTo(userSaveRequestDto.getName());
        assertThat(user.get().phoneNumber()).isEqualTo(userSaveRequestDto.getPhoneNumber());
        assertThat(user.get().userType()).isEqualTo(userSaveRequestDto.getUserType());
        assertThat(user.get().userStatus()).isEqualTo(userSaveRequestDto.getUserStatus());
    }
}