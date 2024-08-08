package com.fiap.tc.core.domain.exception.handler;

import com.fiap.tc.adapters.driver.web.response.DefaultResponse;
import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.util.CollectionUtils.isEmpty;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<DefaultResponse> defaultExceptionHandler(Exception e) {

        log.error(e.getMessage(), e);

        DefaultResponse response = new DefaultResponse();
        response.setStatus("ERROR");
        response.setMessage("Unexpected error!");

        return status(INTERNAL_SERVER_ERROR).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        log.warn(e.getMessage(), e);

        DefaultResponse response = new DefaultResponse();
        response.setStatus(BAD_REQUEST.name());
        response.getMessages().addAll(getValidationMessageErrors(e));

        return status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<DefaultResponse> notFoundExceptionHandler(NotFoundException e) {

        log.warn(e.getMessage(), e);

        DefaultResponse response = new DefaultResponse();
        response.setStatus(NOT_FOUND.name());
        response.setMessage(e.getMessage());

        return status(NOT_FOUND).body(response);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<DefaultResponse> badRequestExceptionHandler(BadRequestException e) {

        log.warn(e.getMessage(), e);

        DefaultResponse response = new DefaultResponse();
        response.setStatus(BAD_REQUEST.name());
        if (!isEmpty(e.getErrors())) {
            response.setMessages(e.getErrors());
        } else {
            response.setMessage(e.getMessage());
        }


        return status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = {IllegalStateException.class, IllegalArgumentException.class,
            NoSuchElementException.class})
    public ResponseEntity<DefaultResponse> badRequestExceptionHandler(RuntimeException e) {

        log.warn(e.getMessage(), e);

        DefaultResponse response = new DefaultResponse();
        response.setStatus(BAD_REQUEST.name());
        response.setMessage(e.getMessage());

        return status(BAD_REQUEST).body(response);
    }


    private Set<String> getValidationMessageErrors(MethodArgumentNotValidException e) {
        var messages = e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toSet());


        var fields = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getField).toList();

        return fields.stream().flatMap(field -> messages.stream()
                        .map(message -> field.concat(": ").concat(message)))
                .collect(Collectors.toSet());


    }

}
