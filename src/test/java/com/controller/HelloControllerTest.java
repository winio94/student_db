package com.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;

/**
 * Created by Michał on 2016-11-27.
 */
@RunWith(MockitoJUnitRunner.class)
public class HelloControllerTest {

    private static final String MESSAGE_VALUE = "Hello world";
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String HELLO_VIEW_NAME = "hello";
    private HelloController helloController;

    @Before
    public void setUp() throws Exception {
        helloController = new HelloController();
    }

    @Test
    public void getHello() throws Exception {
        ModelAndView hello = helloController.hello();

        assertEquals(HELLO_VIEW_NAME, hello.getViewName());
        assertEquals(MESSAGE_VALUE, hello.getModel().get(MESSAGE_ATTRIBUTE));
    }
}
