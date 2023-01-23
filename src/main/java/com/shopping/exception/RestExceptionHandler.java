/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.exception;


import com.shopping.view.ResponseView;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;


@RestControllerAdvice
public class RestExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseView formValidation(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        String[] arrOfStr = errors.get(0).split("-", 2);
        String errorCode = null;
        String errorMessage = null;
        try {
            if (arrOfStr.length > 1) {
                errorCode = arrOfStr[0];
                errorMessage = arrOfStr[1];
            } else {
                errorMessage = errors.get(0);
            }
        } catch (Exception e) {

        }
        if (errorMessage == null) {
            errorMessage = errors.get(0);
        }
        return new ResponseView(errorMessage, errorCode);
    }

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity<Object> responseStatus(ResponseStatusException ex) {

        String[] arrOfStr = ex.getReason().split("-", 2);
        String errorCode = null;
        String errorMessage = null;
        try {
            if (arrOfStr.length > 1) {
                errorCode = arrOfStr[0];
                errorMessage = arrOfStr[1];
            } else {
                errorMessage = ex.getReason();
            }
        } catch (Exception e) {

        }
        if (errorMessage == null) {
            String field = null;
            if (errorCode != null && errorCode.contains("java.lang.NumberFormatException")) {
                field = errorCode.replaceAll("^.*for property '(.*)';.*$", "$1");
            }
            if (field != null && !"".equals(field)) {
                errorCode = "invalid " + field;
            }
            errorMessage = errorCode;
        }
        LOGGER.warn(ex.getMessage());
        ResponseView responseView = new ResponseView(errorMessage, errorCode);
        return new ResponseEntity<>(responseView, null, ex.getStatus());
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseView methodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
    	LOGGER.warn(ex.getMessage());
        return new ResponseView(ex.getMessage(),"600");
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseView mediaTypeNotAccepted(HttpMediaTypeNotSupportedException ex) {
    	LOGGER.warn(ex.getMessage());
        return new ResponseView("mediaTypeNotSupported","3200");
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseView unKnownException(Exception ex) {
    	LOGGER.warn(ex.getMessage());
    	ex.printStackTrace();
        return new ResponseView("BAD_REQUEST", ex.getMessage());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseView illegalException(IllegalArgumentException ex) {
    	LOGGER.warn(ex.getMessage());
        return new ResponseView("Invalid input arguments", "1532");
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseView validException(HttpMessageNotReadableException ex) {
    	LOGGER.warn(ex.getMessage());
        return new ResponseView("Invalid JSON","1531");
    }
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseView TypeMismatchException(MethodArgumentTypeMismatchException ex) {
    	LOGGER.warn(ex.getMessage());
        
        return new ResponseView(ex.getName() + " should be of type " + ex.getRequiredType().getName(),"1533");
    }
    

    
}
