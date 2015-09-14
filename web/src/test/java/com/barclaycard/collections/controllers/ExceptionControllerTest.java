package com.barclaycard.collections.controllers;

import com.barclaycard.collections.LogMessages;
import com.equalexperts.logging.OpsLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionControllerTest {

    @Mock
    private OpsLogger<LogMessages> opsLogger;

    @Test
    public void handleException_shouldLogTheUnhandledException() throws Exception {
        Exception expected = new IllegalArgumentException("foo bar");
        ExceptionController controller = new ExceptionController(opsLogger);
        controller.handleException(expected);
        verify(opsLogger).log(LogMessages.Unexpected, expected);
    }

}