package com.gpsolutions.hotelapi.model.exception;

import java.util.Map;

public class ExceptionBody {

    private String message;
    private Map<String, String> errors;

    public ExceptionBody() {}

    public ExceptionBody(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public ExceptionBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}