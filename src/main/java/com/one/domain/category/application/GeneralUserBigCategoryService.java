package com.one.domain.category.application;

import com.one.domain.category.dto.UserBigCategorySaveRequestDto;
import com.one.domain.category.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralUserBigCategoryService implements UserBigCategoryService {

    private final CategoryMapper categoryMapper;

    @Override
    public void save(final UserBigCategorySaveRequestDto userBigCategorySaveRequestDto) {
        categoryMapper.saveUserBigCategory(userBigCategorySaveRequestDto);
    }
}
