package com.one.domain.file.application;

import com.one.domain.file.code.ImageFileType;
import com.one.domain.file.domain.FileManager;
import com.one.domain.file.dto.ImageFileSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileManagementService {

    private final FileManager fileManager;
    private final Environment environment;

    @Transactional
    public int upload(final MultipartFile multipartFile, final ImageFileType imageFileType) {
        final ImageFileSaveRequestDto imageFileSaveRequestDto = ImageFileSaveRequestDto.of(environment.getProperty("file.dir"), multipartFile, imageFileType);
        final int id = fileManager.upload(imageFileSaveRequestDto);
        fileManager.save(multipartFile, imageFileSaveRequestDto.getPath());
        return id;
    }
}
