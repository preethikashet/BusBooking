package com.example.exceptions;

import org.springframework.validation.BindingResult;
import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {
    private final Map<String, String> validationErrors;

    public ValidationException(String message, BindingResult bindingResult) {
        super(message);
        this.validationErrors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage()));
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
}