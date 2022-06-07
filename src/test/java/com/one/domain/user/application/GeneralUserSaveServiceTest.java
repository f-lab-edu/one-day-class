package com.one.domain.user.application;

import com.one.domain.user.code.UserStatus;
import com.one.domain.user.code.UserType;
import com.one.domain.user.dto.UserSaveRequestDto;
import com.one.domain.user.mapper.UserMapper;
import com.one.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GeneralUserSaveServiceTest {

    @Autowired
    private UserSaveService userSaveService;

    @Autowired
    private UserMapper userMapper;

    private UserSaveRequestDto userSaveRequestDto;

    @BeforeEach
    public void setUp() {
        userSaveRequestDto = new UserSaveRequestDto(null, "test2", 1, "1234", "호스트", "01099999999", UserType.H, UserStatus.B);
    }

    @Test
    @DisplayName("유저 정보 저장에 성공한다.")
    public void test() {
        int id = userSaveService.save(userSaveRequestDto);
        assertThat(userSaveRequestDto.getId()).isEqualTo(id);
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
}