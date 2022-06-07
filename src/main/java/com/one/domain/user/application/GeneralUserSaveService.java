package com.one.domain.user.application;

import com.one.domain.user.dto.UserSaveRequestDto;
import com.one.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralUserSaveService implements UserSaveService {

    private final UserMapper userMapper;

    @Override
    public int save(UserSaveRequestDto userSaveRequestDto) {
        userMapper.saveUser(userSaveRequestDto);
        return userSaveRequestDto.getId();
    }
}
