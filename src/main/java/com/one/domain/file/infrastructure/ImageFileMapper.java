package com.one.domain.file.infrastructure;

import com.one.domain.file.domain.ImageFile;
import com.one.domain.file.dto.ImageFileSaveDto;
import com.one.domain.file.dto.ImageFileSaveRequestRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ImageFileMapper {
    int saveImageFile(ImageFileSaveDto imageFileSaveDto);

    int saveImageFileByRecord(ImageFileSaveRequestRecord imageFileSaveRequestRecord);

    Optional<ImageFile> findImageFileById(int id);

    Optional<ImageFile> findByName(String name);
}
