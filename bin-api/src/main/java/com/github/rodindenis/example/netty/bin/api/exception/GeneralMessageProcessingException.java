package com.github.rodindenis.example.netty.bin.api.exception;

public class GeneralMessageProcessingException extends RuntimeException {
    public GeneralMessageProcessingException() {
    }

    public GeneralMessageProcessingException(String message) {
        super(message);
    }

    public GeneralMessageProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralMessageProcessingException(Throwable cause) {
        super(cause);
    }

    public GeneralMessageProcessingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
