package com.example.springboot.ExceptionHandler;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 李阳
 * @Date: 2025/05/16/21:01
 * @Description: 异常信息
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}