package com.one.domain.file.domain;

import com.one.domain.file.dto.ImageFileSaveRequestDto;
import com.one.domain.file.exception.ImageFileSaveFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileManager {

    private final ImageFileMapper imageFileMapper;

    /**
     * @param imageFileSaveRequestDto
     * @return 업로드된 이미지 파일 식별자
     */
    public int upload(final ImageFileSaveRequestDto imageFileSaveRequestDto) {
        final Optional<Integer> id;
        try {
            final int i = imageFileMapper.saveImageFile(imageFileSaveRequestDto);
            if (i != 1) {
                throw new RuntimeException();
            }
            id = Optional.ofNullable(imageFileSaveRequestDto.getId());
        } catch (RuntimeException re) {
            throw new ImageFileSaveFailedException();
        }
        return id.orElseThrow(() -> new ImageFileSaveFailedException());
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
