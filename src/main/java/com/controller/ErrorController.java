package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Michał on 2016-12-02.
 */
@Controller("error")
public class ErrorController {

    private static final String ERROR_VIEW_NAME = "error";

    @RequestMapping
    public String onError() {
        return ERROR_VIEW_NAME;
    }
}