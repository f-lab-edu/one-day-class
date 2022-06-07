package com.one.domain.file.application;

import com.one.global.common.code.ImageFileType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileManagementService {
    int upload(MultipartFile multipartFile, ImageFileType imageFileType) throws IOException;
    void download(int imageFileId);
}