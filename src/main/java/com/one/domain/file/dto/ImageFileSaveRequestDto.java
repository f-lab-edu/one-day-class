package com.one.domain.file.dto;

import com.one.domain.file.code.ImageFileType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ImageFileSaveRequestDto {
    private final Integer id;
    private final String path;
    private final String name;
    private final ImageFileType contentType;
}
