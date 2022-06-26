package com.one.domain.user.service;

import com.one.domain.category.domain.UserBigCategoryDao;
import com.one.domain.category.dto.UserBigCategorySaveDto;
import com.one.domain.file.domain.ImageFileManager;
import com.one.domain.file.dto.ImageFileSaveDto;
import com.one.domain.sms.domain.SmsAuthenticationManager;
import com.one.domain.user.domain.UserDao;
import com.one.domain.user.dto.UserSaveDto;
import com.one.domain.user.dto.GuestUserSignUpDto;
import com.one.domain.user.dto.HostUserSignUpDto;
import com.one.domain.user.exception.DuplicateUserIdException;
import com.one.domain.user.exception.PasswordMismatchException;
import com.one.domain.file.domain.ImageFileType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final Environment environment;
    private final UserDao userDao;
    private final ImageFileManager imageFileManager;
    private final SmsAuthenticationManager smsAuthenticationManager;
    private final UserBigCategoryDao userBigCategoryDao;

    @Transactional
    public void signUp(final GuestUserSignUpDto guestUserSignUpDto) {
        smsAuthenticationManager.checkAuthenticatedPhoneNumber(guestUserSignUpDto.phoneNumber());
        checkDuplicateUserId(guestUserSignUpDto.userId());
        checkPassword(guestUserSignUpDto.password(), guestUserSignUpDto.password2());
        userDao.save(UserSaveDto.from(guestUserSignUpDto));
    }

    @Transactional
    public void signUp(final HostUserSignUpDto hostUserSignUpDto) {
        smsAuthenticationManager.checkAuthenticatedPhoneNumber(hostUserSignUpDto.phoneNumber());
        final ImageFileSaveDto imageFileSaveDto = ImageFileSaveDto.of(environment.getProperty("file.dir"), hostUserSignUpDto.multipartFile(), ImageFileType.USER);
        final int imageFileId = imageFileManager.upload(imageFileSaveDto);
        imageFileManager.save(hostUserSignUpDto.multipartFile(), imageFileSaveDto.path());
        final int userId = userDao.save(UserSaveDto.of(imageFileId, hostUserSignUpDto)).get().id();
        userBigCategoryDao.save(new UserBigCategorySaveDto(userId, hostUserSignUpDto.bigCategoryId()));
    }

    public void checkPassword(final String password, final String password2) {
        if (!password.equals(password2)) {
            throw new PasswordMismatchException();
        }
    }

    public void checkDuplicateUserId(final String userId) {
        if (userDao.findByUserId(userId).isPresent()) {
            throw new DuplicateUserIdException();
        }
    }
}