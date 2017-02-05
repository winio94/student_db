package com.comon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;

/**
 * Created by Michał on 2017-02-04.
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerErrorHandlerTest {

    private static final String MESSAGE = "some error message";
    private static final String VIEW_NAME = "error";
    private static final String REQUEST_ERROR_MESSAGE_ATTR = "errorMessage";
    private HttpServletRequest request = new MockHttpServletRequest();
    private Exception exception = new RuntimeException(MESSAGE);

    @InjectMocks
    private ControllerErrorHandler errorHandler;

    @Test
    public void shouldReturnErrorViewName() throws Exception {
        String errorViewName = errorHandler.processError(request, exception);

        assertEquals(VIEW_NAME, errorViewName);
    }

    @Test
    public void shouldAddErrorMessageAttributeToRequestFromGivenException() throws Exception {
        errorHandler.processError(request, exception);

        assertEquals(exception.getMessage(), request.getAttribute(REQUEST_ERROR_MESSAGE_ATTR));
    }
}