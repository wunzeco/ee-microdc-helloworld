package com.barclaycard.collections.controllers;

import com.barclaycard.collections.LogMessages;
import com.equalexperts.logging.OpsLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UnhandledExceptionTest {

    @Mock
    private OpsLogger<LogMessages> logger;

    private MockMvc mockMvc;

    @Test
    public void shouldHandleUnhandledException() throws Exception {
        UnsupportedOperationException expectedException = new UnsupportedOperationException();
        mockMvc = MockMvcBuilders.standaloneSetup(new TestController(expectedException)).setControllerAdvice(new ExceptionController(logger)).build();
        mockMvc.perform(get("/someException"))
                .andExpect(status().is(500));
        verify(logger).log(LogMessages.Unexpected, expectedException);
    }

    @Controller
    private static class TestController{

        private final UnsupportedOperationException expectedException;

        public TestController(UnsupportedOperationException expectedException) {

            this.expectedException = expectedException;
        }

        @RequestMapping("/someException")
        public void methodThatThrowsException(){
            throw expectedException;
        }

    }

}
