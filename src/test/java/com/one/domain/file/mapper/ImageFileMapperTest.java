package com.one.domain.file.mapper;

import com.one.domain.file.infrastructure.ImageFileMapper;
import com.one.domain.file.dto.ImageFileSaveDto;
import com.one.domain.file.dto.ImageFileSaveRequestRecord;
import com.one.domain.file.domain.ImageFile;
import com.one.domain.file.domain.ImageFileType;
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
        final ImageFileSaveDto imageFileSaveDto = new ImageFileSaveDto(null, "testPath", "testName", ImageFileType.USER.getValue());
        imageFileMapper.saveImageFile(imageFileSaveDto);
        Optional<ImageFile> imageFile = imageFileMapper.findImageFileById(imageFileSaveDto.getId());
        Assertions.assertThat(imageFile.get().path()).isEqualTo(imageFileSaveDto.getPath());
        Assertions.assertThat(imageFile.get().name()).isEqualTo(imageFileSaveDto.getName());
        Assertions.assertThat(imageFile.get().contentType()).isEqualTo(imageFileSaveDto.getContentType());
    }

    @Test
    public void test2() {
        final ImageFileSaveRequestRecord imageFileSaveRequestRecord = new ImageFileSaveRequestRecord(null, "testPath", "testName", ImageFileType.USER.getValue());
        imageFileMapper.saveImageFileByRecord(imageFileSaveRequestRecord);
    }
}