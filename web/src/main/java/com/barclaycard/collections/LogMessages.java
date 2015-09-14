package com.barclaycard.collections;

import com.equalexperts.logging.LogMessage;

public enum LogMessages implements LogMessage {
    Unexpected("Unexpected", "Unexpected Exception");

    private final String errorCode;
    private final String message;

    LogMessages(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getMessageCode() {
        return errorCode;
    }

    @Override
    public String getMessagePattern() {
        return message;
    }
}
