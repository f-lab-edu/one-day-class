package com.one.domain.category.infrastructure;

import com.one.domain.category.dto.UserBigCategorySaveDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    void save(UserBigCategorySaveDto userBigCategorySaveDto);
}
