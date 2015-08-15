package com.jee.esapi.exception;

/**
 * Created by Administrator on 2015/8/14.
 */
public class EncryptException extends Exception {

    public EncryptException() {
    }

    public EncryptException(String message) {
        super(message);
    }

    public EncryptException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncryptException(Throwable cause) {
        super(cause);
    }

    public EncryptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
