package com.one.domain.user.domain.dao;

import com.one.domain.user.infrastructure.UserMapper;
import com.one.domain.user.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSaveDao {

    private final UserMapper userMapper;

    public int save(UserSaveRequestDto userSaveRequestDto) {
        userMapper.saveUser(userSaveRequestDto);
        return userSaveRequestDto.getId();
    }
}
