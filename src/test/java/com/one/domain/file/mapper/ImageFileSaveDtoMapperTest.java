package com.one.domain.file.mapper;

import com.one.domain.file.dto.ImageFileSaveDto;
import com.one.domain.file.model.ImageFile;
import com.one.global.common.code.ImageFileType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
class ImageFileSaveDtoMapperTest {

    @Autowired
    private ImageFileMapper imageFileMapper;

    @Test
    @DisplayName("이미지 파일 정보를 DB에 저장하고 조회한다")
    public void test() {
        ImageFileSaveDto imageFileSaveDto = new ImageFileSaveDto(null, "testPath", "testName", ImageFileType.A);
        imageFileMapper.saveImageFile(imageFileSaveDto);
        Optional<ImageFile> imageFile = imageFileMapper.findImageFileById(imageFileSaveDto.getId());
        Assertions.assertThat(imageFile.get().path()).isEqualTo(imageFileSaveDto.getPath());
        Assertions.assertThat(imageFile.get().name()).isEqualTo(imageFileSaveDto.getName());
        Assertions.assertThat(imageFile.get().contentType()).isEqualTo(imageFileSaveDto.getContentType());
    }

}