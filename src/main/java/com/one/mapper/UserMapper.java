package com.one.mapper;

import com.one.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    Optional<User> selectUserById(int id);

}
