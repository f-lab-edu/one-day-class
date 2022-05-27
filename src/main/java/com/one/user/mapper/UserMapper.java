package com.one.user.mapper;

import com.one.user.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectUserById(int id);

}
