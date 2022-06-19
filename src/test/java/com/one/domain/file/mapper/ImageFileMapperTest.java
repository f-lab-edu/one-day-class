package com.one.domain.file.mapper;

import com.one.domain.file.domain.ImageFileMapper;
import com.one.domain.file.dto.ImageFileSaveRequestDto;
import com.one.domain.file.dto.ImageFileSaveRequestRecord;
import com.one.domain.file.domain.ImageFile;
import com.one.domain.file.code.ImageFileType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class ImageFileMapperTest {

    @Autowired
    private ImageFileMapper imageFileMapper;

    @Test
    @DisplayName("이미지 파일 정보를 DB에 저장하고 조회한다")
    public void test() {
        final ImageFileSaveRequestDto imageFileSaveRequestDto = new ImageFileSaveRequestDto(null, "testPath", "testName", ImageFileType.USER.getValue());
        imageFileMapper.saveImageFile(imageFileSaveRequestDto);
        Optional<ImageFile> imageFile = imageFileMapper.findImageFileById(imageFileSaveRequestDto.getId());
        Assertions.assertThat(imageFile.get().path()).isEqualTo(imageFileSaveRequestDto.getPath());
        Assertions.assertThat(imageFile.get().name()).isEqualTo(imageFileSaveRequestDto.getName());
        Assertions.assertThat(imageFile.get().contentType()).isEqualTo(imageFileSaveRequestDto.getContentType());
    }

    @Test
    public void test2() {
        final ImageFileSaveRequestRecord imageFileSaveRequestRecord = new ImageFileSaveRequestRecord(null, "testPath", "testName", ImageFileType.USER.getValue());
        imageFileMapper.saveImageFileByRecord(imageFileSaveRequestRecord);
    }
}