package com.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Michał on 2016-12-02.
 */
@Controller("error")
public class AppErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";
    private static final String ERROR_VIEW = "error";

    @RequestMapping(value = ERROR_PATH)
    public String error() {
        return ERROR_VIEW;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}