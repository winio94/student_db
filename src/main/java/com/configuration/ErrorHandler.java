package com.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Michał on 2016-12-02.
 */
@ControllerAdvice
public class ErrorHandler {

    private static final String ERROR_VIEW_NAME = "error";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public String processError(HttpServletRequest request, RuntimeException e) {
        request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, e.getMessage());
        return ERROR_VIEW_NAME;
    }
}