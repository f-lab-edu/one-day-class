package com.one.domain.file.infrastructure;

import com.one.domain.file.domain.ImageFile;
import com.one.domain.file.dto.ImageFileSaveRequestDto;
import com.one.domain.file.dto.ImageFileSaveRequestRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ImageFileMapper {
    int saveImageFile(ImageFileSaveRequestDto imageFileSaveRequestDto);

    int saveImageFileByRecord(ImageFileSaveRequestRecord imageFileSaveRequestRecord);

    Optional<ImageFile> findImageFileById(int id);
}
