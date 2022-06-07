package com.one.domain.user.application;

import com.one.domain.file.application.FileManagementService;
import com.one.domain.sms.application.SmsAuthenticationService;
import com.one.domain.user.dto.GuestUserSignUpRequestDto;
import com.one.domain.user.dto.HostUserSignUpRequestDto;
import com.one.domain.user.dto.UserSaveRequestDto;
import com.one.domain.user.exception.DuplicateUserIdException;
import com.one.global.common.code.ImageFileType;
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

    @Override
    public void signUp(final GuestUserSignUpRequestDto guestUserSignUpRequestDto) {
        smsAuthenticationService.checkAuthenticatedPhoneNumber(guestUserSignUpRequestDto.phoneNumber());
        checkDuplicateUserId(guestUserSignUpRequestDto.userId());
        userSaveService.save(UserSaveRequestDto.of(guestUserSignUpRequestDto));
    }

    @Override
    @Transactional
    public void signUp(final HostUserSignUpRequestDto hostUserSignUpRequestDto) throws IOException {
        smsAuthenticationService.checkAuthenticatedPhoneNumber(hostUserSignUpRequestDto.phoneNumber());
        checkDuplicateUserId(hostUserSignUpRequestDto.userId());
        final int imageFileId = fileManagementService.upload(hostUserSignUpRequestDto.multipartFile(), ImageFileType.A);
        userSaveService.save(UserSaveRequestDto.of(hostUserSignUpRequestDto, imageFileId));
    }

    private void checkDuplicateUserId(final String userId) {
        if (userFindService.findUserByUserId(userId).isPresent()) {
            throw new DuplicateUserIdException();
        }
    }
}
