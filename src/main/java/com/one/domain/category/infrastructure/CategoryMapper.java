package com.one.domain.category.infrastructure;

import com.one.domain.category.domain.UserBigCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    void saveUserBigCategory(UserBigCategory userBigCategory);
}
