package com.gsh.springbootquick.common.exception;

import com.gsh.springbootquick.system.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author GSH
 * @create 2023/3/8 22:10
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(DefaultException.class)
    public Result<Void> handleGseinException(DefaultException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }
}
