package com.one.domain.user.application;

import com.one.domain.category.domain.CategoryMapper;
import com.one.domain.file.domain.ImageFileMapper;
import com.one.domain.file.domain.ImageFile;
import com.one.domain.sms.exception.NotAuthenticatedPhoneNumberException;
import com.one.domain.user.code.UserStatus;
import com.one.domain.user.code.UserType;
import com.one.domain.user.dto.GuestUserSignUpRequestDto;
import com.one.domain.user.dto.HostUserSignUpRequestDto;
import com.one.domain.user.dto.UserSaveRequestDto;
import com.one.domain.user.exception.DuplicateUserIdException;
import com.one.domain.user.exception.PasswordMismatchException;
import com.one.domain.user.domain.UserMapper;
import com.one.domain.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserSignUpServiceTest {

    @Autowired
    private UserSignUpService userSignUpService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ImageFileMapper imageFileMapper;

    @Autowired
    private HttpSession httpSession;

    @BeforeEach
    public void setUp() {
        userMapper.deleteAll();
        userMapper.resetId();
        categoryMapper.deleteAll();
    }

    @Test
    @DisplayName("게스트 유저가 회원가입에 성공한다.")
    public void test() {
        final String phoneNumber = "01012345678";
        final GuestUserSignUpRequestDto guestUserSignUpRequestDto = new GuestUserSignUpRequestDto("test", "1234", "1234", "홍길동", phoneNumber);
        httpSession.setAttribute("authenticatedPhoneNumber", phoneNumber);
        userSignUpService.signUp(guestUserSignUpRequestDto);
        final Optional<User> user = userMapper.selectUserByUserId(guestUserSignUpRequestDto.userId());
        assertThat(user.get().id()).isEqualTo(1);
        assertThat(user.get().userId()).isEqualTo(guestUserSignUpRequestDto.userId());
        assertThat(user.get().imageFileId()).isNull();
        assertThat(user.get().password()).isEqualTo(guestUserSignUpRequestDto.password());
        assertThat(user.get().name()).isEqualTo(guestUserSignUpRequestDto.name());
        assertThat(user.get().phoneNumber()).isEqualTo(guestUserSignUpRequestDto.phoneNumber());
        assertThat(user.get().userType()).isEqualTo(UserType.GUEST.getValue());
        assertThat(user.get().userStatus()).isEqualTo(UserStatus.SIGN_UP_SUCCESS.getValue());
    }

    @Test
    @DisplayName("게스트 유저가 휴대폰번호 인증을 완료하지 않아 예외가 발생한다.")
    public void test2() {
        final String phoneNumber = "01012345678";
        final GuestUserSignUpRequestDto guestUserSignUpRequestDto = new GuestUserSignUpRequestDto("test", "1234", "1234", "홍길동", phoneNumber);
        assertThrows(NotAuthenticatedPhoneNumberException.class, () -> userSignUpService.signUp(guestUserSignUpRequestDto));
    }

    @Test
    @DisplayName("게스트 유저가 존재하는 아이디를 입력하여 예외가 발생한다.")
    public void test3() {
        userMapper.saveUser(new UserSaveRequestDto(null, "test", null, "4567", "테스트", "01011111111", UserType.GUEST.getValue(), UserStatus.SIGN_UP_SUCCESS.getValue()));
        final String phoneNumber = "01012345678";
        httpSession.setAttribute("authenticatedPhoneNumber", phoneNumber);
        final GuestUserSignUpRequestDto guestUserSignUpRequestDto = new GuestUserSignUpRequestDto("test", "1234", "1234", "홍길동", phoneNumber);
        assertThrows(DuplicateUserIdException.class, () -> userSignUpService.signUp(guestUserSignUpRequestDto));
    }

    @Test
    @DisplayName("게스트 유저가 비밀번호와 검증 비밀번호를 다르게 입력하여 예외가 발생한다.")
    public void test4() {
        final String phoneNumber = "01012345678";
        httpSession.setAttribute("authenticatedPhoneNumber", phoneNumber);
        final GuestUserSignUpRequestDto guestUserSignUpRequestDto = new GuestUserSignUpRequestDto("test", "1234", "5678", "홍길동", phoneNumber);
        assertThrows(PasswordMismatchException.class, () -> userSignUpService.signUp(guestUserSignUpRequestDto));
    }

    @Test
    @DisplayName("호스트 유저가 회원가입에 성공한다.")
    public void test21() throws IOException {
        final String phoneNumber = "01012345678";
        httpSession.setAttribute("authenticatedPhoneNumber", phoneNumber);
        final MultipartFile multipartFile = new MockMultipartFile("test", "test.txt", "text/plain", "Hello World".getBytes());
        final HostUserSignUpRequestDto hostUserSignUpRequestDto = new HostUserSignUpRequestDto("test", "1234", "1234", "홍길동", phoneNumber, 1, multipartFile);
        userSignUpService.signUp(hostUserSignUpRequestDto);
        final Optional<User> user = userMapper.selectUserByUserId(hostUserSignUpRequestDto.userId());
        assertThat(user.get().id()).isEqualTo(1);
        assertThat(user.get().userId()).isEqualTo(hostUserSignUpRequestDto.userId());
        assertThat(user.get().password()).isEqualTo(hostUserSignUpRequestDto.password());
        assertThat(user.get().name()).isEqualTo(hostUserSignUpRequestDto.name());
        assertThat(user.get().phoneNumber()).isEqualTo(hostUserSignUpRequestDto.phoneNumber());
        assertThat(user.get().userType()).isEqualTo(UserType.HOST.getValue());
        assertThat(user.get().userStatus()).isEqualTo(UserStatus.SIGN_UP_PROCEEDING.getValue());
        final Integer imageFileId = user.get().imageFileId();
        final Optional<ImageFile> imageFileById = imageFileMapper.findImageFileById(imageFileId);
        assertThat(new FileInputStream(imageFileById.get().path()).readAllBytes()).isEqualTo("Hello World".getBytes(StandardCharsets.UTF_8));
    }
}