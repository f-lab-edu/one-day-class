package com.one.global.common.response;

import com.one.global.common.code.ResponseCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public record CommonResponse(int status, String message, String detail, Map<String, Object> data) {
    public static ResponseEntity<CommonResponse> of(final ResponseCode responseCode) {
        CommonResponse commonResponse = new CommonResponse(responseCode.getHttpStatus().value(), responseCode.getHttpStatus().getReasonPhrase(), responseCode.getMessage(), new HashMap<>());
        return new ResponseEntity<>(commonResponse, responseCode.getHttpStatus());
    }
    public static ResponseEntity<CommonResponse> of(final ResponseCode responseCode, final Map<String, Object> data) {
        CommonResponse commonResponse = new CommonResponse(responseCode.getHttpStatus().value(), responseCode.getHttpStatus().getReasonPhrase(), responseCode.getMessage(), data);
        return new ResponseEntity<>(commonResponse, responseCode.getHttpStatus());
    }
}
