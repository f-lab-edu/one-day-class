package com.one.domain.category.mapper;

import com.one.domain.category.dto.UserBigCategorySaveRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    void saveUserBigCategory(UserBigCategorySaveRequestDto userBigCategorySaveRequestDto);

    void deleteAll();
}
