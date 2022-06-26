package com.one.domain.user;

import com.one.domain.user.domain.*;
import com.one.domain.user.dto.UserSaveDto;
import com.one.domain.user.exception.UserNotFoundException;
import com.one.domain.user.infrastructure.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@Sql("classpath:table-init.sql")
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private UserSaveDto userSaveDto;

    @BeforeEach
    public void setUp() {
        userSaveDto = new UserSaveDto(1, "test", "0000", "테스트", "01012345678", UserType.HOST.getValue(), UserStatus.SIGN_UP_PROCEEDING.getValue());
        userMapper.save(userSaveDto);
    }

    @Test
    @DisplayName("유저 식별자로 조회에 성공한다.")
    void test() {
        final Optional<User> user = userMapper.findById(1);
        check(user.get());
    }

    @Test
    @DisplayName("유저 식별자로 조회된 값이 없을 시 예외가 발생한다.")
    void test2() {
        assertThrows(UserNotFoundException.class, () -> userMapper.findById(2));
    }

    @Test
    @DisplayName("유저 아이디로 조회에 성공한다.")
    void test3() {
        final Optional<User> user = userMapper.findByUserId(userSaveDto.userId());
        check(user.get());
    }

    @Test
    @DisplayName("유저 정보 저장에 성공한다.")
    public void test4() {
        userMapper.save(userSaveDto);
        final Optional<User> user = userMapper.findByUserId(userSaveDto.userId());
        check(user.get());
    }

    private void check(User user) {
        assertThat(user.id()).isEqualTo(1);
        assertThat(user.userId()).isEqualTo(userSaveDto.userId());
        assertThat(user.imageFileId()).isEqualTo(userSaveDto.imageFileId());
        assertThat(user.password()).isEqualTo(userSaveDto.password());
        assertThat(user.name()).isEqualTo(userSaveDto.name());
        assertThat(user.phoneNumber()).isEqualTo(userSaveDto.phoneNumber());
        assertThat(user.userType()).isEqualTo(userSaveDto.userType());
        assertThat(user.userStatus()).isEqualTo(userSaveDto.userStatus());
    }
}