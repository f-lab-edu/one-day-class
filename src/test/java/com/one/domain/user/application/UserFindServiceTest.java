package com.one.domain.user.application;

import com.one.domain.user.code.UserStatus;
import com.one.domain.user.code.UserType;
import com.one.domain.user.domain.UserFindService;
import com.one.domain.user.dto.UserSaveRequestDto;
import com.one.domain.user.exception.UserNotFoundException;
import com.one.domain.user.domain.UserMapper;
import com.one.domain.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserFindServiceTest {

    @Autowired
    private UserFindService userFindService;

    @Autowired
    private UserMapper userMapper;

    private int id;
    private String userId;
    private UserSaveRequestDto userSaveRequestDto;

    @BeforeEach
    public void setUp() {
        userMapper.deleteAll();
        userMapper.resetId();
        userSaveRequestDto = new UserSaveRequestDto(null, "testId", 1, "1234", "테스트", "01000000000", UserType.H, UserStatus.B);
        userMapper.saveUser(userSaveRequestDto);
        id = userSaveRequestDto.getId();
        userId = userSaveRequestDto.getUserId();
    }

    @Test
    @DisplayName("유저 식별자로 조회에 성공한다.")
    void test() {
        final User userById = userFindService.findUserById(id);
        assertThat(userById.id()).isEqualTo(id);
        assertThat(userById.userId()).isEqualTo(userId);
        assertThat(userById.imageFileId()).isEqualTo(userSaveRequestDto.getImageFileId());
        assertThat(userById.password()).isEqualTo(userSaveRequestDto.getPassword());
        assertThat(userById.name()).isEqualTo(userSaveRequestDto.getName());
        assertThat(userById.phoneNumber()).isEqualTo(userSaveRequestDto.getPhoneNumber());
        assertThat(userById.userType()).isEqualTo(userSaveRequestDto.getUserType());
        assertThat(userById.userStatus()).isEqualTo(userSaveRequestDto.getUserStatus());
    }

    @Test
    @DisplayName("유저 식별자로 조회된 값이 없을 시 예외가 발생한다.")
    void test2() {
        assertThrows(UserNotFoundException.class, () -> userFindService.findUserById(2));
    }

    @Test
    @DisplayName("유저 아이디로 조회에 성공한다.")
    void test3() {
        final Optional<User> userByUserId = userFindService.findUserByUserId(userId);
        assertThat(userByUserId.get().id()).isEqualTo(id);
        assertThat(userByUserId.get().userId()).isEqualTo(userId);
        assertThat(userByUserId.get().imageFileId()).isEqualTo(userSaveRequestDto.getImageFileId());
        assertThat(userByUserId.get().password()).isEqualTo(userSaveRequestDto.getPassword());
        assertThat(userByUserId.get().name()).isEqualTo(userSaveRequestDto.getName());
        assertThat(userByUserId.get().phoneNumber()).isEqualTo(userSaveRequestDto.getPhoneNumber());
        assertThat(userByUserId.get().userType()).isEqualTo(userSaveRequestDto.getUserType());
        assertThat(userByUserId.get().userStatus()).isEqualTo(userSaveRequestDto.getUserStatus());
    }
}