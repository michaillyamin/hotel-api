package com.gpsolutions.hotelapi.model.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(HotelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionBody> handleHotelNotFoundException(HotelNotFoundException ex) {
        log.error("Hotel not found: {}", ex.getMessage() );
        ExceptionBody exceptionBody = new ExceptionBody(ex.getMessage());

        return new ResponseEntity<>(exceptionBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionBody> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("Validation exception occurred: {}", ex.getMessage());
        ExceptionBody exceptionBody = new ExceptionBody("Validation Error");

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        exceptionBody.setErrors(errors.stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage))
        );
        return new ResponseEntity<>(exceptionBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionBody> handleConstraintViolation(ConstraintViolationException ex) {
        Set<String> errorFields = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath().toString())
                .collect(Collectors.toSet());

        log.error("Data validation failed for fields: {}. Violation count: {}",
                errorFields,
                ex.getConstraintViolations().size()
        );

        ExceptionBody exceptionBody = new ExceptionBody("Validation Error");
        exceptionBody.setErrors(ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                )));

        return new ResponseEntity<>(exceptionBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionBody> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("Illegal argument exception occurred: ", ex);
        ExceptionBody exceptionBody = new ExceptionBody(ex.getMessage());
        return new ResponseEntity<>(exceptionBody, HttpStatus.BAD_REQUEST);
    }
}
