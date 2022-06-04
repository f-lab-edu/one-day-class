package com.one.domain.file.mapper;

import com.one.domain.file.dto.ImageFileSaveDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageFileMapper {
    int save(ImageFileSaveDto imageFileSaveDto);
}
