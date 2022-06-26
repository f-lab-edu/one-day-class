package com.one.domain.category.infrastructure;

import com.one.domain.category.domain.UserBigCategoryDao;
import com.one.domain.category.dto.UserBigCategorySaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserBigCategoryDaoImpl implements UserBigCategoryDao {

    private final CategoryMapper categoryMapper;

    public void save(final UserBigCategorySaveDto userBigCategorySaveDto) {
        categoryMapper.save(userBigCategorySaveDto);
    }
}
