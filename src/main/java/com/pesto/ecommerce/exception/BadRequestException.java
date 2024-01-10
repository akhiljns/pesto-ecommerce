package com.pesto.ecommerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    @Getter
    protected final Integer code = 400;

    public BadRequestException(String msg) {
        super(msg);
    }
}
