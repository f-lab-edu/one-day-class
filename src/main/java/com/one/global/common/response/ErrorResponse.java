package com.one.global.common.response;
import com.one.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record ErrorResponse(int status, String message, String detail, List<FieldError> errors) {

    public static ResponseEntity<ErrorResponse> of(final ResponseCode responseCode) {
        final ErrorResponse errorResponse = new ErrorResponse(responseCode.getHttpStatus().value(), responseCode.getHttpStatus().getReasonPhrase(), responseCode.getMessage(), new ArrayList<>());
        return new ResponseEntity<>(errorResponse, responseCode.getHttpStatus());
    }

    public static ResponseEntity<ErrorResponse> of(final Exception e) {
        final ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), new ArrayList<>());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<ErrorResponse> of(final ResponseCode responseCode, final BindingResult bindingResult) {
        final ErrorResponse errorResponse = new ErrorResponse(responseCode.getHttpStatus().value(), responseCode.getHttpStatus().getReasonPhrase(), responseCode.getMessage(), FieldError.of(bindingResult));
        return new ResponseEntity<>(errorResponse, responseCode.getHttpStatus());
    }
    record FieldError(String field, String value, String reason){
        public static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()
                    ))
                    .collect(Collectors.toList());
        }
    }

}
