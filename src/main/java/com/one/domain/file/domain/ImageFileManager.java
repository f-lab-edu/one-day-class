package com.one.domain.file.domain;

import com.one.domain.file.dto.ImageFileSaveDto;
import com.one.domain.file.exception.ImageFileSaveFailedException;
import com.one.domain.file.infrastructure.ImageFileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageFileManager {

    private final ImageFileMapper imageFileMapper;

    /**
     * @param imageFileSaveDto
     * @return 업로드된 이미지 파일 식별자
     */
    public int upload(final ImageFileSaveDto imageFileSaveDto) {
        final int id;
        try {
            final int i = imageFileMapper.saveImageFile(imageFileSaveDto);
            if (i != 1) {
                throw new RuntimeException();
            }
            Optional<ImageFile> imageFile = imageFileMapper.findByName(imageFileSaveDto.name());
            id = imageFile.get().id();
        } catch (RuntimeException re) {
            log.error("이미지파일 저장 실패", re);
            throw new ImageFileSaveFailedException();
        }
        return id;
    }

    /**
     * @param multipartFile
     * @param fullPath
     */
    public void save(final MultipartFile multipartFile, final String fullPath) {
        try {
            multipartFile.transferTo(Paths.get(fullPath).toAbsolutePath().toFile());
        } catch (IOException e) {
            throw new ImageFileSaveFailedException();
        }
    }

}
