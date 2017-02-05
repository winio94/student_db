package com.comon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Michał on 2016-12-02.
 */
@ControllerAdvice
class ControllerErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerErrorHandler.class);
    private static final String ERROR_VIEW_NAME = "error";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public String processError(HttpServletRequest request, Exception e) {
        LOGGER.error("Exception occurred. Message : {}", e.getMessage());
        request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, e.getMessage());
        return ERROR_VIEW_NAME;
    }
}