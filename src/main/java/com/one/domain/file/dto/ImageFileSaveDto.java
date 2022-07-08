package com.one.domain.file.dto;

import com.one.domain.file.domain.ImageFileType;

import java.util.UUID;

public record ImageFileSaveDto(String path, String name, int contentType) {

    /**
     * 저장할 파일명은 업로드한 파일명을 그대로 사용해서는 안된다. 유저가 업로드한 파일명이 중복될 수 있기 때문이다.
     * 따라서 고유한 파일명으로 재정의할 필요가 있다.
     */
    public static ImageFileSaveDto of(final String fileDirectory, final String originalFilename, final ImageFileType imageFileType) {
        final StringBuilder sb = new StringBuilder();
        final String storedFileName = sb.append(UUID.randomUUID()).append(".").append(originalFilename.substring(originalFilename.lastIndexOf(".") + 1)).toString();
        return new ImageFileSaveDto(fileDirectory + storedFileName, storedFileName, imageFileType.getValue());
    }

}
