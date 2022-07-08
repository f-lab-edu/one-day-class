package com.one.domain.category.infrastructure;

import com.one.domain.category.domain.UserBigCategoryDao;
import com.one.domain.category.dto.UserBigCategorySaveDto;
import org.springframework.stereotype.Repository;

@Repository
public class UserBigCategoryDaoImpl implements UserBigCategoryDao {

    private final CategoryMapper categoryMapper;

    public UserBigCategoryDaoImpl(final CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public void save(final UserBigCategorySaveDto userBigCategorySaveDto) {
        categoryMapper.save(userBigCategorySaveDto);
    }
}
