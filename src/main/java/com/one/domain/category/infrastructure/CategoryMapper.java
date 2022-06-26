package com.one.domain.category.infrastructure;

import com.one.domain.category.domain.UserBigCategorySaveDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    void save(UserBigCategorySaveDto userBigCategorySaveDto);
}
