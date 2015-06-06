package com.jee.web.api.exception;

/**
 * Created by Administrator on 2015/6/6.
 */
public class RestfulException extends RuntimeException {
    public RestfulException() {
    }

    public RestfulException(String message) {
        super(message);
    }

    public RestfulException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestfulException(Throwable cause) {
        super(cause);
    }

    public RestfulException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
