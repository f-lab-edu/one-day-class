package com.one.domain.category.domain;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    void saveUserBigCategory(UserBigCategory userBigCategory);

    void deleteAll();
}
