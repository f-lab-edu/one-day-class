package com.one.domain.file.mapper;

import com.one.domain.file.dto.ImageFileSaveRequestDto;
import com.one.domain.file.model.ImageFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ImageFileMapper {
    int saveImageFile(ImageFileSaveRequestDto imageFileSaveRequestDto);

    Optional<ImageFile> findImageFileById(int id);
}
