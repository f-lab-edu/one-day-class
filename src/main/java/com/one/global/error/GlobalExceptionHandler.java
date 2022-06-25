package com.one.global.error;

import com.one.domain.file.exception.ImageFileSaveFailedException;
import com.one.domain.sms.exception.*;
import com.one.domain.user.exception.DuplicateUserIdException;
import com.one.domain.user.exception.PasswordMismatchException;
import com.one.domain.user.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.one.global.common.ResponseCode.*;

/**
 * 스프링부트가 제공하는 ExceptionResolver의 우선순위는 다음과 같다.
 * 1. ExceptionHandlerExceptionResolver -> @ExceptionHandler 처리
 * 2. ResponseStatusExceptionResolver -> @ResponseStatus(value = "HttpStatus.BAD_REQUEST"), ResponseStatusException
 * 3. DefaultHandlerExceptionResolver -> 스프링 내부 기본 예외 처리
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleException(final Exception e) {
        return ErrorResponse.of(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return ErrorResponse.of(E001, e.getBindingResult());
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ErrorResponse> handleUserNotFoundException(final UserNotFoundException e) {
        return ErrorResponse.of(E002);
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

    @ExceptionHandler(ImageFileSaveFailedException.class)
    ResponseEntity<ErrorResponse> handleImageFileSaveFailedException(final ImageFileSaveFailedException e) {
        return ErrorResponse.of(E007);
    }

    @ExceptionHandler(NotAuthenticatedPhoneNumberException.class)
    ResponseEntity<ErrorResponse> handleNotAuthenticatedPhoneNumberException(final NotAuthenticatedPhoneNumberException e) {
        return ErrorResponse.of(E009);
    }

    @ExceptionHandler(DuplicateUserIdException.class)
    ResponseEntity<ErrorResponse> handleDuplicateUserIdException(final DuplicateUserIdException e) {
        return ErrorResponse.of(E010);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    ResponseEntity<ErrorResponse> handlePasswordMismatchException(final PasswordMismatchException e) {
        return ErrorResponse.of(E011);
    }
}
