package com.one.domain.user.application;

import com.one.domain.category.application.UserBigCategoryService;
import com.one.domain.category.dto.UserBigCategorySaveRequestDto;
import com.one.domain.file.application.FileManagementService;
import com.one.domain.sms.application.SmsAuthenticationService;
import com.one.domain.user.dto.GuestUserSignUpRequestDto;
import com.one.domain.user.dto.HostUserSignUpRequestDto;
import com.one.domain.user.dto.UserSaveRequestDto;
import com.one.domain.user.exception.DuplicateUserIdException;
import com.one.domain.user.exception.PasswordMismatchException;
import com.one.domain.file.code.ImageFileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class GeneralUserSignUpService implements UserSignUpService {

    private final UserFindService userFindService;
    private final UserSaveService userSaveService;
    private final FileManagementService fileManagementService;
    private final SmsAuthenticationService smsAuthenticationService;
    private final UserBigCategoryService userBigCategoryService;

    @Override
    public void signUp(final GuestUserSignUpRequestDto guestUserSignUpRequestDto) {
        smsAuthenticationService.checkAuthenticatedPhoneNumber(guestUserSignUpRequestDto.phoneNumber());
        checkDuplicateUserId(guestUserSignUpRequestDto.userId());
        checkPassword(guestUserSignUpRequestDto.password(), guestUserSignUpRequestDto.password2());
        userSaveService.save(UserSaveRequestDto.of(guestUserSignUpRequestDto));
    }

    @Override
    @Transactional
    public void signUp(final HostUserSignUpRequestDto hostUserSignUpRequestDto) throws IOException {
        smsAuthenticationService.checkAuthenticatedPhoneNumber(hostUserSignUpRequestDto.phoneNumber());
        checkDuplicateUserId(hostUserSignUpRequestDto.userId());
        checkPassword(hostUserSignUpRequestDto.password(), hostUserSignUpRequestDto.password2());
        final int imageFileId = fileManagementService.upload(hostUserSignUpRequestDto.multipartFile(), ImageFileType.A);
        final int userId = userSaveService.save(UserSaveRequestDto.of(hostUserSignUpRequestDto, imageFileId));
        userBigCategoryService.save(new UserBigCategorySaveRequestDto(userId, hostUserSignUpRequestDto.bigCategoryId()));
    }

    private void checkPassword(final String password, final String password2) {
        if (!password.equals(password2)) {
            throw new PasswordMismatchException();
        }
    }

    private void checkDuplicateUserId(final String userId) {
        if (userFindService.findUserByUserId(userId).isPresent()) {
            throw new DuplicateUserIdException();
        }
    }
}
