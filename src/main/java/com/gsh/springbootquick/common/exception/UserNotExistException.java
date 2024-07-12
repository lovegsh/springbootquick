package com.gsh.springbootquick.common.exception;

/**
 * Create By GSH on .
 */
public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("用户不存在");
    }

}
