package com.example.demo.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public String getPath() {
        throw new UnsupportedOperationException("Unimplemented method 'getPath'");
    }
}
