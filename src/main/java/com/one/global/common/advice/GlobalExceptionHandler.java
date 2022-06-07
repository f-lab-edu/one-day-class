package com.one.global.common.advice;

import com.one.domain.sms.exception.AlreadyAuthenticatedPhoneNumberException;
import com.one.domain.sms.exception.AuthenticationNumberMismatchException;
import com.one.domain.sms.exception.SmsAuthenticationNotFoundException;
import com.one.domain.sms.exception.SmsSendFailedException;
import com.one.global.common.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.one.global.common.code.ResponseCode.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return ErrorResponse.of(E001, e.getBindingResult());
    }

    @ExceptionHandler(AlreadyAuthenticatedPhoneNumberException.class)
    ResponseEntity<ErrorResponse> handleAlreadyAuthenticatedPhoneNumberException(final AlreadyAuthenticatedPhoneNumberException e) {
        return ErrorResponse.of(E003);
    }

    @ExceptionHandler(AuthenticationNumberMismatchException.class)
    ResponseEntity<ErrorResponse> handleAuthenticationNumberMismatchException(final AuthenticationNumberMismatchException e) {
        return ErrorResponse.of(E004);
    }

    @ExceptionHandler(SmsAuthenticationNotFoundException.class)
    ResponseEntity<ErrorResponse> handleSmsAuthenticationNotFoundException(final SmsAuthenticationNotFoundException e) {
        return ErrorResponse.of(E005);
    }

    @ExceptionHandler(SmsSendFailedException.class)
    ResponseEntity<ErrorResponse> handleSmsSendFailedException(final SmsSendFailedException e) {
        return ErrorResponse.of(E006);
    }
}