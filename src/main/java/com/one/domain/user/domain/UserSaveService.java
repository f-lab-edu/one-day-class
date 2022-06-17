package com.one.domain.user.domain;

import com.one.domain.user.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSaveService {

    private final UserMapper userMapper;

    public int save(UserSaveRequestDto userSaveRequestDto) {
        userMapper.saveUser(userSaveRequestDto);
        return userSaveRequestDto.getId();
    }
}
