package com.fiap.tc.core.domain.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
@Getter
@Setter
public class BadRequestException extends RuntimeException {
    private List<String> errors;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, List<String> listErrors) {
        super(message);
        errors = listErrors;
    }
}
