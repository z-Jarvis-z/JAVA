package com.wh.bank.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wh.bank.utils.Result;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(Exception.class)
    public Result apiExceptionHandler(Exception e) {
        return Result.error(e.getMessage());        
    }
}
