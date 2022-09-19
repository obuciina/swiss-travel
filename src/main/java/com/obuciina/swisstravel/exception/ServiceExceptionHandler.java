package com.obuciina.swisstravel.exception;

import com.obuciina.swisstravel.exception.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import java.net.UnknownHostException;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return new ResponseEntity<>(processFieldErrors(fieldErrors), BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(processErrorMessage(BAD_REQUEST.value(), ex.getMessage()), BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = {UnknownHostException.class})
    public ResponseEntity<?> handleUnknownHostException(UnknownHostException ex) {
        return new ResponseEntity<>(processErrorMessage(INTERNAL_SERVER_ERROR.value(), ex.getMessage()), INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<?> handleHttpClientErrorException(HttpClientErrorException ex) {
        return new ResponseEntity<>(processErrorMessage(INTERNAL_SERVER_ERROR.value(), ex.getMessage()), INTERNAL_SERVER_ERROR);
    }

    private ErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ErrorDTO error = new ErrorDTO(BAD_REQUEST.value(), "Validation Error");
        for (FieldError fieldError : fieldErrors) {
            error.addError(fieldError.getDefaultMessage());
        }
        return error;
    }

    private ErrorDTO processErrorMessage(int status, String message) {
        ErrorDTO error = new ErrorDTO(status, message);
        error.addError(message);
        return error;
    }

}
