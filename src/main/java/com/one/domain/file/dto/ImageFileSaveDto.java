package com.one.domain.file.dto;

import com.one.domain.file.domain.ImageFileType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
public record ImageFileSaveDto(String path, String name, int contentType) {

    public static ImageFileSaveDto of(final String fileDirectory, final MultipartFile multipartFile, final ImageFileType imageFileType) {
        final String originalFilename = multipartFile.getOriginalFilename();
        final String storedFileName = makeStoredFileName(originalFilename);
        final String fullPath = getFullPath(fileDirectory, storedFileName);
        return new ImageFileSaveDto(fullPath, storedFileName, imageFileType.getValue());
    }

    private static String getFullPath(final String fileDirectory, final String storedFileName) {
        final StringBuilder sb = new StringBuilder();
        return sb.append(fileDirectory).append(storedFileName).toString();
    }

    /**
     * 저장할 파일명은 업로드한 파일명을 그대로 사용해서는 안된다. 유저가 업로드한 파일명이 중복될 수 있기 때문이다.
     * 따라서 고유한 파일명으로 재정의할 필요가 있다.
     *
     * @param originalFilename
     * @return 저장할 파일명
     */
    private static String makeStoredFileName(final String originalFilename) {
        final StringBuilder sb = new StringBuilder();
        return sb.append(UUID.randomUUID()).append(".")
                .append(originalFilename.substring(originalFilename.lastIndexOf(".") + 1)).toString();
    }
}
