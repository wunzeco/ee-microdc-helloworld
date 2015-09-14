package com.barclaycard.collections.controllers;


import com.barclaycard.collections.LogMessages;
import com.equalexperts.logging.OpsLogger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ExceptionController {

    private final OpsLogger<LogMessages> opsLogger;

    public ExceptionController(OpsLogger opsLogger) {
        this.opsLogger = opsLogger;
    }

    @ResponseStatus(value = INTERNAL_SERVER_ERROR, reason = "Unexpected Server Exception")
    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception) {
        opsLogger.log(LogMessages.Unexpected,exception);
    }
}
