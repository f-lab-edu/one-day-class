package com.one.domain.user.application;

import com.one.domain.user.dto.UserSaveRequestDto;

public interface UserSaveService {
    int save(UserSaveRequestDto userSaveRequestDto);
}
