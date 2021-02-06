package com.vishdev.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 7809200551672852690L; //TODO Why its needed here7 Video 5 1 min

    public ResourceNotFoundException(String message) {
        super(message);
    }


}
