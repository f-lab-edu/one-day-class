package com.one.domain.file.application;

import com.one.domain.file.dto.ImageFileSaveDto;
import com.one.domain.file.mapper.ImageFileMapper;
import com.one.global.common.code.ImageFileType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeneralFileManagementService implements FileManagementService {

    private final Environment environment;
    private final ImageFileMapper imageFileMapper;

    @Override
    public int upload(final MultipartFile multipartFile, final ImageFileType imageFileType) throws IOException {
        final String originalFilename = multipartFile.getOriginalFilename();
        final String storedFileName = makeStoredFileName(originalFilename);
        final String fullPath = getFullPath(storedFileName);
        multipartFile.transferTo(new File(fullPath));
        final ImageFileSaveDto imageFileSaveDto = new ImageFileSaveDto(null, fullPath, storedFileName, imageFileType);
        return imageFileMapper.saveImageFile(imageFileSaveDto);
    }

    private String getFullPath(final String storedFileName) {
        final StringBuilder sb = new StringBuilder();
        return sb.append(environment.getProperty("file.dir")).append(storedFileName).toString();
    }

    private String makeStoredFileName(final String originalFilename) {
        final StringBuilder sb = new StringBuilder();
        return sb.append(UUID.randomUUID()).append(".")
                .append(originalFilename.substring(originalFilename.lastIndexOf(".") + 1)).toString();
    }

    @Override
    public void download(int imageFileId) {

    }
}
