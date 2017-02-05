package com.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Michał on 2017-02-05.
 */
@RunWith(MockitoJUnitRunner.class)
public class ErrorControllerTest {

    @InjectMocks
    private ErrorController errorController;

    @Test
    public void shouldReturnErrorViewName() throws Exception {
        String errorViewName = errorController.onError();

        assertEquals("error", errorViewName);
    }
}