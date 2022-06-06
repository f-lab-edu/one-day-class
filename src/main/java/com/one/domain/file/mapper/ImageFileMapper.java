package com.one.domain.file.mapper;

import com.one.domain.file.dto.ImageFileSaveDto;
import com.one.domain.file.model.ImageFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ImageFileMapper {
    int saveImageFile(ImageFileSaveDto imageFileSaveDto);

    Optional<ImageFile> findImageFileById(int id);
}
