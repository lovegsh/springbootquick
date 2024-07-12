package com.gsh.springbootquick.common.exception;

/**
 * @author GSH
 * @create 2023/3/8 22:07
 */
public class DefaultException extends RuntimeException{

    public DefaultException() {
    }

    public DefaultException(String message) {
        super(message);
    }

    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultException(Throwable cause) {
        super(cause);
    }

    public DefaultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
