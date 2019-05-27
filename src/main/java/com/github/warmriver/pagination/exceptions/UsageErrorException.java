package com.github.warmriver.pagination.exceptions;

public class UsageErrorException extends Exception {
    public UsageErrorException() {
        super();
    }

    public UsageErrorException(String message) {
        super(message);
    }

    public UsageErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsageErrorException(Throwable cause) {
        super(cause);
    }

    protected UsageErrorException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

