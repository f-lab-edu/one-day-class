package com.one.domain.category.application;

import com.one.domain.category.domain.UserBigCategory;
import com.one.domain.category.domain.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBigCategorySaveService {

    private final CategoryMapper categoryMapper;

    public void save(final UserBigCategory userBigCategory) {
        categoryMapper.saveUserBigCategory(userBigCategory);
    }
}
