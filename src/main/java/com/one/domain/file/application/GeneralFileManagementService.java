package com.one.domain.file.application;

import com.one.domain.file.dto.ImageFileSaveRequestDto;
import com.one.domain.file.exception.ImageFileSaveFailedException;
import com.one.domain.file.mapper.ImageFileMapper;
import com.one.global.common.code.ImageFileType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeneralFileManagementService implements FileManagementService {

    private final Environment environment;
    private final ImageFileMapper imageFileMapper;

    /**
     * @param multipartFile
     * @param imageFileType
     * @return 업로드된 이미지 파일 식별자
     * @throws IOException
     */
    @Override
    public int upload(final MultipartFile multipartFile, final ImageFileType imageFileType) throws IOException {
        log.debug("multipartFile: {}", multipartFile);
        log.debug("imageFileType: {}", imageFileType);
        final Optional<Integer> id;
        try {
            final String originalFilename = multipartFile.getOriginalFilename();
            final String storedFileName = makeStoredFileName(originalFilename);
            final String fullPath = getFullPath(storedFileName);
            log.info("originalFilename: {}", originalFilename);
            log.info("storedFileName: {}", storedFileName);
            log.info("fullPath: {}", fullPath);
            multipartFile.transferTo(Paths.get(fullPath).toAbsolutePath().toFile());
            final ImageFileSaveRequestDto imageFileSaveRequestDto = new ImageFileSaveRequestDto(null, fullPath, storedFileName, imageFileType);
            final int i = imageFileMapper.saveImageFile(imageFileSaveRequestDto);
            if (i != 1) {
                throw new RuntimeException();
            }
            if (true) {
                throw new RuntimeException();
            }
            id = Optional.ofNullable(imageFileSaveRequestDto.getId());
            log.debug("id: {}", id);
        } catch (RuntimeException e) {
            throw new ImageFileSaveFailedException();
        }
        return id.orElseThrow(() -> new ImageFileSaveFailedException());
    }

    private String getFullPath(final String storedFileName) {
        final StringBuilder sb = new StringBuilder();
        String s = sb.append(environment.getProperty("file.dir")).append(storedFileName).toString();
        return s;
    }

    /**
     * 저장할 파일명은 업로드한 파일명을 그대로 사용해서는 안된다. 유저가 업로드한 파일명이 중복될 수 있기 때문이다.
     * 따라서 고유한 파일명으로 재정의할 필요가 있다.
     * @param originalFilename
     * @return 저장할 파일명
     */
    private String makeStoredFileName(final String originalFilename) {
        final StringBuilder sb = new StringBuilder();
        return sb.append(UUID.randomUUID()).append(".")
                .append(originalFilename.substring(originalFilename.lastIndexOf(".") + 1)).toString();
    }

    @Override
    public void download(int imageFileId) {

    }
}
