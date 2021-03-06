package com.one.domain.user.application;

import com.one.domain.category.application.UserBigCategorySaveService;
import com.one.domain.category.domain.UserBigCategory;
import com.one.domain.file.application.FileManagementService;
import com.one.domain.sms.domain.SmsAuthenticationService;
import com.one.domain.user.domain.UserFindService;
import com.one.domain.user.domain.UserSaveService;
import com.one.domain.user.dto.GuestUserSignUpRequestDto;
import com.one.domain.user.dto.HostUserSignUpRequestDto;
import com.one.domain.user.dto.UserSaveRequestDto;
import com.one.domain.user.exception.DuplicateUserIdException;
import com.one.domain.user.exception.PasswordMismatchException;
import com.one.domain.file.code.ImageFileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserSignUpService {

    private final UserFindService userFindService;
    private final UserSaveService userSaveService;
    private final FileManagementService fileManagementService;
    private final SmsAuthenticationService smsAuthenticationService;
    private final UserBigCategorySaveService userBigCategorySaveService;

    public void signUp(final GuestUserSignUpRequestDto guestUserSignUpRequestDto) {
        smsAuthenticationService.checkAuthenticatedPhoneNumber(guestUserSignUpRequestDto.phoneNumber());
        checkDuplicateUserId(guestUserSignUpRequestDto.userId());
        checkPassword(guestUserSignUpRequestDto.password(), guestUserSignUpRequestDto.password2());
        userSaveService.save(UserSaveRequestDto.of(guestUserSignUpRequestDto));
    }

    @Transactional
    public void signUp(final HostUserSignUpRequestDto hostUserSignUpRequestDto) {
        smsAuthenticationService.checkAuthenticatedPhoneNumber(hostUserSignUpRequestDto.phoneNumber());
        checkDuplicateUserId(hostUserSignUpRequestDto.userId());
        checkPassword(hostUserSignUpRequestDto.password(), hostUserSignUpRequestDto.password2());
        final int imageFileId = fileManagementService.upload(hostUserSignUpRequestDto.multipartFile(), ImageFileType.USER);
        final int userId = userSaveService.save(UserSaveRequestDto.of(hostUserSignUpRequestDto, imageFileId));
        userBigCategorySaveService.save(new UserBigCategory(userId, hostUserSignUpRequestDto.bigCategoryId()));
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
