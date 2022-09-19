package com.obuciina.swisstravel.exception.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorDTO {
    private final int status;
    private final String message;
    private final List<String> errors = new ArrayList<>();

    public ErrorDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String message) {
        errors.add(message);
    }
}
